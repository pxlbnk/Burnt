package net.pixelbank.burnt.util;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

// Loads user datapacks from <configdir>/burnt/mappings as always-active server datapacks
// One copy of a mapping pack applies to every world
// 1.20.1 Pack API variant

public final class GlobalMappingsPacks {

	public static final String README =
			"Burnt - global block-mapping datapacks\n" +
			"======================================\n" +
			"A ready-to-edit starter pack (\"Burnt Block Mappings\") is placed in this folder\n" +
			"automatically on first run - open its files under data/burnt/tags/block/ and add\n" +
			"your blocks. You can also drop your own mapping datapacks here (a folder or a .zip,\n" +
			"the same kind you would put in <world>/datapacks/).\n" +
			"Everything in this folder applies to EVERY world automatically - no per-save copying -\n" +
			"and is always enabled. After adding or editing a pack, run /reload in-game (or rejoin\n" +
			"the world) to pick up the change.\n" +
			"\n" +
			"When Burnt ships an updated starter pack it replaces this one only if you have not\n" +
			"edited it. If you have, yours is kept and a note appears in the log - delete the folder\n" +
			"to take the new one. A pack from an older Burnt version is moved to ../mappings_backup\n" +
			"first, so nothing is ever lost.\n";


	public static Path folder(Path configDir) {
		return configDir.resolve("burnt").resolve("mappings");
	}

	// The editable template pack shipped in the jar, unpacked on first run as a starting point
	private static final String SEED_RESOURCE = "/burnt_seed/mappings.zip";
	private static final String SEED_PACK_NAME = "Burnt Block Mappings 1.20.1";
	// Bump this whenever the bundled pack changes, so existing installs get the new copy.
	private static final int SEED_PACK_VERSION = 2;

	// Create the folder (+ a one-time README), then seed or refresh the editable template pack.
	public static void ensureFolder(Path folder) {
		try {
			Files.createDirectories(folder);
			Path readme = folder.resolve("README.txt");
			if (!Files.exists(readme))
				Files.writeString(readme, README);
			seedDefault(folder);
		} catch (IOException e) {
			BurntMod.LOGGER.warn("[Burnt] could not prepare global mappings folder {}: {}", folder, e.toString());
		}
	}

	// Seed on first run and refresh when we ship a newer one - the rules live in SeedPackMigration
	private static void seedDefault(Path folder) {
		Path backupRoot = folder.getParent().resolve("mappings_backup");
		SeedPackMigration.Result result = SeedPackMigration.run(folder, SEED_PACK_NAME, SEED_PACK_VERSION,
				() -> GlobalMappingsPacks.class.getResourceAsStream(SEED_RESOURCE), backupRoot);
		Path dest = folder.resolve(SEED_PACK_NAME);
		switch (result) {
			case SEEDED -> BurntMod.LOGGER.info("[Burnt] seeded default mapping pack into {}", dest);
			case REPLACED -> BurntMod.LOGGER.info("[Burnt] updated the default mapping pack in {} (it was unedited)", dest);
			case BACKED_UP_AND_REPLACED -> BurntMod.LOGGER.info(
					"[Burnt] replaced an older mapping pack with the current one; your previous copy is at {}. "
					+ "Any entries you added there need copying into {} to take effect.", SeedPackMigration.lastBackup, dest);
			case KEPT_USER_EDITS -> BurntMod.LOGGER.warn(
					"[Burnt] the mapping pack in {} has been edited, so it was left as it is - but Burnt ships a newer one. "
					+ "Delete that folder to get the current pack (your copy is not touched otherwise).", dest);
			case FAILED -> BurntMod.LOGGER.warn("[Burnt] could not seed or update the default mapping pack in {}", folder);
			default -> { }
		}
	}

	public static RepositorySource source(Path folder) {
		return onLoad -> {
			if (!Files.isDirectory(folder))
				return;
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
				for (Path entry : stream) {
					Pack pack = readPack(entry);
					if (pack != null)
						onLoad.accept(pack);
				}
			} catch (IOException e) {
				BurntMod.LOGGER.warn("[Burnt] failed scanning global mappings folder: {}", e.toString());
			}
		};
	}

	private static Pack readPack(Path entry) {
		boolean dir = Files.isDirectory(entry);
		String name = entry.getFileName().toString();
		boolean zip = !dir && name.toLowerCase(Locale.ROOT).endsWith(".zip");
		if (!dir && !zip)
			return null;
		if (dir && !Files.isRegularFile(entry.resolve("pack.mcmeta")))
			return null; // skip README.txt and any non-pack folder
		Pack.ResourcesSupplier supplier;
		if (dir)
			supplier = id -> new PathPackResources(id, entry, false);
		else
			supplier = id -> new FilePackResources(id, entry.toFile(), false);
		// required=true -> always active (cannot be turned off), so it loads in every world.
		return Pack.readMetaAndCreate("burnt_global/" + name, Component.literal(name), true,
				supplier, PackType.SERVER_DATA, Pack.Position.TOP, PackSource.WORLD);
	}

	private GlobalMappingsPacks() {}
}
