package Day07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Inna Pirina
 * @since 07.12.2022
 */
public class DataStorageOrganizer {

	private final ItemInStorage root;

	private ItemInStorage currentDirectory;

	public DataStorageOrganizer() {
		root = new ItemInStorage("/", 0, null, new HashMap<>());
		currentDirectory = root;
	}

	public long sumFileSizes(List<String> storageOutline) {
		long sum = 0;
		for (String line : storageOutline) {
			if (line.startsWith("$ cd")) {
				if (line.trim().equalsIgnoreCase("$ cd /")) {
					currentDirectory = root;
				} else if (line.trim().equalsIgnoreCase("$ cd ..")) {
					if (currentDirectory.size < 100000) {
						sum += currentDirectory.size;
					}
					currentDirectory.parent.size += currentDirectory.size;
					currentDirectory = currentDirectory.parent;
				} else {
					String innerDirName = line.replace("$ cd ", "").trim();
					currentDirectory = currentDirectory.children.get(innerDirName);
				}
			} else if (line.startsWith("$ ls")) {
				continue;
			} else if (line.startsWith("dir")) {
				String innerDirName = line.replace("dir ", "").trim();
				currentDirectory.children.put(innerDirName, new ItemInStorage(innerDirName, 0, currentDirectory, new HashMap<>()));
			} else {
				String[] split = line.trim().split(" ");
				int filesize = Integer.parseInt(split[0]);
				String fileName = split[1];
				currentDirectory.size += filesize;
				currentDirectory.children.put(fileName, new ItemInStorage(fileName, filesize, currentDirectory, null));
			}
		}
		root.size += currentDirectory.size;
		return sum;
	}

	public int findADirectoryToDelete() {
		int neededAmountToFreeUp = 30000000 - (70000000 - root.size);
		return findRequiredDirectorySize(root, neededAmountToFreeUp, root.size);
	}

	private int findRequiredDirectorySize(ItemInStorage directory, int neededSize, int smallestPassingDirectory) {
		if (directory.size < neededSize) {
			return smallestPassingDirectory;
		}
		for (ItemInStorage childDirectory : directory.children.values()) {
			if (childDirectory.children == null) {
				continue;
			}
			if (childDirectory.size < smallestPassingDirectory && childDirectory.size >= neededSize) {
				smallestPassingDirectory = childDirectory.size;
				smallestPassingDirectory = findRequiredDirectorySize(childDirectory, neededSize, smallestPassingDirectory);
			}
		}
		return smallestPassingDirectory;
	}

	private static class ItemInStorage {
		String name;
		int size;
		ItemInStorage parent;
		Map<String, ItemInStorage> children;

		public ItemInStorage(String name, int size, ItemInStorage parent, Map<String, ItemInStorage> children) {
			this.name = name;
			this.size = size;
			this.parent = parent;
			this.children = children;
		}
	}
}
