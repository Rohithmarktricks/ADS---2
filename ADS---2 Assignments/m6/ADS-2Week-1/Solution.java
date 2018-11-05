import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
// import java.io.*;

/**
 * Class for page rank.
 */
class PageRank {
	private static Double[] pageRankList;
	static Digraph g;
	static double value;
	static Digraph rev;
	PageRank(Digraph g) {
		rev = g.reverse();
		value = g.V();
		this.g = g;
		pageRankList = new Double[g.V()];
		for (int i = 0; i < g.V(); i++) {
			pageRankList[i] = 1.0 / (g.V());
		}
		for (int k = 0; k < 1000; k++) {
			Double[] calculatedListValues = prcalculation(pageRankList, g);
			/*			if (Arrays.toString(pageRankList).equals(Arrays.toString(calculatedListValues))) {
							pageRankList = calculatedListValues;
							break;
						} else {*/
			pageRankList = calculatedListValues;
			//}
		}





	}
	/**
	 * PageRank Calculation.
	 *
	 * @param      list  The list
	 * @param      g     { Digraph graph }
	 *
	 * @return     { value of pagerank }
	 */
	public Double[] prcalculation(Double[] list, Digraph g) {
		Double[] l = new Double[g.V()];
		for (int i = 0; i < g.V(); i++) {
			Double pr = 0.0;
			for (int j = 0; j < g.V(); j++) {
				for (int each : g.adj(j)) {
					if (each == i) {
						pr += (double)(list[j] / g.outdegree(j));
					}
				}
			}
			l[i] = pr;
		}
		return l;
	}
	/**
	 * Gets the Page rank.
	 *
	 * @param      v     { parameter_description }
	 *
	 * @return     The pr.
	 */
	public static Double getPR(int x) {
		double prr = 1.0 / value;
		return helper(prr, x);

		// return pageRankList[v];
	}
	public static double helper(double pr, int x) {
		double[] prs = new double[g.V()];
		for (int i = 0; i < prs.length; i++) {
			prs[i] = pr;
		}
		double[] new_prs = new double[g.V()];
		for (int i = 0; i < prs.length; i++) {
			new_prs[i] = prs[i];
		}
		for (int iter = 0; iter < 1000; iter++) {
			for (int i = 0; i < prs.length; i++) {
				prs[i] = new_prs[i];
			}
			for (int j = 0; j < g.V(); j++) {
				Iterable<Integer> it = rev.adj(j);
				double cal = 0.0;
				for (Integer in : it) {
					cal += prs[in] / g.outdegree(in);
				}
				new_prs[j] = cal;
			}
		}
		return new_prs[x];
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < pageRankList.length; i++) {
			str += i + " - " + pageRankList[i] + "\n";
		}
		return str;
	}
}

class WebSearch {
	Hashtable<String, Bag<Integer>> wordMaxID;
	public WebSearch(PageRank pr, String fileName) {
		In inp = new In(fileName);
		wordMaxID = new Hashtable<String, Bag<Integer>>();

		for (String webPage : inp.readAllLines()) {
			String[] entries = webPage.split(":");
			Integer webID = Integer.parseInt(entries[0]);
			String[] webPageElements = entries[1].split(" ");

			for (String str : webPageElements) {
				wordMaxID.putIfAbsent(str, new Bag<Integer>());
				wordMaxID.get(str).add(webID);
			}
		}
	}
	public int iAmFeelingLucky(String query) {
		if (wordMaxID.containsKey(query)) {
			Bag<Integer> sample = wordMaxID.get(query);
			double maxx = 0.0;
			int index = 0;
			for (Integer i : sample) {
				if (maxx < PageRank.getPR(i)) {
					index = i;
				}
			}
			return index;
		}
		return -1;
	}

}

/**
 * Class for solution.
 */
public class Solution {
	/**
	 * Constructs the object.
	 */
	Solution() {

	}
	/**
	 * Main function.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
		int n = Integer.parseInt(sc.nextLine());
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		Digraph graph = new Digraph(n);
		for (int i = 0; i < n; i++) {
			String[] tokens = sc.nextLine().split(" ");
			for (int k = 1 ; k < tokens.length ; k++) {
				graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[k]));
				// System.out.println(graph);
			}
		}
		System.out.println(graph);
		for (int i = 0; i < graph.V(); i++) {
			if (graph.outdegree(i) == 0) {
				for (int k = 0; k < graph.V(); k++) {
					if (i != k) {
						graph.addEdge(i, k);
					}
				}
			}
		}
		// Create page rank object and pass the graph object to the constructor
		PageRank pr = new PageRank(graph);
		// print the page rank object
		for(int i = 0; i<n; i++){
			System.out.println(i+"-"+pr.getPR(i));
		}
		// System.out.println(pr);
		// This part is only for the final test case
		//
		// File path to the web content
		String file = "WebContent.txt";
		WebSearch wbs = new WebSearch(pr, file);
		while (sc.hasNext()) {
			String[] queriesList = sc.nextLine().split("=");
			System.out.println(wbs.iAmFeelingLucky(queriesList[1]));
		}

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
