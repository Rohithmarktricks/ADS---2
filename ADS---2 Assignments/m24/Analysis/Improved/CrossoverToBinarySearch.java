import java.util.Random;
public class CrossoverToBinarySearch {

    public static void main(String[] args) {
        new CrossoverToBinarySearch().doExperiment();
    }

    private void doExperiment() {
        SequentialSeachSymbolTable<Integer, Integer> sequentialSearchSymbolTable = new SequentialSeachSymbolTable<>();
        BinarySearchSymbolTable<Integer, Integer> binarySearchSymbolTable = new BinarySearchSymbolTable<>();
        TestAnalysis ta = new TestAnalysis();

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
            long sequentialSearchRunningTime = System.nanoTime() - sequentialSearchStart;

            long binarySearchStart = System.nanoTime();
            binarySearchSymbolTable.get(searchKeyNotFound);
            long binarySearchRunningTime = System.nanoTime() - binarySearchStart;

            if (sequentialSearchRunningTime == binarySearchRunningTime) {
                continue;
            }

            if (sequentialSearchRunningTime >= binarySearchRunningTime * 10
                    && !tenX) {
                StdOut.println("Value of N for which binary search becomes 10 times faster than sequential search: " + i);
                // ta.limit = i;
                ta.doIT(i);
                // System.out.println(ta.limit);
                // System.out.println("Sequential steps: " + ta.seqSteps1 + " Binary Steps: " + ta.binSteps1 );
                System.out.println("Sequential : [" + ta.minSeqSteps + ", " + ta.maxSeqSteps + "]");
                System.out.println("Binary : [" + ta.minBinarySteps + ", " + ta.maxBinarySteps + "]");
                tenX = true;
            }
            if (sequentialSearchRunningTime >= binarySearchRunningTime * 100
                    && !hundredX) {
                StdOut.println("Value of N for which binary search becomes 100 times faster than sequential search: " + i);
                // ta.limit = i;
                ta.doIT(i);
                // System.out.println(ta.limit);
                // System.out.println("Sequential steps: " + ta.seqSteps2 + " Binary Steps: " + ta.binSteps2 );
                System.out.println("Sequential : [" + ta.minSeqSteps + ", " + ta.maxSeqSteps + "]");
                System.out.println("Binary : [" + ta.minBinarySteps + ", " + ta.maxBinarySteps + "]");
                hundredX = true;
            }
            if (sequentialSearchRunningTime >= binarySearchRunningTime * 1000
                    && !thousandX) {
                StdOut.println("Value of N for which binary search becomes 1000 times faster than sequential search: " + i);
                // ta.limit = i;
                ta.doIT(i);
                // System.out.println(ta.limit);
                // System.out.println("Sequential steps: " + ta.seqSteps3 + " Binary Steps: " + ta.binSteps3 );
                System.out.println("Sequential : [" + ta.minSeqSteps + ", " + ta.maxSeqSteps + "]");
                System.out.println("Binary : [" + ta.minBinarySteps + ", " + ta.maxBinarySteps + "]");
                thousandX = true;
            }

            if (thousandX) {
                break;
            }
        }

    }

}
