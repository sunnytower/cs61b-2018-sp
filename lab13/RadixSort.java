import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    private static final int size = 256;
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxDigit = 0;
        for (String s : asciis) {
            maxDigit = Math.max(s.length(), maxDigit);
        }
        String[] res = asciis.clone();
//        for (String s : res) {
//            int diff = maxDigit - s.length();
//            while (diff > 0) {
//                s = "0" + s;
//                diff--;
//            }
//        }
        for (int i = maxDigit - 1; i >= 0; --i) {
            sortHelperLSD(res, i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        //size = 256 to hold all the asciis.
        int[] counts = new int[size + 1];
        for (String s : asciis) {
            int c = findValidIndex(index, s);
            counts[c]++;
        }

        int[] starts = new int[size + 1];
        int pos = 0;
        for (int i = 0; i < size + 1; ++i) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; ++i) {
            String s = asciis[i];
            int c = findValidIndex(index, s);
            int place = starts[c];
            sorted[place] = s;
            starts[c]++;
        }
        for (int i = 0; i < asciis.length; ++i) {
            asciis[i] = sorted[i];
        }
    }
    private static int findValidIndex(int index, String item) {
        if (index < item.length()) {
            return item.charAt(index) + 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        String[] s1 = new String[] {"356", "112", "904", "294", "209", "820", "394", "810"};
        String[] res = sort(s1);
        for (String s : res) {
            System.out.println(s + " ");
        }
    }
    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
