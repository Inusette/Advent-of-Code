package Day04;

import static advent.utils.AdventCollectionsUtils.listHasIntersection;
import static advent.utils.AdventCollectionsUtils.listWithinList;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Inna Pirina
 * @since 04.12.2022
 */
public class CleanupPairIdenitifier {

	public static final String LINE_SPLIT = ",";

	public static final String AREA_SPLIT = "-";

	public long countPairsWithCompletelyOverlappingAreas(List<ElfPair> elfPairs) {
		return elfPairs.stream()
				.filter(elfPair -> listWithinList(elfPair.firstElfsArea(), elfPair.secondElfsArea())
						|| listWithinList(elfPair.secondElfsArea(), elfPair.firstElfsArea()))
				.count();
	}

	public long countPairsWithIntersections(List<ElfPair> elfPairs) {
		return elfPairs.stream()
				.filter(elfPair -> listHasIntersection(elfPair.firstElfsArea(), elfPair.secondElfsArea()))
				.count();
	}

	public List<ElfPair> identifyPairAreas(List<String> elfAreasLines) {
		return elfAreasLines.stream()
				.map(currentPairLine -> currentPairLine.split(LINE_SPLIT))
				.map(splitAreas -> new ElfPair(identifyElfsArea(splitAreas[0]), identifyElfsArea(splitAreas[1])))
				.toList();
	}

	private List<Integer> identifyElfsArea(String areaRange) {
		String[] splitRange = areaRange.trim().split(AREA_SPLIT);
		return IntStream.rangeClosed(Integer.parseInt(splitRange[0]), Integer.parseInt(splitRange[1])).boxed().toList();
	}
}
