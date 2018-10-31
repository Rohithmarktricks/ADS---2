import java.util.Scanner;

class Graph {
	private int ver;
	private int edg;
	private Bag<Integer>[] adj;

	Graph(final int numberOfVertices) {
		this.ver = numberOfVertices;
		this.edg = 0;
		this.adj = (Bag<Integer>[])new Bag[numberOfVertices];
		for (int i = 0; i < ver; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public int numberOfVertices() {
		return this.ver;
	}
	public int numberOfEdges() {
		return this.edg;
	}
	public void addEdge(int v, int w) {
		edg++;
		adj[v].add(w);
	}

	public Iterable<Integer> adj(final int v) {
		return adj[v];
	}

	public boolean isCyclic() {
		boolean[] visited = new boolean[ver];
		boolean[] recStack = new boolean[ver];


		for (int ind = 0; ind < ver; ind++) {
			if (isCyclicUtil(ind, visited, recStack)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
		if (recStack[i]) {
			return true;
		}
		if (visited[i]) {
			return true;
		}
		visited[i] = true;
		recStack[i] = true;
		for (Integer inti : adj[i]) {
			if (isCyclicUtil(inti, visited, recStack)) {
				return true;
			}
		}
		recStack[i] = false;

		return false;

	}
}


public class Solution {
	Solution() {
		//unused.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertex = scan.nextInt();
		int edges = scan.nextInt();
		Graph graph = new Graph(vertex);
		while (edges > 0) {
			// String[] vertices = scan.nextLine().split(" ");
			// System.out.println(Integer.parseInt(vertices[0]) + ", " + Integer.parseInt(vertices[2]));
			graph.addEdge(scan.nextInt(), scan.nextInt());
			edges--;
		}
		if (graph.isCyclic()) {
			System.out.println("Cycle exists.");
		} else {
			System.out.println("Cycle doesn't exists.");
		}

	}
}