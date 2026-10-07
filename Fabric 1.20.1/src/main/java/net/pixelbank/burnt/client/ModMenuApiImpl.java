package net.pixelbank.burnt.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

@Environment(EnvType.CLIENT)
public class ModMenuApiImpl implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(BurntBasicConfigConfiguration.class, parent).get();
	}
}
