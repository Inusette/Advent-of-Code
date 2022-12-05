package Day05;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Inna Pirina
 * @since 05.12.2022
 */
public class ContainerStackArrenger {

	public static String rearrangeContainerStacks(List<MovingDirection> movingDirections, List<ArrayDeque<String>> containerStacks) {
		String topContainers;
		for (MovingDirection direction : movingDirections) {
			IntStream.range(0, direction.containerAmount())
					.mapToObj(container -> containerStacks.get(direction.fromStack() - 1).pop())
					.forEach(topContainer -> containerStacks.get(direction.toStack() - 1).addFirst(topContainer));
		}
		topContainers = containerStacks.stream().map(ArrayDeque::peekFirst).collect(Collectors.joining());
		return topContainers;
	}

	public static String rearrangeContainerStacksEfficiently(List<MovingDirection> movingDirections,
			List<ArrayDeque<String>> containerStacks) {
		String topContainers;
		for (MovingDirection direction : movingDirections) {
			ArrayDeque<String> stupidInefficientTemporaryStorage = new ArrayDeque<>();
			IntStream.range(0, direction.containerAmount())
					.mapToObj(container -> containerStacks.get(direction.fromStack() - 1).pop())
					.forEach(stupidInefficientTemporaryStorage::push);

			IntStream.range(0, stupidInefficientTemporaryStorage.size())
					.forEach(container -> containerStacks.get(direction.toStack() - 1).push(stupidInefficientTemporaryStorage.pop()));
		}
		topContainers = containerStacks.stream().map(ArrayDeque::peekFirst).collect(Collectors.joining());
		return topContainers;
	}

	public static List<ArrayDeque<String>> makeStacksFromInput(List<String> input) {
		List<ArrayDeque<String>> containerStacks = IntStream
				.rangeClosed(0, 8)
				.mapToObj(i -> new ArrayDeque<String>())
				.toList();

		for (String line : input) {
			if (Character.isDigit(line.charAt(1))) {
				break;
			}

			int containerNumber = 0;
			String[] splitLine = line.split("");
			for (int index = 1; index <= line.length() - 1; index += 4) {
				if (!splitLine[index].isBlank()) {
					containerStacks.get(containerNumber).addLast(splitLine[index]);
				}
				containerNumber++;
			}
		}
		return containerStacks;
	}

	public static List<MovingDirection> parseMovingDirections(List<String> input) {
		return input.stream().filter(line -> line.startsWith("move"))
				.map(ContainerStackArrenger::getDirectionParts)
				.map(directionParts -> new MovingDirection(Integer.parseInt(directionParts[0]),
						Integer.parseInt(directionParts[1]), Integer.parseInt(directionParts[2])))
				.toList();
	}

	private static String[] getDirectionParts(String line) {
		line = line.replace("move ", "")
				.replace(" from ", ",")
				.replace(" to ", ",");
		return line.split(",");
	}
}
