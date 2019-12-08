package Day08;

import java.util.*;

class PasswordReader {

    private static final int CORRUPTION_DIGIT_1 = '1';
    private static final int CORRUPTION_DIGIT_2 = '2';

    private List<List<Character>> layers;
    private int pixelsWide;
    private int pixelsTall;


    PasswordReader(List<Character> input, int pixelsWide, int pixelsTall) {

        this.pixelsWide = pixelsWide;
        this.pixelsTall = pixelsTall;
        this.layers = decodeImageIntoLayers(input, pixelsWide, pixelsTall);
    }


    private List<List<Character>> decodeImageIntoLayers(List<Character> input, int pixelsWide, int pixelsTall) {

        // find the number of pixels in one layer
        int layerLength = pixelsWide * pixelsTall;

        // split the input into lists of layers
        return splitIntoListsByLength(input, layerLength);
    }


    private List<List<Character>> splitIntoListsByLength(List<Character> toSplit, int length) {

        List<List<Character>> split = new ArrayList<>();

        int fromIndex = 0;
        int toIndex = length;  // high endpoint of a sublist is exclusive

        // split the given list into sublists of the specified length
        while (toIndex <= toSplit.size()) {

            split.add(toSplit.subList(fromIndex, toIndex));

            fromIndex += length;
            toIndex += length;
        }
        return split;
    }


    int calculateCorruptionValue(char fewest) {

        // find the layer that has the least of given digits
        List<Character> layerWithFewest = findLayerWithFewestOf(fewest);

        // the frequency of 1 digits multiplied by the frequency of 2 digits
        return Collections.frequency(layerWithFewest, CORRUPTION_DIGIT_1) *
                Collections.frequency(layerWithFewest, CORRUPTION_DIGIT_2);

    }


    private List<Character> findLayerWithFewestOf(char fewest) {

        // assign the min to the length of a row
        int min = pixelsWide * pixelsTall;

        List<Character> targetLayer = new ArrayList<>();

        // go over each layer and calculate the frequency of the given fewest element
        for (List<Character> layer : layers){

            int fewestCount = Collections.frequency(layer, fewest);

            // if the current frequency is smaller than the current minimum, update them
            if (fewestCount < min) {
                min = fewestCount;
                targetLayer = layer;
            }
        }
        return targetLayer;
    }


    List<List<Character>> getNonTransparentRows() {

        // merge layers
        List<Character> nonTrasparentLayer = mergeLayers();

        // split the layer into rows of N given pixels
        return splitIntoListsByLength(nonTrasparentLayer, pixelsWide);
    }


    private List<Character> mergeLayers() {

        List<Character> nonTransparent = new ArrayList<>();

        // go over each pixel in each layer, find the first non-transparent one
        // and add it to the final non-transparent layer
        for (int pixel = 0; pixel < (pixelsWide * pixelsTall); pixel++) {

            for (List<Character> layer : layers) {

                if (layer.get(pixel) != '2') {
                    nonTransparent.add(layer.get(pixel));
                    break;
                }
            }
        }
        return nonTransparent;
    }
}
