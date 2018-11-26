public class Experiment {
    public static void main(String[] args) {
        new Experiment().doExperiment();
    }
    private void doExperiment() {
        SequentialSearchST<Integer, Integer> sequentialSearchSymbolTable = new SequentialSearchST<>();
        BST<Integer, Integer> binarySearchSymbolTable = new BST<>();

        boolean tenX = false;
        boolean hundredX = false;
        boolean thousandX = false;

        int maxKey = 100000;

        for (int i = 1; i <= maxKey; i++) {

            sequentialSearchSymbolTable.put(i, i);
            binarySearchSymbolTable.put(i, i);

            int searchKeyNotFound = maxKey + 1;

            long sequentialSearchStart = System.nanoTime();
            sequentialSearchSymbolTable.get(searchKeyNotFound);
            int stepsSeq = sequentialSearchSymbolTable.getSteps();
            long sequentialSearchRunningTime = System.nanoTime() - sequentialSearchStart;

            long binarySearchStart = System.nanoTime();
            binarySearchSymbolTable.get(searchKeyNotFound);
            int stepsBin = binarySearchSymbolTable.getSteps();
            long binarySearchRunningTime = System.nanoTime() - binarySearchStart;

            if (stepsSeq == stepsBin) {
                continue;
            }

            if (stepsSeq >= stepsBin * 10
                    && !tenX) {
                StdOut.println("Value of N for which binary search becomes 10 times faster than sequential search: " + i);
                System.out.println("Number of steps involved");
                System.out.println("sequential search: " + sequentialSearchSymbolTable.getSteps());
                System.out.println("binary search: " + binarySearchSymbolTable.getSteps());
                System.out.println("-------------------------------------------------------------------------------------");
                sequentialSearchSymbolTable.steps = 0;
                binarySearchSymbolTable.steps = 0;
                tenX = true;
            }
            if (stepsSeq >= stepsBin * 100
                    && !hundredX) {
                StdOut.println("Value of N for which binary search becomes 100 times faster than sequential search: " + i);
                System.out.println("Number of steps involved");
                System.out.println("sequential search: " + sequentialSearchSymbolTable.getSteps());
                System.out.println("binary search: " + binarySearchSymbolTable.getSteps());
                System.out.println("-------------------------------------------------------------------------------------");
                sequentialSearchSymbolTable.steps = 0;
                binarySearchSymbolTable.steps = 0;
                hundredX = true;
            }
            if (stepsSeq >= stepsBin * 1000
                    && !thousandX) {
                StdOut.println("Value of N for which binary search becomes 1000 times faster than sequential search: " + i);
                System.out.println("Number of steps involved");
                System.out.println("sequential search: " + sequentialSearchSymbolTable.getSteps());
                System.out.println("binary search: " + binarySearchSymbolTable.getSteps());
                System.out.println("-------------------------------------------------------------------------------------");
                sequentialSearchSymbolTable.steps = 0;
                binarySearchSymbolTable.steps = 0;
                thousandX = true;
            }

            if (thousandX) {
                break;
            }
        }
    }
}
