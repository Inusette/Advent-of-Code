package Day12;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

import advent.utils.AdventGridUtils;

/**
 * @author Inna Pirina
 * @since 12.12.2022
 */
public class HillClimbPathFinder {

	private final int maxX;

	private final int maxY;

	private final List<ArrayList<Character>> grid;
	private final Point startPoint;
	private final Point endPoint;

	public HillClimbPathFinder(List<ArrayList<Character>> grid) {
		this.grid = grid;
		this.maxX = grid.get(0).size();
		this.maxY = grid.size();
		startPoint = findAllPointsWithValue('S').get(0);
		endPoint = findAllPointsWithValue('E').get(0);
	}

	public int findPathWithLeastSteps() {
		grid.get(startPoint.y).set(startPoint.x, 'a');
		grid.get(endPoint.y).set(endPoint.x, 'z');
		return findShortestPathDistances(startPoint, endPoint);
	}

	public int findPathWithLeastStepsFromAnyStart() {
		List<Point> potentialStarts = findAllPointsWithValue('a');
		int shortestPath = Integer.MAX_VALUE;
		for (Point potentialStart : potentialStarts) {
			int shortestPathForStart = findShortestPathDistances(potentialStart, endPoint);
			if (shortestPathForStart < shortestPath && shortestPathForStart > 0) {
				shortestPath = shortestPathForStart;
			}
		}
		return shortestPath;
	}

	public List<Point> findAllPointsWithValue(Character valueToFind) {
		List<Point> allMatchingPoints = new ArrayList<>();
		for (int row = 0; row < grid.size(); row++) {
			for (int column = 0; column < grid.get(0).size(); column++) {
				if (Objects.equals(grid.get(row).get(column), valueToFind)) {
					allMatchingPoints.add(new Point(column, row));
				}
			}
		}
		return allMatchingPoints;
	}

	public int findShortestPathDistances(Point startPoint, Point endPoint) {
		Map<Point, Integer> pointDistances = new HashMap<>();
		pointDistances.put(startPoint, 0);
		PriorityQueue<Point> nextPoints = new PriorityQueue<>(Comparator.comparing(
				point -> pointDistances.getOrDefault(point, Integer.MAX_VALUE)));
		fillInPriorityQueue(nextPoints);

		while (!nextPoints.isEmpty()) {
			Point currentPoint = nextPoints.poll();
			Character currentValue = grid.get(currentPoint.y).get(currentPoint.x);
			if (Objects.equals(currentPoint, endPoint)) {
				break;
			}
			List<Point> neighbours = AdventGridUtils.getNeighbours(currentPoint, maxX, maxY);
			int distance = pointDistances.getOrDefault(currentPoint, Integer.MAX_VALUE) + 1;
			for (Point neighbour : neighbours) {
				Character neighbourValue = grid.get(neighbour.y).get(neighbour.x);
				if (neighbourValue - currentValue > 1) {
					continue;
				}
				if (distance < pointDistances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
					pointDistances.put(neighbour, distance);
					nextPoints.remove(neighbour);
					nextPoints.add(neighbour);
				}
			}
		}
		return pointDistances.get(endPoint);
	}

	private void fillInPriorityQueue(PriorityQueue<Point> nextPoints) {
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				nextPoints.add(new Point(x, y));
			}
		}
	}
}
