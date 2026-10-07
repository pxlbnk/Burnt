package net.pixelbank.burnt.compat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.pixelbank.burnt.BurntMod;

import java.util.Map;

// Loads every data/<namespace>/burn_mappings/*.json into BurnMappingRegistry
// Format: { "mappings": [ { "from": "byg:mahogany_log", "to": "burnt:smoldering_log_2" } ] }
// Unknown ids and unparseable files are skipped with a log line, never a crash
public class BurnMappingLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    public BurnMappingLoader() {
        super(GSON, "burn_mappings");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> data, ResourceManager manager, ProfilerFiller profiler) {
        BurnMappingRegistry.clear();
        int count = 0;

        for (Map.Entry<ResourceLocation, JsonElement> entry : data.entrySet()) {
            try {
                JsonObject json = entry.getValue().getAsJsonObject();
                if (!json.has("mappings")) continue;

                JsonArray mappings = json.getAsJsonArray("mappings");
                for (JsonElement el : mappings) {
                    JsonObject mapping = el.getAsJsonObject();
                    ResourceLocation fromId = ResourceLocation.parse(mapping.get("from").getAsString());
                    ResourceLocation toId   = ResourceLocation.parse(mapping.get("to").getAsString());

                    Block from = BuiltInRegistries.BLOCK.get(fromId);
                    Block to   = BuiltInRegistries.BLOCK.get(toId);

                    if (from == Blocks.AIR) {
                        BurntMod.LOGGER.warn("[Burnt] burn_mapping '{}': unknown source block '{}' - skipping",
                                entry.getKey(), fromId);
                        continue;
                    }
                    if (to == Blocks.AIR) {
                        BurntMod.LOGGER.warn("[Burnt] burn_mapping '{}': unknown destination block '{}' - skipping",
                                entry.getKey(), toId);
                        continue;
                    }

                    BurnMappingRegistry.put(from, to);
                    count++;
                }
            } catch (Exception e) {
                BurntMod.LOGGER.error("[Burnt] Failed to parse burn_mapping file '{}': {}",
                        entry.getKey(), e.getMessage());
            }
        }

        BurntMod.LOGGER.info("[Burnt] Loaded {} burn mapping(s) from datapacks.", count);
    }
}
