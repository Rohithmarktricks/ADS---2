import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class TestAnalysis {
	public static int limit = 0;
	public static int seqSteps1 = 0;
	public static int binSteps1;
	public static int seqSteps2;
	public static int binSteps2;
	public static int seqSteps3;
	public static int binSteps3;
	public static int minBianrySteps;
	public static int maxBinarySteps;
	public static int minSeqSteps;
	public static int maxSeqSteps;
	private TestAnalysis() {
		//unused.
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		SequentialSearchST<Integer, Integer> st = new SequentialSearchST<Integer, Integer>();
		BST<Integer, Integer> bt = new BST<Integer, Integer>();
		//Insertion of random values in the symbol tables.
		// Let us insert limit random values in the symbol table.
		// Random case.
		// System.out.println("------------------------------");
		// System.out.println("Step 1 :SymbolTables created.");
		// System.out.println("------------------------------");
		// System.out.print("Enter the limit : ");
		// int limit = 0;
		int[] keysArr = new int[limit];
		for (int i = 0; i < limit; i++) {
			int key = rand.nextInt(limit) + 1;
			int value = rand.nextInt(limit) + 1;
			keysArr[i] = key;
			st.put(key,  value);
			bt.put(key,  value);
		}
		// System.out.println();
		// System.out.println("Step 2 :Key-Value pairs inserted.");
		// System.out.println("---------------------------");
		// int keyNotFound = limit + 1;
		//For average case:
		//Sequential search : search takes n.
		//Binary search : search takes n.
		//Lets search for a random elements.(Say 50 random elements in the table and calculate Min and Max values.
		int[] stepsSequential = new int[50];
		int[] stepsBinary = new int[50];
		for (int j = 0 ; j < 50; j++) {
			int targetIndex = rand.nextInt(limit);
			int target = keysArr[targetIndex];

			Integer tempResult = st.get(target);
			stepsSequential[j] = st.getSeqSteps();
			st.getSteps = 0;

			Integer tempResult2 = bt.get(target);
			stepsBinary[j] = bt.getBSTSteps();
			bt.steps = 0;

			if ((stepsSequential[j] >= (10 * stepsBinary[j]) - 2)  && (stepsSequential[j] <= (10 * stepsBinary[j]) + 2)) {
				// System.out.println("Found 10X");
				// System.out.println("Target Key is : " + target);
				// System.out.println("Sequential steps: " + stepsSequential[j] + " and " + "Binary steps: " + stepsBinary[j] );
				 seqSteps1 = stepsSequential[j];
				 binSteps1 = stepsBinary[j];
			} else if ((stepsSequential[j] >= (100 * stepsBinary[j]) - 2)  && (stepsSequential[j] <= (100 * stepsBinary[j]) + 2)) {
				// System.out.println("Sequential steps: " + stepsSequential[j] + " and " + "Binary steps: " + stepsBinary[j] );
				// System.out.println("Found 100X");
				// System.out.println("Target Key is : " + target);
				 seqSteps2 = stepsSequential[j];
				 binSteps2 = stepsBinary[j];
			} else if ((stepsSequential[j] >= (100 * stepsBinary[j]) - 2)  && (stepsSequential[j] <= (100 * stepsBinary[j]) + 2)) {
				// System.out.println("Found 1000X");
				// System.out.println("Target Key is : " + target);
				// System.out.println("Sequential steps: " + stepsSequential[j] + " and " + "Binary steps: " + stepsBinary[j] );
				 seqSteps3 = stepsSequential[j];
				 binSteps3 = stepsBinary[j];
			}
		}

		Arrays.sort(stepsBinary);
		Arrays.sort(stepsSequential);
		// System.out.println("-------------");
		// System.out.println("steps Sorted");
		// System.out.println("---------------");
		// System.out.println();
		// System.out.println("**Binary steps required** : " + "[Min :" + stepsBinary[0] + ", " + "Max :" + stepsBinary[stepsBinary.length - 1] + "]");
		// System.out.println("--------------------------------------------");
		// System.out.println("**Sequential steps required** : " + "[Min :" + stepsSequential[0] + ", " + "Max :" + stepsSequential[stepsSequential.length - 1] + "]");
		// System.out.println("--------------------------------------------");
		 minBianrySteps = stepsBinary[0];
		 maxBinarySteps = stepsBinary[stepsBinary.length - 1];
		 minSeqSteps = stepsSequential[0];
		 maxSeqSteps = stepsSequential[stepsSequential.length - 1];
	}
}