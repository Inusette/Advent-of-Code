package Day1;

import java.util.List;

class FuelCalculator {

    private static final int DIVIDE_BY = 3;
    private static final int SUBTRACT = 2;


    static int calculateTotalMassFuel(List<String> masses) {

        int totalFuel = 0;

        for (String mass : masses) {

            // calculate the fuel needed for the mass and add to the sum
            totalFuel += calculateFuel(Integer.parseInt(mass));
        }
        return totalFuel;
    }


    static int calculateTotalFuel(List<String> masses) {

        int totalFuel = 0;

        for (String mass : masses) {

            // calculate the fuel needed for the mass
            int fuel = calculateFuel(Integer.parseInt(mass));

            while (fuel >= 0) {

                // first add the current fuel to the total sum
                totalFuel += fuel;

                // then calculate the fuel for fuel
                fuel = calculateFuel(fuel);
            }
        }
        return totalFuel;
    }


    private static int calculateFuel(int mass) {

        // To find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
        return (mass / DIVIDE_BY) - SUBTRACT;
    }

}
