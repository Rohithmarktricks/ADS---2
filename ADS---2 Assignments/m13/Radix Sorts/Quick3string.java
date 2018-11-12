/******************************************************************************
 *  Compilation:  javac Quick3string.java
 *  Execution:    java Quick3string < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *                https://algs4.cs.princeton.edu/51radix/shells.txt
 *
 *  Reads string from standard input and 3-way string quicksort them.
 *
 *  % java Quick3string < shell.txt
 *  are
 *  by
 *  sea
 *  seashells
 *  seashells
 *  sells
 *  sells
 *  she
 *  she
 *  shells
 *  shore
 *  surely
 *  the
 *  the
 *
 *
 ******************************************************************************/

/**
 *  The {@code Quick3string} class provides static methods for sorting an
 *  array of strings using 3-way radix quicksort.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/51radix">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class Quick3string {
    /**
     * Cutoff variable.
     */
    private static final int CUTOFF =  15;   // cutoff to insertion sort

    /**
     * Constructs the object.
     */
    private Quick3string() {
        //unused.
    }

    /**
     * Rearranges the array of strings in ascending order.
     *
     * @param a the array to be sorted
     */
    public static void sort(final String[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, 0);
        assert isSorted(a);
    }

    /**
     * Character At.
     *
     * @param      s     { parameter_description }
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private static int charAt(final String s, final int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }


    /**
     * Sorting.
     *
     * @param      a     { parameter_description }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { parameter_description }
     */
    private static void sort(final String[] a,
                             final int lo, final int hi, final int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if      (t < v) {
                exch(a, lt++, i++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }

        sort(a, gt + 1, hi, d);
    }

    /**
     * Insertion.
     *
     * @param      a     { parameter_description }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { parameter_description }
     */
    private static void insertion(final String[] a,
                                  final int lo, final int hi, final int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * Exchange.
     *
     * @param      a     { parameter_description }
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     */
    private static void exch(final String[] a, final int i, final int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    // DEPRECATED BECAUSE OF SLOW SUBSTRING EXTRACTION IN JAVA 7
    // private static boolean less(String v, String w, int d) {
    //    assert v.substring(0, d).equals(w.substring(0, d));
    //    return v.substring(d).compareTo(w.substring(d)) < 0;
    // }

    /**
     * checks if less.
     *
     * @param      v     { different elements. }
     * @param      w     { different elements. }
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private static boolean less(final String v, final String w, final int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }
    /**
     * Determines if sorted.
     *
     * @param      a     { parameter_description }
     *
     * @return     True if sorted, False otherwise.
     */
    private static boolean isSorted(final String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }


    // /**
    //  * Reads in a sequence of fixed-length strings from standard input;
    //  * 3-way radix quicksorts them;
    //  * and prints them to standard output in ascending order.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {

    //     // read in the strings from standard input
    //     String[] a = StdIn.readAllStrings();
    //     int n = a.length;

    //     // sort the strings
    //     sort(a);

    //     // print the results
    //     for (int i = 0; i < n; i++)
    //         StdOut.println(a[i]);
    // }
}
