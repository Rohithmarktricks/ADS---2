import java.util.Scanner;
// import java.util.Iterator;
// import java.util.NoSuchElementException;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * numberOfVertices.
     *
     * @return     { number of vertices. }
     */
    int numberOfVertices();
    /**
     * Edges.
     *
     * @return     { number of edges. }
     */
    int numberOfEdges();
    /**
     * Adds an edge.
     *
     * @param      v     { numberOfVertice. }
     * @param      w     { numberOfVertice. }
     */
    void addEdge(int v, int w);
    /**
     * Adjacent vertices.
     *
     * @param      v     { vertex }
     *
     * @return     { Iterable<Integer> adjacent vertices, }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { numberOfVertice. }
     * @param      w     { numberOfVertice. }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * List of graphs.
 */
class GraphList implements Graph {
    /**
     * Number of vertices.
     */
    private int numberOfVertices;
    /**
     * Edges.
     */
    private int edges;
    /**
     * Bag of Integer adjacent elements.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      numberOfVertices1    The v 1
     */
    GraphList(final int numberOfVertices1) {
        this.numberOfVertices = numberOfVertices1;
        this.edges = 0;
        this.adj = (Bag<Integer>[]) new Bag[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * numberOfVerticesertices.
     *
     * @return     { Number of vertices }
     */
    public int numberOfVertices() {
        return this.numberOfVertices;
    }
    /**
     * Edges.
     *
     * @return     { Number of Edges. }
     */
    public int numberOfEdges() {
        return this.edges;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { numberOfVertice 1 }
     * @param      w     { numberOfVertice 2 }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * Adjacent vetices.
     *
     * @param      v     { numberOfVertice }
     *
     * @return     { Iterable<Integer> vertices. }
     */
    public Iterable<Integer> adj(final int v) {
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
    public String display(final String[] data) {
        String s = "";
        s += numberOfVertices + " vertices, " + edges + " edges" + '\n';
        if (edges == 0) {
            s += "No edges ";
        } else {
            for (int v = 0; v < numberOfVertices; v++) {
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
    /**
     * Number of vertices.
     */
    private int numberOfVertices;
    /**
     * Integer E (Number of edges).
     */
    private int edges2;
    /**
     * Integet array for matrix.
     */
    private int[][] matrix;
    /**
     * Constructs the object.
     *
     * @param      numberOfVertices1    The v 1
     */
    GraphMatrix(final int numberOfVertices1) {
        this.numberOfVertices = numberOfVertices1;
        this.edges2 = 0;
        this.matrix = new int[numberOfVertices][numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
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
    public Iterable<Integer> adj(final int v) {
        return null;
    }
    /**
     * numberOfVertice.
     *
     * @return     { vertices. }
     */
    public int numberOfVertices() {
        return this.numberOfVertices;
    }
    /**
     * Edges.
     *
     * @return     { Edges. }
     */
    public int numberOfEdges() {
        return this.edges2;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { numberOfVertice }
     * @param      w     { numberOfVertice }
     */
    public void addEdge(final int v, final int w) {
        if (!hasEdge(v, w) && v != w) {
            edges2++;
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
    public boolean hasEdge(final int v, final int w) {
        return (matrix[v][w] == 1);
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        s += numberOfVertices + " vertices, " + edges2 + " edges" + '\n';
        if (edges2 == 0) {
            s += "No edges ";
        } else {
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
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
    private Solution() {
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
                list.addEdge(Integer.parseInt(fun[0]),
                             Integer.parseInt(fun[1]));
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
