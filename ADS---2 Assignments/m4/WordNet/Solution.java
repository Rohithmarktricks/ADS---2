import java.util.*;
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			WordNet wordNet = new WordNet("Files/"+sc.nextLine(), "Files/"+sc.nextLine());
			switch (sc.nextLine()) {
				case "Graph": System.out.println(wordNet.g);
							  break;
				case "Queries":
					while(sc.hasNextLine()){
						String[] tokens = sc.nextLine().split(" ");
						Integer dist = wordNet.distance(tokens[0], tokens[1]);
						String ancestor = wordNet.sap(tokens[0], tokens[1]);
						System.out.println("distance = " + dist + ", " + "ancestor = " + ancestor);
					}
			}
		}
		catch (NullPointerException f) {
			System.out.println("IllegalArgumentException");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}