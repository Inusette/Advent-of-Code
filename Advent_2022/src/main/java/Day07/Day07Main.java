package Day07;

import java.util.List;

import advent.utils.AdventFileUtils;

public class Day07Main {

	public static void main(String[] args) {
		List<String> inputAsLines = getInput();

		DataStorageOrganizer dataStorageOrganizer = new DataStorageOrganizer();
		long sumOfSpecificDirectorySizes = dataStorageOrganizer.sumFileSizes(inputAsLines);
		System.out.println(sumOfSpecificDirectorySizes);

		int aDirectoryToDelete = dataStorageOrganizer.findADirectoryToDelete();
		System.out.println(aDirectoryToDelete);
	}

	protected static List<String> getInput() {
		return AdventFileUtils.readInputIntoStringLines(Day07Main.class);
	}
}
