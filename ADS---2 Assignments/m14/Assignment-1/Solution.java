import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * Main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] words = loadWords();
        String prefixWord = scan.nextLine();
        TST symbolTable = new TST();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                symbolTable.put(words[i].substring(j), i);
            }
        }

        Iterable<String> prefixwords =
            symbolTable.keysWithPrefix(prefixWord);
        for (String str : prefixwords) {
            System.out.println(str);
        }
    }

    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
