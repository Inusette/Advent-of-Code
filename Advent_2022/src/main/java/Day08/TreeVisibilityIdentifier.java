package Day08;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Inna Pirina
 * @since 08.12.2022
 */
public class TreeVisibilityIdentifier {

	private final Set<Point> allVisibleTrees;

	private final Map<Point, Integer> scenicScores;

	public TreeVisibilityIdentifier() {
		allVisibleTrees = new HashSet<>();
		scenicScores = new HashMap<>();
	}

	public void exploreForest(List<List<Integer>> forestMap) {
		lookRight(forestMap);
		lookLeft(forestMap);
		lookDown(forestMap);
		lookUp(forestMap);
	}

	public int findNumberOfVisibleTrees() {
		return allVisibleTrees.size();
	}

	public int findHighestPossibleScenicScore() {
		Optional<Integer> max = scenicScores.values().stream().max(Integer::compareTo);
		return max.orElse(0);
	}

	private void lookRight(List<List<Integer>> forestMap) {
		for (int currentY = 0; currentY < forestMap.size(); currentY++) {
			int highestVisibleTree = -1;
			Map<Point, Integer> seenTrees = new HashMap<>();
			for (int currentX = 0; currentX < forestMap.get(0).size(); currentX++) {
				Point currentTreePosition = new Point(currentX, currentY);
				highestVisibleTree = findVisibleTree(forestMap, highestVisibleTree, currentTreePosition);
				estimateTreesScenicScores(forestMap, seenTrees, currentTreePosition);
			}
			seenTrees.keySet().
					forEach(seenTree -> scenicScores.put(seenTree,
							scenicScores.getOrDefault(seenTree, 1) * seenTrees.get(seenTree)));
		}
	}

	private void lookLeft(List<List<Integer>> forestMap) {
		for (int currentY = 0; currentY < forestMap.size(); currentY++) {
			int highestVisibleTree = -1;
			Map<Point, Integer> seenTrees = new HashMap<>();
			for (int currentX = forestMap.get(0).size() - 1; currentX >= 0; currentX--) {
				Point currentTreePosition = new Point(currentX, currentY);
				highestVisibleTree = findVisibleTree(forestMap, highestVisibleTree, currentTreePosition);
				estimateTreesScenicScores(forestMap, seenTrees, currentTreePosition);
			}
			seenTrees.keySet().
					forEach(seenTree -> scenicScores.put(seenTree,
							scenicScores.getOrDefault(seenTree, 1) * seenTrees.get(seenTree)));
		}
	}

	private void lookDown(List<List<Integer>> forestMap) {
		for (int currentX = 0; currentX < forestMap.get(0).size(); currentX++) {
			int highestVisibleTree = -1;
			Map<Point, Integer> seenTrees = new HashMap<>();
			for (int currentY = 0; currentY < forestMap.size(); currentY++) {
				Point currentTreePosition = new Point(currentX, currentY);
				highestVisibleTree = findVisibleTree(forestMap, highestVisibleTree, currentTreePosition);
				estimateTreesScenicScores(forestMap, seenTrees, currentTreePosition);
			}
			seenTrees.keySet().
					forEach(seenTree -> scenicScores.put(seenTree,
							scenicScores.getOrDefault(seenTree, 1) * seenTrees.get(seenTree)));
		}
	}


	private void lookUp(List<List<Integer>> forestMap) {
		for (int currentX = 0; currentX < forestMap.get(0).size(); currentX++) {
			int highestVisibleTree = -1;
			Map<Point, Integer> seenTrees = new HashMap<>();
			for (int currentY = forestMap.size() - 1; currentY >= 0; currentY--) {
				Point currentTreePosition = new Point(currentX, currentY);
				highestVisibleTree = findVisibleTree(forestMap, highestVisibleTree, currentTreePosition);
				estimateTreesScenicScores(forestMap, seenTrees, currentTreePosition);
			}
			seenTrees.keySet().
					forEach(seenTree -> scenicScores.put(seenTree,
							scenicScores.getOrDefault(seenTree, 1) * seenTrees.get(seenTree)));
		}
	}


	private int findVisibleTree(List<List<Integer>> forestMap, int highestTree, Point currentTreePosition) {
		Integer currentTreeHight = forestMap.get(currentTreePosition.y).get(currentTreePosition.x);
		if (currentTreeHight > highestTree) {
			highestTree = currentTreeHight;
			allVisibleTrees.add(currentTreePosition);
		}
		return highestTree;

	}

	private void estimateTreesScenicScores(List<List<Integer>> forestMap, Map<Point, Integer> seenTrees, Point currentTreePosition) {
		Integer currentTreeHight = forestMap.get(currentTreePosition.y).get(currentTreePosition.x);
		Set<Point> seenTreesPoints = new HashSet<>(seenTrees.keySet());
		for (Point treeBefore : seenTreesPoints) {
			addScenicScoreForTree(seenTrees, treeBefore, seenTrees.get(treeBefore) + 1);
			if (currentTreeHight >= forestMap.get(treeBefore.y).get(treeBefore.x)) {
				addScenicScoreForTree(scenicScores, treeBefore, scenicScores.getOrDefault(treeBefore, 1) * seenTrees.get(treeBefore));
				seenTrees.remove(treeBefore);
			}
		}
		addScenicScoreForTree(seenTrees, currentTreePosition, 0);
	}

	private void addScenicScoreForTree(Map<Point, Integer> scenicScores, Point treeBefore, int scenicScores1) {
		scenicScores.put(treeBefore,
				scenicScores1);
	}
}
