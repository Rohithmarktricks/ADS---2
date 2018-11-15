import java.util.*;
public class BoggleSolver {
	private TrieSET validDictionary;
	// public boolean[][] markedindices;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		this.validDictionary = new TrieSET();
		for (String str : dictionary) {
			this.validDictionary.add(str);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		Set<String> validWords = new HashSet<String>();
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] markedindices = new boolean[board.rows()][board.cols()];
				collectWords(board, i, j, markedindices, "", validWords);
			}
		}
		return new Bag<String>();
	}

	public void collectWords(BoggleBoard board, int row, int col, boolean[][]marked, String prefix, Set<String> set) {
		if (marked[row][col]) {
			return;
		}

		char letter = board.getLetter(row, col);
		String word = prefix;

		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}

		if (!validDictionary.hasPrefix(word)) {
			return;
		}

		if (word.length() >= 3 && validDictionary.contains(word)) {
			set.add(word);
		}

		marked[row][col] = true;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}

				if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
					collectWords(board, row + i, col + j, marked, word, set);
				}
			}
		}
		marked[row][col] = false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		int score = 0;
		if (validDictionary.contains(word)) {
			switch (word.length()) {
			case 0:
			case 1:
			case 2:
				score = 0;
				break;
			case 3:
			case 4:
				score = 1;
				break;
			case 5:
				score = 2;
				break;
			case 6:
				score = 3;
				break;
			case 7:
				score = 5;
				break;
			default:
				score = 11;
				break;
			}
		} else {
			return 0;

		}
		return score;
	}
}