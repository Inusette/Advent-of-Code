package Day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ModuleLauncher {

    private static final String INPUT = "../Advent_2019/src/Day1/input.txt";


    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> inputMasses = readFileToList(INPUT);

        // Part 1:
        // Calculate the fuel needed for the masses in input
        int totalMassFuel = FuelCalculator.calculateTotalMassFuel(inputMasses);
        System.out.println(totalMassFuel);

        // Part 2:
        // Calculate the fuel needed for each mass and its fuels
        int totalFuel = FuelCalculator.calculateTotalFuel(inputMasses);

        System.out.println(totalFuel);
    }


    private static List<String> readFileToList(String fileName) {

        // instantiate an empty list of strings
        List<String> fileStrings = new ArrayList<>();

        // attempt to read the file into the list
        try {
            fileStrings = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fileStrings;
    }

}
