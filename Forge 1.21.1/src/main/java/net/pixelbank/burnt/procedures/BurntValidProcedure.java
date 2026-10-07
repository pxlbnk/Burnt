package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

public class BurntValidProcedure {
	public static boolean execute() {
		if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
			return true;
		}
		return false;
	}
}