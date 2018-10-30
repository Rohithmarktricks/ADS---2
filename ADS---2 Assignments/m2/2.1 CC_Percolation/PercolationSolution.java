class Percolation {
	private boolean[][] grid;
	private int bottom;
	private int top;
	private CC connectedComponents;
	Percolation(order) {
		top = 0;
		bottom = order * order + 1;
		grid = new boolean[order][order];
		for (int row = 0; row < order; row++) {
			for (int coloum = 0; coloum < order; coloum++) {
				grid[row][coloum] = false;
			}
		}
	}

}

public class Solution {
	Solution() {
		//unused.
	}

	public static void main(String[] args) {

	}
}



