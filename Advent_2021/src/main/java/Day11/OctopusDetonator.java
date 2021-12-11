package Day11;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OctopusDetonator {

    private final int GRID_MAX = 10;

    public long detonateOctopusFlashes(List<List<Integer>> octopuses) {
        int step = 0;
        long flashCount = 0;
        while (step != 100) {
            incrementEnergy(octopuses);

            Set<Point> flashed = getFlashedOctopuses(octopuses);
            flashed.forEach(flash -> octopuses.get(flash.y).set(flash.x, 0));
            flashCount += flashed.size();
            step++;
        }
        return flashCount;
    }

    public long findAllFlashingStep(List<List<Integer>> octopuses) {
        int step = 0;
        long flashCount = 0;
        while (flashCount != 100) {
            incrementEnergy(octopuses);
            Set<Point> flashed = getFlashedOctopuses(octopuses);
            flashed.forEach(flash -> octopuses.get(flash.y).set(flash.x, 0));
            flashCount = flashed.size();
            step++;
        }
        return step;
    }

    private Set<Point> getFlashedOctopuses(List<List<Integer>> octopuses) {
        Set<Point> flashed = new HashSet<>();
        for (int row = 0; row < GRID_MAX; row++) {
            for (int column = 0; column < GRID_MAX; column++) {
                Integer octopusEnergy = octopuses.get(row).get(column);
                if (octopusEnergy > 9) {
                    flash(octopuses, new Point(column, row), flashed);
                }
            }
        }
        return flashed;
    }

    private void incrementEnergy(List<List<Integer>> octopuses) {
        for (int row = 0; row < GRID_MAX; row++) {
            for (int column = 0; column < GRID_MAX; column++) {
                octopuses.get(row).set(column, octopuses.get(row).get(column) + 1);
            }
        }
    }

    private void flash(List<List<Integer>> octopuses, Point flash, Set<Point> flashed) {
        if (flashed.contains(flash)) {
            return;
        }
        flashed.add(flash);

        Set<Point> neighbours = getNeighbours(flash);
        for (Point neighbour : neighbours) {
            Integer neighbourEnergy = octopuses.get(neighbour.y).get(neighbour.x);
            neighbourEnergy++;
            octopuses.get(neighbour.y).set(neighbour.x, neighbourEnergy);
            if (neighbourEnergy > 9) {
                flash(octopuses, neighbour, flashed);
            }
        }
    }

    private Set<Point> getNeighbours(Point flash) {
        Set<Point> neighbours = new HashSet<>();
        if (flash.x > 0) {
            // left neighbour
            neighbours.add(new Point(flash.x - 1, flash.y));
            if (flash.y > 0) {
                // left Neighbour Above
                neighbours.add(new Point(flash.x - 1, flash.y - 1));
            }
            if (flash.y < GRID_MAX - 1) {
                //left Neighbour Below
                neighbours.add(new Point(flash.x - 1, flash.y + 1));
            }
        }
        if (flash.x < GRID_MAX - 1) {
            // right Neighbour
            neighbours.add(new Point(flash.x + 1, flash.y));
            if (flash.y > 0) {
                // right Neighbour Above
                neighbours.add(new Point(flash.x + 1, flash.y - 1));
            }
            if (flash.y < GRID_MAX - 1) {
                // right Neighbour Below
                neighbours.add(new Point(flash.x + 1, flash.y + 1));
            }
        }
        if (flash.y > 0) {
            // above Neighbour
            neighbours.add(new Point(flash.x, flash.y - 1));
        }
        if (flash.y < GRID_MAX - 1) {
            // below Neighbour
            neighbours.add(new Point(flash.x, flash.y + 1));
        }
        return neighbours;
    }
}
