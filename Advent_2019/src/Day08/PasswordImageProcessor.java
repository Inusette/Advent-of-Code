package Day08;

// // Full task at https://adventofcode.com/2019/day/8

// Part 1:
// The image you received is 25 pixels wide and 6 pixels tall.
// To make sure the image wasn't corrupted during transmission,
// the Elves would like you to find the layer that contains the fewest 0 digits.
// On that layer, what is the number of 1 digits multiplied by the number of 2 digits?

// Part 2:
// Now you're ready to decode the image. The image is rendered by stacking the layers and aligning the pixels with
// the same positions in each layer. The digits indicate the color of the corresponding pixel: 0 is black,
// 1 is white, and 2 is transparent.
// What message is produced after decoding your image?

import java.util.List;

public class PasswordImageProcessor {


    private static final String INPUT = "../Advent_2019/src/Day08/input.txt";
    private static final int PIXELS_WIDE = 25;
    private static final int PIXELS_TALL = 6;
    private static final char FEWEST_OF = '0';


    public static void main(String[] args) {

        // read the input file
        List<Character> input = Utils.InputReader.readFileIntoListOfChars(INPUT);

        // create the password reader object
        PasswordReader passwordReader = new PasswordReader(input, PIXELS_WIDE, PIXELS_TALL);

        // Part 1:
        System.out.println(passwordReader.calculateCorruptionValue(FEWEST_OF) + "\n");

        // Part 2:
        List<List<Character>> finalMessage = passwordReader.getNonTransparentRows();

        // make the string more readable
        for (List<Character> row : finalMessage) {

            String line = Utils.ListAssembler.joinListOfCharsIntoString(row);
            line = line.replaceAll("0", " ");
            System.out.println(line);
        }
    }
}
