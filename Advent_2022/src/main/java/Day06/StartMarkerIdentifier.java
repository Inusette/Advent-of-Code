package Day06;

/**
 * @author Inna Pirina
 * @since 06.12.2022
 */
public class StartMarkerIdentifier {

	public static int findIndexOfFirstStartMarker(String inputData, int packetSize) {
		if (inputData.length() < packetSize) {
			throw new IllegalArgumentException("Input data length must be londer than " + packetSize);
		}
		for (int currentIndex = packetSize; currentIndex < inputData.length(); currentIndex++) {
			String currentChunk = inputData.substring(currentIndex - packetSize, currentIndex);
			long numberOfUniqueCharacters = currentChunk.chars().distinct().count();
			if (numberOfUniqueCharacters == packetSize) {
				return currentIndex;
			}
		}
		return 0;
	}
}
