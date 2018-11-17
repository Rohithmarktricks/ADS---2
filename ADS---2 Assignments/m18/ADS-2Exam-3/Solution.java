import java.util.Scanner;
import java.util.TreeSet;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }

    /**
     *Main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * Reads the content in a File.
     */
    
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { BST }
     */
    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        String[] tempKeys = toReadFile(file);
        for (String str : tempKeys) {
            str = str.toLowerCase();
            if (!st.contains(str)) {
                st.put(str, 1);
            } else {
                st.put(str, st.get(str) + 1);
            }
        }

        return st;
    }

}

/**
 * Class for t 9.
 */
class T9 {

    /**
     * Dict of type TST.
     */
    private TST dict;
    /**
     * Constructs the object.
     *
     * @param      st    { BST }
     */
    public T9(BinarySearchST<String, Integer> st) {
        this.dict = new TST();
        Iterable iter = st.keys();
        for (Object it : iter) {
            this.dict.put((String)it, st.get((String)it));
        }
    }

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(String prefix) {
        return dict.keysWithPrefix(prefix);
    }

    /**
     * Mapper function.
     *
     * @param      inNum  In number
     *
     * @return     { char Array. }
     */
    public char[] mapper(String inNum) {
        int num = Integer.parseInt(inNum);
        if (num == 2) {
            char[] guess = {'a', 'b', 'c'};
            return guess;
        } else if (num == 3) {
            char[] guess = {'d', 'e', 'f'};
            return guess;
        } else if (num == 4) {
            char[] guess = {'g', 'h', 'i'};
            return guess;
        } else if (num == 5) {
            char[] guess = {'j', 'k', 'l'};
            return guess;
        } else if (num == 6) {
            char[] guess = {'m', 'n', 'o'};
            return guess;
        } else if (num == 7) {
            char[] guess = {'p', 'q', 'r', 's'};
            return guess;
        } else if (num == 8) {
            char[] guess = {'t', 'u', 'v'};
            return guess;
        }
        char[] guess = {'w', 'x', 'y', 'z'};
        return guess;
    }
    /**
     * Potential words.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { POssible Strings. }
     */
    public Iterable<String> potentialWords(String t9Signature) {
        String[] sample = t9Signature.split("");
        int len = t9Signature.length();
        String str = "";
        for (int i = 0; i < len; i++) {
            String reference = sample[i];

        }
        return null;
    }

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { limit. }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        MaxPQ<Integer> sortedfrequencies = new MaxPQ<Integer>();
        for (String each : words) {
            sortedfrequencies.insert((Integer) dict.get(each));
        }
        TreeSet<String> suggestions = new TreeSet<String>();
        for (int i = 0; i < k; i++) {
            int value = sortedfrequencies.delMax();
            for (String word : words) {
                if (value == (Integer) dict.get(word)) {
                    suggestions.add(word);
                }
            }
        }
        return suggestions;
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
