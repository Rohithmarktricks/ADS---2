import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
  String[] root;
  /**
   * Constructs the object.
   */
  private Solution() {
    // unused
  }

  private int getIndex(String str) {
    int index = 0;
    for (int i = 0; i < root.length; i++) {
      if (str.equals(root[i])) {
        index = i;
      }
    }
    return index;
  }


  /**
   * Main method.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    Solution sol = new Solution();
    Scanner scan = new Scanner(System.in);
    String[] values = scan.nextLine().split(" ");
    int vertices = Integer.parseInt(values[0]);
    int edges = Integer.parseInt(values[1]);
    String[] tokens = scan.nextLine().split(" ");
    sol.root = new String[tokens.length];
    for (int j = 0; j < tokens.length; j++) {
      sol.root[j] = tokens[j];
    }
    EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);
    while (edges > 0) {
      String[] token = scan.nextLine().split(" ");
      int first = sol.getIndex(token[0]);
      int second = sol.getIndex(token[1]);
      int weight = Integer.parseInt(token[2]);
      Edge e = new Edge(first, second, weight);
      eg.addEdge(e);
      edges--;
    }
    int i = Integer.parseInt(scan.nextLine());
    while (i > 0) {
      String[] tokens1 = scan.nextLine().split(" ");
      int home = sol.getIndex(tokens1[0]);
      SP l = new SP(eg, home);
      int destination = sol.getIndex(tokens1[1]);
      System.out.printf("%d\n", (int) l.distTo(destination));
      i--;
    }
  }
}
