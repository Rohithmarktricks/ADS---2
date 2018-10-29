import java.util.Scanner;
/**GRAPHIMPLEMENTATION.**/
final class Solution {
    /**
     * @brief [brief description]
     * @details [long description]
     */
    private Solution() {
        //Unused.
    }
     /**
     * variable.
     */

    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(N)
     * @param args value
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        int num1;
        int num2;
        final int hundred = 100;
        String[] tokens = new String[hundred];
        String[] tokens1 = new String[hundred];
        switch (s1) {
        case "List":
            num1 = Integer.parseInt(s.nextLine());
            num2 = Integer.parseInt(s.nextLine());
            if (num1 == 0 || num2 == 0) {
                System.out.println(num1 + " vertices, " + num2 + " edges");
                System.out.println("No edges");
                return;
            }
            Graphs g = new Graphs(num1);
            tokens = s.nextLine().split(",");
            for (int i = 0; i < num1; i++) {
                g.addvertices(tokens[i]);
            }
            for (int i = 0; i < num2; i++) {
                tokens1 = s.nextLine().split(" ");
                g.addEdge(Integer.parseInt(tokens1[0]),
                    Integer.parseInt(tokens1[1]));
            }
            if (tokens1[0].equals(tokens1[1])) {
                break;
            } else {
            System.out.println(g);
        }
            break;
    case "Matrix":
                 num1 = Integer.parseInt(s.nextLine());
                num2 = Integer.parseInt(s.nextLine());
                if (num1 == 0 || num2 == 0) {
                    System.out.println(num1 + " vertices, " + num2 + " edges");
                    System.out.println("No edges");
                    return;
                }
                Matrix gp = new Matrix(num1);
                tokens = s.nextLine().split(",");
                for (int i = 0; i < num1; i++) {
                    gp.addmat(tokens[i]);
                }
                for (int i = 0; i < num2; i++) {
                    tokens1  = s.nextLine().split(" ");
                    gp.addEdge(Integer.parseInt(tokens1[0]),
                        Integer.parseInt(tokens1[1]));
                } if (tokens1[0].equals(tokens1[1])) {
                    break;
                } else {
                System.out.println(gp);
            }
            default:
            break;
        }

}
}



