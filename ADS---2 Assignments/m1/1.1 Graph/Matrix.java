/**graphimplementation.**/
final class Matrix {
    /**
     * variable.
     */
    private final int vertices;
    /**
     * variable.
     */
    private int edges;
    /**
     * variable.
     */
    private int[][] adj;
    /**
     * variable.
     */
    private String[] matrices;
    /**
     * variable.
     */
    private int size;
    /**
     * @param vert value
     */
     Matrix(final int vert) {
        if (vert < 0) {
            throw new IllegalArgumentException(
            "Too few vertices");
        }
        this.vertices = vert;
        this.edges = 0;
        this.adj = new int[vert][vert];
        this.matrices = new String[vert];
        this.size = 0;
    }
    /**
     * @brief [brief description]
     * @details [long description]#
     * Time complexity is O(1)
     * @return value
     */
      public int vertex() {
        return vertices;
      }
      /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @return value
     */
    public int edge() {
        return edges;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param v value
     * @param w value
     */
    public void addEdge(final int v, final int w) {
    if (v == w) {
        System.out.println(vertices + " vertices, " + edges + " edges");
        System.out.println("No edges");
        return;
    }
        if (adj[v][w] == 0) {
            edges++;
        }
        adj[v][w] = 1;
        adj[w][v] = 1;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param s value
     */
    public void addmat(final String s) {
        matrices[size++] = s;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(N^2)
     * @return value
     */
 public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            //s.append(matrices[v] + ": ");
            for (int w : adj[v]) {
                if (w == 1) {
                s.append(1 + " ");
            } else {
                s.append(0 + " ");
            }
            }
            s.append("\n");
        }
        return s.toString();
    }

}

