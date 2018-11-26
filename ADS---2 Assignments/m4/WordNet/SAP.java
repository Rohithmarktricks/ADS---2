import java.util.*;

public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    Digraph G ;
    Integer dist, ancestor;
    public SAP(Digraph G) {
        this.G = G;
        dist = Integer.MAX_VALUE;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(G, w);
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfs1.hasPathTo(i)) set1.add(i);
            if (bfs2.hasPathTo(i)) set2.add(i);
        }
        // ArrayList<Integer> ancestors = new ArrayList<>();
        set1.retainAll(set2);
        for (Integer e : set1)
            if (dist >= bfs1.distTo(e) + bfs2.distTo(e)) {
                dist = bfs1.distTo(e) + bfs2.distTo(e);
                ancestor = e;
            }
        if (dist == Integer.MAX_VALUE) return -1;
        return dist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(G, w);
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfs1.hasPathTo(i)) set1.add(i);
            if (bfs2.hasPathTo(i)) set2.add(i);
        }
        // ArrayList<Integer> ancestors = new ArrayList<>();
        set1.retainAll(set2);
        // System.out.println(set1);
        for (Integer e : set1)
            if (dist >= bfs1.distTo(e) + bfs2.distTo(e)) {
                dist = bfs1.distTo(e) + bfs2.distTo(e);
                ancestor = e;
            }
        if (dist == Integer.MAX_VALUE) return -1;
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer i : v)
            for (Integer j : w) {
                int tdist = length(i,j);
                if (tdist!=-1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        if (dist == Integer.MAX_VALUE) return -1;
        return dist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer i : v)
            for (Integer j : w) {
                int tdist = length(i,j);
                if (tdist!=-1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        if (dist == Integer.MAX_VALUE) return -1;
        return ancestor;
    }

    // do unit testing of this class
    // public static void main(String[] args)
}