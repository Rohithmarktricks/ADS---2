import java.util.*;
public class WordNet {
    Map<Integer, Bag<String>> synsetts;
    Map<String, Bag<Integer>> ids;
    Digraph g;
    SAP sap;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws Exception {
        In inp = new In(synsets);

        synsetts = new HashMap<Integer, Bag<String>>();
        ids = new HashMap<String, Bag<Integer>>();

        for (String line : inp.readAllLines()) {

            String[] lines = line.split(",");
            Integer id = Integer.parseInt(lines[0]);
            String[] synset = lines[1].split(" ");

            synsetts.putIfAbsent(id, new Bag<String>());
            for (String word : synset) {
                synsetts.get(id).add(word);
                ids.putIfAbsent(word, new Bag<Integer>());
                ids.get(word).add(id);
            }
        }

        inp = new In(hypernyms);
        g = new Digraph(synsetts.size());
        for (String line : inp.readAllLines()) {
            String[] lines = line.split(",");
            for (int i = 1; i < lines.length; i++) g.addEdge(Integer.parseInt(lines[0]), Integer.parseInt(lines[i]));
        }
        sap = new SAP(g);
        int cnt = 0;
        for (int i = 0; i < g.V(); i++)
            if (g.outdegree(i) == 0) cnt++;
        if (cnt !=1 ) throw new Exception("Multiple roots");
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.hasCycle()) throw new Exception("Cycle detected");
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        // sap = new SAP(g);
        return ids.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return ids.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) { sap = new SAP(g); return sap.length(ids.get(nounA), ids.get(nounB));}

    // a synset (second field of synsetts.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        sap = new SAP(g);
        Integer id = sap.ancestor(ids.get(nounA), ids.get(nounB));
        String ancestor = "";
        for (String s : synsetts.get(id)) ancestor =  s + " " + ancestor;
        return ancestor.trim();
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
