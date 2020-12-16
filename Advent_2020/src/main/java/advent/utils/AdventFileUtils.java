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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Reads the class path input file into a list of lines, where each line is a string
     *
     * @return List of lines, or an empty list + a printed exception
     */
    public static List<Integer> readClassInputIntoIntegerLines(Class sourceClass) {

        List<Integer> numberLines;
        List<String> fileLines = readClassInputIntoLines(sourceClass);
        numberLines = fileLines.stream().map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        return numberLines;
    }

    /**
     * Reads the class path input file into a list of lines, where each line is a string
     *
     * @return List of lines, or an empty list + a printed exception
     */
    public static List<Long> readClassInputIntoLongLines(Class sourceClass) {

        List<Long> numberLines;
        List<String> fileLines = readClassInputIntoLines(sourceClass);
        numberLines = fileLines.stream().map(Long::parseLong).collect(Collectors.toCollection(LinkedList::new));
        return numberLines;
    }

    /**
     * Writes a given String into the Class path output file
     *
     * @throws FileNotFoundException - if the file is not found
     */
    public static void writeStringIntoClassOutput(Class sourceClass, String output) throws FileNotFoundException {

        URL outputFileUrl = getFileUrl(sourceClass, OUTPUT_FILE);
        try (FileOutputStream outputStream = new FileOutputStream(new File(outputFileUrl.getPath()))) {
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

    /**
     * Reads input file into a list of Strings - chunks. Each chunk is separated by an empty line in the file.
     * The chunks are of inconsistent size.
     *
     * @return List of String chunks
     */
    public static List<String> readInputChunksSplitByEmptyLines(Class sourceClass) {

        List<String> inputLines = readClassInputIntoLines(sourceClass);
        inputLines.add(""); // make sure there is a last empty line

        List<String> inputChunks = new ArrayList<>();
        StringBuilder currentChunk = new StringBuilder();

        for (String line : inputLines) {
            if (line.isEmpty()) {
                inputChunks.add(currentChunk.toString());
                currentChunk = new StringBuilder();
            } else {
                currentChunk.append(String.format(" %s", line));
            }
        }
        return inputChunks;
    }

    /**
     * Reads input file into a list of Strings - chunks. Each chunk is separated by an empty line in the file.
     * The chunks are of inconsistent size.
     *
     * @return List of String chunks
     */
    public static List<List<String>> readInputChunksSplitByPattern(Class sourceClass, String pattern) {

        List<String> inputLines = readClassInputIntoLines(sourceClass);
        List<List<String>> inputChunks = new ArrayList<>();
        List<String> currentChunk = new ArrayList<>();

        for (String line : inputLines) {
            if (line.startsWith(pattern)) {
                if (!currentChunk.isEmpty())
                    inputChunks.add(currentChunk);
                currentChunk = new ArrayList<>();
            }
            currentChunk.add(line);
        }
        inputChunks.add(currentChunk);
        return inputChunks;
    }
}
