import java.util.Scanner;

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
	 * Main Function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int limit = Integer.parseInt(scan.nextLine());
		String[] compareList = new String[limit];
		for (int i = 0; i < limit; i++) {
			compareList[i] = scan.nextLine();
		}
		Quick3string.sort(compareList);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int j = 0; j < limit - 1; j++) {
			sb.append(compareList[j]);
			sb.append(", ");
		}
		sb.append(compareList[limit - 1]);
		sb.append("]");
		System.out.println(sb.toString());

	}
}