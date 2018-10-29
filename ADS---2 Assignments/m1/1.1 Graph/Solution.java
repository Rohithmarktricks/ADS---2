import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Interface for graph.
 */
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
/**
 * List of graphs.
 */
class GraphList implements Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	/**
	 * Constructs the object.
	 *
	 * @param      V1    The v 1
	 */
	GraphList(int V1) {
		this.V = V1;
		this.E = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	/**
	 * Vertices.
	 *
	 * @return     { Number of vertices }
	 */
	public int V() {
		return this.V;
	}
	/**
	 * Edges.
	 *
	 * @return     { Number of Edges. }
	 */
	public int E() {
		return this.E;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v     { Vertex 1 }
	 * @param      w     { Vertex 2 }
	 */
	public void addEdge(int v, int w) {
		if (v == w) {
			return;
		}
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	/**
	 * Adjacent vetices.
	 *
	 * @param      v     { Vertex }
	 *
	 * @return     { Iterable<Integer> vertices. }
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	/**
	 * Determines if it has edge.
	 *
	 * @param      v     { vertex 1}
	 * @param      w     { vertex 2}
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(final int v, final int w) {
		int count = 0;
		for (int i : adj[v]) {
			if (i == w) {
				count += 1;
				break;
			}
		}
		for (int i : adj[w]) {
			if (i == v) {
				count += 1;
				break;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}
	/**
	 * Display Function.
	 *
	 * @param      data  The data
	 *
	 * @return     { String. }
	 */
	public String display(String[] data) {
		String s = "";
		s += V + " vertices, " + E + " edges" + '\n';
		if (E == 0) {
			s += "No edges ";
		} else {
			for (int v = 0; v < V; v++) {
				s += data[v] + ": ";
				for (int w : adj[v]) {
					s += data[w] + " ";
				}
				s += '\n';
			}
		}
		return s.substring(0, s.length() - 1);
	}

}
/**
 * Class for graph matrix.
 */
class GraphMatrix implements Graph {
	private int V;
	private int E;
	private int[][] matrix;
	/**
	 * Constructs the object.
	 *
	 * @param      V1    The v 1
	 */
	GraphMatrix(int V1) {
		this.V = V1;
		this.E = 0;
		this.matrix = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	/**
	 * adjacent vertices.
	 *
	 * @param      v     { vertex v }
	 *
	 * @return     { Iterable<Integer> vertices. }
	 */
	public Iterable<Integer> adj(int v) {
		return null;
	}
	/**
	 * Vertex.
	 *
	 * @return     { vertices. }
	 */
	public int V() {
		return this.V;
	}
	/**
	 * Edges.
	 *
	 * @return     { Edges. }
	 */
	public int E() {
		return this.E;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v     { Vertex }
	 * @param      w     { Vertex }
	 */
	public void addEdge(int v, int w) {
		if (!hasEdge(v, w) && v != w) {
			E++;
		}
		matrix[v][w] = 1;
		matrix[w][v] = 1;
	}
	/**
	 * Determines if it has edge.
	 *
	 * @param      v     { vertex }
	 * @param      w     { vertex }
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(int v, int w) {
		return (matrix[v][w] == 1);
	}
	/**
	 * Returns a string representation of the object.
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
		String s = "";
		s += V + " vertices, " + E + " edges" + '\n';
		if (E == 0) {
			s += "No edges ";
		} else {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					s += matrix[i][j] + " ";
				}
				s = s.substring(0, s.length());
				s += ('\n');
			}
		}

		return s.substring(0, s.length() -  1);
	}
}
/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	public Solution() {
	}
	/**
	 * { function_description }.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		int v = Integer.parseInt(scan.nextLine());
		int e = Integer.parseInt(scan.nextLine());
		int temp = e;
		String[] data = scan.nextLine().split(",");
		if (s.equals("List")) {
			GraphList list = new GraphList(v);
			while (temp > 0) {
				String[] fun = scan.nextLine().split(" ");
				list.addEdge(Integer.parseInt(fun[0]), Integer.parseInt(fun[1]));
				temp--;
			}
			System.out.println(list.display(data));
		} else if (s.equals("Matrix")) {
			temp = e;
			GraphMatrix sol = new GraphMatrix(v);
			while (temp > 0) {
				String[] fun = scan.nextLine().split(" ");
				sol.addEdge(Integer.parseInt(fun[0]), Integer.parseInt(fun[1]));
				temp--;
			}
			System.out.println(sol);
		}
	}
}



