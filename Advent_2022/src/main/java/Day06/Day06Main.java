package Day06;

import java.util.List;

import advent.utils.AdventFileUtils;

public class Day06Main {

	public static void main(String[] args) {
		String inputAsString = getInput().get(0);
		System.out.println(inputAsString);

		int indexOfFirstStartMarker = StartMarkerIdentifier.findIndexOfFirstStartMarker(inputAsString, 4);
		System.out.println("Packet size 4: " + indexOfFirstStartMarker);

		indexOfFirstStartMarker = StartMarkerIdentifier.findIndexOfFirstStartMarker(inputAsString, 14);
		System.out.println("Packet size 14: " + indexOfFirstStartMarker);
	}

	protected static List<String> getInput() {
		return AdventFileUtils.readInputIntoStringLines(Day06Main.class);
	}
}
