package Day1;

// Part 1:
// The Fuel Counter-Upper needs to know the total fuel requirement.
// To find it, individually calculate the fuel needed for the mass
// of each module (your puzzle input), then add together all the fuel values.

// Part 2:
// What is the sum of the fuel requirements for all of the modules on your spacecraft when also
// taking into account the mass of the added fuel? (Calculate the fuel requirements for each module
// separately, then add them all up at the end.)
// Full task at https://adventofcode.com/2019/day/1


import java.util.List;

public class ModuleLauncher {

    private static final String INPUT = "../Advent_2019/src/Day1/input.txt";


    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> inputMasses = Utils.InputReader.readFileToList(INPUT);

        // Part 1:
        // Calculate the fuel needed for the masses in input
        int totalMassFuel = FuelCalculator.calculateTotalMassFuel(inputMasses);
        System.out.println(totalMassFuel);

        // Part 2:
        // Calculate the fuel needed for each mass and its fuels
        int totalFuel = FuelCalculator.calculateTotalFuel(inputMasses);

        System.out.println(totalFuel);
    }
}
