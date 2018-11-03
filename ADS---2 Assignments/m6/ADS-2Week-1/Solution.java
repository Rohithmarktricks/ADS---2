import java.util.*;
class PageRank {

}

class WebSearch {

}


public class Solution {
	static Map<Integer, Bag<Integer>>information;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		information = new HashMap<Integer, Bag<Integer>>();
		int vertices = Integer.parseInt(scan.nextLine());
		for (int limit = 0; limit < vertices; limit++) {
			String[] num = scan.nextLine().split(" ");
			Integer id = Integer.parseInt(num[0]);
			information.putIfAbsent(id, new Bag<Integer>());
			int len = 1;
			while (len >= num.length) {
				information.get(id).add(Integer.parseInt(num[len]));
				len++;

			}
		}
		// Digraph graph = new Digraph(vertices);
		// read the first line of the input to get the number of vertices
		// for (int i = 0; i < vertices; i++) {
		// 	String[] elements = scan.nextLine().split(" ");}
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
