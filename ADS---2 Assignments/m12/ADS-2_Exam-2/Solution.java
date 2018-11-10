import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int cities = Integer.parseInt(scan.nextLine());
		int routes = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph eg = new EdgeWeightedGraph(cities);
		Edge e;
		while (cities > 0) {
			String[] testcases = scan.nextLine().split(" ");
			int source = Integer.parseInt(testcases[0]);
			int destination = Integer.parseInt(testcases[1]);
			int distance = Integer.parseInt(testcases[2]);
			e = new Edge(source, destination, distance);
			eg.addEdge(e);
			cities--;
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			for (Edge ed : eg.edges()) {
				System.out.println(ed);
			}
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] pair = scan.nextLine().split(" ");
			int home = Integer.parseInt(pair[0]);
			SP l = new SP(eg, home);
			int destination = Integer.parseInt(pair[1]);
			if (l.distTo(destination) != 00.00) {
				System.out.printf("%.1f\n", l.distTo(destination));
			}else {
				System.out.println("No Path Found.");
			}

			// System.out.println("No Path Found.");

			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}