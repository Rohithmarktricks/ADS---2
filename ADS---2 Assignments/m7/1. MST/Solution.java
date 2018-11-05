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
	 * Main function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph edGraph = new EdgeWeightedGraph(vertices);
		while (edges > 0) {
			String[] tokens = scan.nextLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int weight = Integer.parseInt(tokens[2]);
			Edge edge = new Edge(a, b, weight);
			edGraph.addEdge(edge);
			edges--;
		}
		LazyPrimMST mst = new LazyPrimMST(edGraph);
		System.out.printf("%.5f\n", mst.weight());

	}
}