package Day04;

import java.util.ArrayList;
import java.util.List;

public class PassportParser {

    public static List<String> parsePassports(List<String> inputLines) {
        List<String> passports = new ArrayList<>();
        StringBuilder currentPassport = new StringBuilder();
        inputLines.add(""); // make sure there is a last empty line

        for (String line : inputLines) {
            if (line.isEmpty()) {
                passports.add(currentPassport.toString());
                currentPassport = new StringBuilder();
            }
            else {
                currentPassport.append(String.format(" %s", line));
            }
        }
        return passports;
    }
}
