package net.pixelbank.burnt.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// Seeds and updates the editable template pack in config/burnt/mappings
// Stamped with a pack version and per-file hashes, so shipped changes reach existing installs
// User-edited files are kept, unedited ones replaced, pre-stamp installs backed up and reseeded
// Free of Minecraft types so it can be unit tested on its own

public final class SeedPackMigration {

	public enum Result {
		SEEDED,                    // first run, pack written
		UP_TO_DATE,                // stamped and current
		REPLACED,                  // stamped, older, untouched by the user
		BACKED_UP_AND_REPLACED,    // pre-stamp copy moved aside and reseeded
		KEPT_USER_EDITS,           // older but edited - left alone
		USER_ZIP,                  // a .zip of the same name is present
		FAILED
	}

	public static final String STAMP_FILE = ".burnt_seed";

	// Where the old pack went, when one was moved. Null unless the result is BACKED_UP_AND_REPLACED.
	public static Path lastBackup;

	public static Result run(Path folder, String packName, int version, Supplier<InputStream> zipSupplier, Path backupRoot) {
		lastBackup = null;
		try {
			Path dest = folder.resolve(packName);
			if (Files.exists(folder.resolve(packName + ".zip")))
				return Result.USER_ZIP;

			if (!Files.exists(dest)) {
				extract(zipSupplier, dest);
				writeStamp(dest, version);
				return Result.SEEDED;
			}

			int stamped = readStampVersion(dest);
			if (stamped >= version)
				return Result.UP_TO_DATE;

			if (stamped > 0) {
				if (!matchesStamp(dest))
					return Result.KEPT_USER_EDITS;
				deleteRecursively(dest);
				extract(zipSupplier, dest);
				writeStamp(dest, version);
				return Result.REPLACED;
			}

			// No stamp means a pre-stamp install - move theirs out of the mappings folder and seed the current pack
			Path backup = backupRoot.resolve(packName + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")));
			Files.createDirectories(backup.getParent());
			try {
				Files.move(dest, backup, StandardCopyOption.ATOMIC_MOVE);
			} catch (IOException atomicFailed) {
				copyRecursively(dest, backup);
				deleteRecursively(dest);
			}
			lastBackup = backup;
			extract(zipSupplier, dest);
			writeStamp(dest, version);
			return Result.BACKED_UP_AND_REPLACED;
		} catch (Exception e) {
			return Result.FAILED;
		}
	}

	private static void extract(Supplier<InputStream> zipSupplier, Path dest) throws IOException {
		try (InputStream in = zipSupplier.get()) {
			if (in == null) throw new IOException("bundled template pack missing");
			Path destAbs = dest.toAbsolutePath().normalize();
			try (ZipInputStream zip = new ZipInputStream(in)) {
				ZipEntry entry;
				while ((entry = zip.getNextEntry()) != null) {
					Path target = destAbs.resolve(entry.getName()).normalize();
					if (!target.startsWith(destAbs)) continue; // zip-slip guard
					if (entry.isDirectory()) {
						Files.createDirectories(target);
					} else {
						Files.createDirectories(target.getParent());
						Files.copy(zip, target, StandardCopyOption.REPLACE_EXISTING);
					}
					zip.closeEntry();
				}
			}
		}
	}

	// version=<n> followed by "<sha256>  <relative path>" for every file we wrote
	private static void writeStamp(Path dest, int version) throws IOException {
		StringBuilder sb = new StringBuilder("version=").append(version).append('\n');
		for (Map.Entry<String, String> e : hashTree(dest).entrySet())
			sb.append(e.getValue()).append("  ").append(e.getKey()).append('\n');
		Files.writeString(dest.resolve(STAMP_FILE), sb.toString(), StandardCharsets.UTF_8);
	}

	private static int readStampVersion(Path dest) {
		Path stamp = dest.resolve(STAMP_FILE);
		if (!Files.isRegularFile(stamp)) return 0;
		try {
			for (String line : Files.readAllLines(stamp, StandardCharsets.UTF_8))
				if (line.startsWith("version="))
					return Integer.parseInt(line.substring(8).trim());
		} catch (Exception ignored) {
		}
		return 0;
	}

	private static boolean matchesStamp(Path dest) {
		try {
			Map<String, String> recorded = new HashMap<>();
			for (String line : Files.readAllLines(dest.resolve(STAMP_FILE), StandardCharsets.UTF_8)) {
				int split = line.indexOf("  ");
				if (split > 0) recorded.put(line.substring(split + 2), line.substring(0, split));
			}
			return recorded.equals(hashTree(dest));
		} catch (Exception e) {
			return false;
		}
	}

	// relative path -> sha256, skipping the stamp itself
	private static Map<String, String> hashTree(Path root) throws IOException {
		Map<String, String> out = new TreeMap<>();
		List<Path> files = new ArrayList<>();
		Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				files.add(file);
				return FileVisitResult.CONTINUE;
			}
		});
		for (Path file : files) {
			String rel = root.relativize(file).toString().replace('\\', '/');
			if (rel.equals(STAMP_FILE)) continue;
			out.put(rel, sha256(file));
		}
		return out;
	}

	private static String sha256(Path file) throws IOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(Files.readAllBytes(file));
			StringBuilder sb = new StringBuilder(digest.length * 2);
			for (byte b : digest) sb.append(Character.forDigit((b >> 4) & 0xF, 16)).append(Character.forDigit(b & 0xF, 16));
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new IOException(e);
		}
	}

	private static void copyRecursively(Path from, Path to) throws IOException {
		Files.walkFileTree(from, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Files.createDirectories(to.resolve(from.relativize(dir).toString()));
				return FileVisitResult.CONTINUE;
			}
			@Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, to.resolve(from.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static void deleteRecursively(Path root) throws IOException {
		Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}
			@Override public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				if (e != null) throw e;
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private SeedPackMigration() {}
}
