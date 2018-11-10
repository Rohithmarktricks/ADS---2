import java.util.Scanner;
/**
* Class for solution.
*/
public final class Solution {
    /**
    * Constructs the object.
    */
    private Solution() {
        // unused
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        int reference = edges;
        EdgeWeightedGraph eg = new EdgeWeightedGraph(vertices);
        while (reference > 0) {
            String[] values = scan.nextLine().split(" ");
            int source = Integer.parseInt(values[0]);
            int destination = Integer.parseInt(values[1]);
            int distance = Integer.parseInt(values[2]);
            Edge e = new Edge(source, destination, distance);
            eg.addEdge(e);
            reference--;
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(eg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths.
            // where two integers are given.
            // First is the source and
            // second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] places = scan.nextLine().split(" ");
            int home = Integer.parseInt(places[0]);
            Shortestpath sp = new Shortestpath(eg, home);
            int destiny = Integer.parseInt(places[1]);
            double result = sp.distTo(destiny);
            if (result == Double.POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(result);
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths,
            // where three integers are given.
            // First is the source and second is the via.
            // is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] place = scan.nextLine().split(" ");
            Shortestpath place1 =
                new Shortestpath(eg, Integer.parseInt(place[0]));
            Shortestpath viaPlace =
                new Shortestpath(eg, Integer.parseInt(place[1]));
            double distancePlace1 =
                place1.distTo(Integer.parseInt(place[1]));
            double distancePlace2 =
                viaPlace.distTo(Integer.parseInt(place[2]));
            String str = place[0] + " ";
            if (distancePlace1 == Double.POSITIVE_INFINITY
                    || distancePlace2 == Double.POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(distancePlace1
                                   + distancePlace2);
                for (Edge edge1
                        : place1.pathTo(Integer.parseInt(place[1]))) {
                    str += edge1.either() + " ";
                }
                int comparison = Integer.parseInt(place[1]);
                for (Edge edge2
                        : viaPlace.pathTo(Integer.parseInt(place[2]))) {
                    int temp = edge2.either();
                    if (comparison == temp) {
                        str += edge2.other(temp) + " ";
                    } else {
                        str = str + temp + " ";
                    }
                    comparison = temp;
                }
                System.out.println(str);
            }
            break;

        default:
            break;
        }

    }
}
