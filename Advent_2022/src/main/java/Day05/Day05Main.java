package Day05;

import static Day05.ContainerStackArrenger.rearrangeContainerStacksEfficiently;
import static advent.utils.AdventFileUtils.readInputIntoStringLines;

import java.util.ArrayDeque;
import java.util.List;

public class Day05Main {

	public static void main(String[] args) {
		List<String> inputInLines = getInput();
		List<MovingDirection> movingDirections = ContainerStackArrenger.parseMovingDirections(inputInLines);

		String topContainers = ContainerStackArrenger.rearrangeContainerStacks(movingDirections, ContainerStackArrenger.makeStacksFromInput(inputInLines));
		System.out.println(topContainers);

		topContainers = rearrangeContainerStacksEfficiently(movingDirections, ContainerStackArrenger.makeStacksFromInput(inputInLines));
		System.out.println(topContainers);
	}

	protected static List<String> getInput() {
		return readInputIntoStringLines(Day05Main.class);
	}
}
