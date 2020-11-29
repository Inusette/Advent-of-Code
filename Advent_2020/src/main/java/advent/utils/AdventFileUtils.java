package advent.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static advent.utils.AdventConstants.INPUT_FILE;
import static advent.utils.AdventConstants.OUTPUT_FILE;


public class AdventFileUtils {

    /**
     * Reads the class path input file into a list of lines, where each line is a string
     *
     * @return List of lines, or an empty list + a printed exception
     */
    public static List<String> readClassInputIntoLines(Class sourceClass) {

        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = readClassFileIntoLines(sourceClass);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        return fileLines;
    }

    public static void writeStringIntoClassOutput(Class sourceClass, String output) throws FileNotFoundException {

        URL outputFileUrl = getFileUrl(sourceClass, OUTPUT_FILE);

        try (FileOutputStream outputStream = new FileOutputStream(new File(outputFileUrl.getPath()))){
            outputStream.write(output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readClassFileIntoLines(Class sourceClass) throws IOException, URISyntaxException {

        URL inputFileUrl = getFileUrl(sourceClass, INPUT_FILE);
        return Files.readAllLines(Paths.get(inputFileUrl.toURI()), StandardCharsets.UTF_8);
    }

    private static URL getFileUrl(Class sourceClass, String fileName) throws FileNotFoundException {

        URL fileUrl = sourceClass.getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException();
        }
        return fileUrl;
    }
}
