package Day2;

import java.util.List;

class Intcoder {

    private List<Integer> intcode;
    private int currentIndex;


    Intcoder(List<Integer> intcode, int pos1, int pos2) {

        this.intcode = intcode;
        this.currentIndex = 0;

        preProcessIntcode(pos1, pos2);
    }


    private void preProcessIntcode(int pos1, int pos2) {
        intcode.set(1, pos1);
        intcode.set(2, pos2);
    }


    void performIntcode() throws Exception {

        while (currentIndex < intcode.size()) {

            if (intcode.get(currentIndex) == 1) {
                addInts(currentIndex);
            }
            else if (intcode.get(currentIndex) == 2) {
                multiplyInts(currentIndex);
            }
            else if (intcode.get(currentIndex) == 99) {
                break;
            }
            else {
                throw new Exception("Something went wrong at index " + currentIndex);
            }
        }
    }


    // the adding method requires 3 parameters
    private void addInts(int index) {

        intcode.set(intcode.get(index + 3), (intcode.get(intcode.get(index + 1)) + intcode.get(intcode.get(index + 2))));
        updateCurrentIndex(4);
    }


    // the multiplication method requires 3 parameters
    private void multiplyInts(int index) {

        intcode.set(intcode.get(index + 3), (intcode.get(intcode.get(index + 1)) * intcode.get(intcode.get(index + 2))));
        updateCurrentIndex(4);
    }


    private void updateCurrentIndex(int jumpOverTo) {

        currentIndex += jumpOverTo;
    }


    List<Integer> getIntcode() {
        return intcode;
    }

    int getPosition0() {
        return intcode.get(0);
    }

}
