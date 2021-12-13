package Day13;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrigamiFolder {

    private Set<Point> dotsOnFoldedPaper;

    public OrigamiFolder() {
        this.dotsOnFoldedPaper = new HashSet<>();
    }

    public void foldPaper(List<String> dots, List<String> instructions) {
        fillInitialPointsOnPaper(dots);
        List<Point> foldingInstructions = parseFoldingInstructions(instructions);
        for (Point foldingInstruction : foldingInstructions) {
            fold(foldingInstruction);
            System.out.println(dotsOnFoldedPaper.size());
        }

        showFoldedPaper();

    }

    private void showFoldedPaper() {
        Integer maxY = dotsOnFoldedPaper.stream().map(dot -> dot.y).max(Integer::compareTo).get();
        Integer maxX = dotsOnFoldedPaper.stream().map(dot -> dot.x).max(Integer::compareTo).get();
        String[][] foldedResult = new String[maxY + 1][maxX + 1];
        for (String[] row : foldedResult) {
            Arrays.fill(row, " ");
        }
        for (Point dot : dotsOnFoldedPaper) {
            foldedResult[dot.y][dot.x] = "#";
        }

        for (String[] row : foldedResult) {
            System.out.println(Arrays.toString(row));
        }
    }

    private void fold(Point foldingInstruction) {
        if (foldingInstruction.x != 0) {
            foldVertically(foldingInstruction);
        } else {
            foldHorizontally(foldingInstruction);
        }
    }

    private void foldVertically(Point foldingInstruction) {
        Set<Point> foldedPoints = new HashSet<>();
        for (Point dot : dotsOnFoldedPaper) {
            if (dot.x > foldingInstruction.x) {
                int newX = dot.x - (dot.x - foldingInstruction.x) * 2;
                foldedPoints.add(new Point(newX, dot.y));
            } else {
                foldedPoints.add(dot);
            }
        }
        dotsOnFoldedPaper = foldedPoints;
    }

    private void foldHorizontally(Point foldingInstruction) {
        Set<Point> foldedPoints = new HashSet<>();
        for (Point dot : dotsOnFoldedPaper) {
            if (dot.y > foldingInstruction.y) {
                int newY = dot.y - (dot.y - foldingInstruction.y) * 2;
                foldedPoints.add(new Point(dot.x, newY));
            } else {
                foldedPoints.add(dot);
            }
        }
        dotsOnFoldedPaper = foldedPoints;
    }

    private void fillInitialPointsOnPaper(List<String> dots) {
        dots.stream()
                .map(dotLoc -> dotLoc.trim().split(","))
                .forEach(splitDot -> dotsOnFoldedPaper.add(new Point(Integer.parseInt(splitDot[0]), Integer.parseInt(splitDot[1]))));
    }

    private List<Point> parseFoldingInstructions(List<String> instructions) {
        List<Point> foldingPoints = new ArrayList<>();
        instructions.stream().map(instruction -> instruction.trim().split("=")).forEach(splitInstruction -> {
            foldingPoints.add(splitInstruction[0].contains("x")
                    ? new Point(Integer.parseInt(splitInstruction[1]), 0)
                    : new Point(0, Integer.parseInt(splitInstruction[1])));
        });
        return foldingPoints;
    }

}
