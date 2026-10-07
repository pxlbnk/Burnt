package net.pixelbank.burnt.mixins;

import net.pixelbank.burnt.util.GlobalMappingsPacks;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.ServerPacksSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.function.Consumer;

// Fabric has no AddPackFindersEvent, so hook the built-in server pack source instead
// Appends <configdir>/burnt/mappings as always-active datapacks, one copy for every world
// Guarded to ServerPacksSource so the client resource repository is left alone

@Mixin(BuiltInPackSource.class)
public class BuiltInPackSourceMixin {

	@Inject(method = "loadPacks", at = @At("TAIL"))
	private void burnt$addGlobalMappings(Consumer<Pack> onLoad, CallbackInfo ci) {
		if (!(((Object) this) instanceof ServerPacksSource))
			return;
		Path folder = GlobalMappingsPacks.folder(FabricLoader.getInstance().getConfigDir());
		GlobalMappingsPacks.ensureFolder(folder);
		GlobalMappingsPacks.source(folder).loadPacks(onLoad);
	}
}
