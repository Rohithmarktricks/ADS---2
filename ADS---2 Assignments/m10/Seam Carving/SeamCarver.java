import java.util.Arrays;
import java.awt.Color;

public class SeamCarver {
	// create a seam carver object based on the given picture
	private static final int BOUNDARY = 1000;
	private Picture pic;
	public SeamCarver(Picture picture) {
		this.pic = new Picture(picture);
	}
	// current picture
	public Picture picture() {
		return pic;
	}
	// width of current picture
	public int width() {
		return pic.width();
	}

	// height of current picture
	public int height() {
		return pic.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		// if (x < 0 || x >= pic.width()
		//         || y < 0 || y >= pic.height()) {
		// 	throw new IndexOutOfBoundsException("Invalid position.");
		// }

//		it is located on the boundary
		if (x == 0 || x == pic.width() - 1
		        || y == 0 || y == pic.height() - 1) {
			return SeamCarver.BOUNDARY;
		}

//		calculate gradient on x axis
		Color c1 = pic.get(x - 1, y);
		Color c2 = pic.get(x + 1, y);
		int rx = c2.getRed() - c1.getRed();
		int gx = c2.getGreen() - c1.getGreen();
		int bx = c2.getBlue() - c1.getBlue();
		int dx = rx * rx + gx * gx + bx * bx;

//		calculate gradient on y axis
		c1 = pic.get(x, y - 1);
		c2 = pic.get(x, y + 1);
		int ry = c2.getRed() - c1.getRed();
		int gy = c2.getGreen() - c1.getGreen();
		int by = c2.getBlue() - c1.getBlue();
		int dy = ry * ry + gy * gy + by * by;

		return (dx + dy);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		int h = pic.height();
		int w = pic.width();
		int total = h * w;
		double[] distTo = new double[total + 2];
		int[] edgeTo = new int[total + 2];
		Arrays.fill(distTo, Double.MAX_VALUE);

		//For source vertex.
		distTo[0] = 0;
		edgeTo[0] = -1;

		for (int i = 0; i <= total; i++) {
			//if i is source.
			if (i == 0) {
				for (int j = 0; j <= h; j++) {
					int index = j + 1;
					double cost = this.energy(0, j);
					if (distTo[0] + cost < distTo[index]) {
						distTo[index] = distTo[0] + cost;
						edgeTo[index] = 0;
					}
				}
				continue;
			}

			int col = (i - 1) / h;
			int row = (i - 1) % h;
//			current vertex is in last row
			if (col == w - 1) {
//				there is zero cost from current vertex to sink
				double cost = 0;
				if (distTo[i] + cost < distTo[total + 1]) {
					distTo[total + 1] = distTo[i];
					edgeTo[total + 1] = i;
				}
				continue;
			}
//			other cases
			for (int j = -1; j <= 1; j++) {
				int nrow = row + j;
				int ncol = col + 1;
				if (nrow < 0 || nrow >= h) continue;
				int index = nrow + ncol * h + 1;
				double cost = this.energy(ncol, nrow);
				if (distTo[i] + cost < distTo[index]) {
					distTo[index] = distTo[i] + cost;
					edgeTo[index] = i;
				}
			}
		} // end for loop

		int[] result = new int[w];
		int current = total + 1;
		int ptr = w - 1;
//		exclude source vertex, whose index is 0
		while (edgeTo[current] > 0) {
			result[ptr] = (edgeTo[current] - 1) % h;
			ptr--;
			current = edgeTo[current];
//			should not happen
			if (ptr < -1) {
				System.err.println("Error occurs");
			}
		}
		return result;
	} // end method findHorizontalSeam

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int h = pic.height();
		int w = pic.width();
		int total = h * w;
//		need to add one source and one sink
//		to simplify the calculation of shortest path
		double[] distTo = new double[total + 2];
		int[] edgeTo = new int[total + 2];
		Arrays.fill(distTo, Double.MAX_VALUE);

//		set parameters for source vertex
		distTo[0] = 0;
		edgeTo[0] = -1;
//		relax edges in topological order
		for (int i = 0; i <= total; i++) {
//			current vertex is source
			if (i == 0) {
				for (int j = 0; j < w; j++) {
					double cost = this.energy(j, 0);
					int index = j + 1;
					if (distTo[0] + cost < distTo[index]) {
						distTo[index] = distTo[0] + cost;
						edgeTo[index] = 0;
					}
				} // end for loop
				continue;
			}
			int row = (i - 1) / w;
			int col = (i - 1) % w;
//			current vertex is in last row
			if (row == h - 1) {
//				there is zero cost from current vertex to sink
				double cost = 0;
//				StdOut.println("Disto0: "+distTo[i]);
				if (distTo[i] + cost < distTo[total + 1]) {
					distTo[total + 1] = distTo[i];
					edgeTo[total + 1] = i;
				}
				continue;
			}
//			other cases
			for (int j = -1; j <= 1; j++) {
				int nrow = row + 1;
				int ncol = col + j;
				if (ncol < 0 || ncol >= w) continue;
				int index = nrow * w + ncol + 1;
				double cost = this.energy(ncol, nrow);
				if (distTo[i] + cost < distTo[index]) {
					distTo[index] = distTo[i] + cost;
					edgeTo[index] = i;
//					StdOut.println("Updated: "+distTo[index]);
				}
			}
		} // end for loop

		int[] result = new int[h];
		int current = total + 1;
		int ptr = h - 1;
//		exclude source vertex, whose index is 0
		while (edgeTo[current] > 0) {
			result[ptr] = (edgeTo[current] - 1) % w;
			ptr--;
			current = edgeTo[current];
//			should not happen
			if (ptr < -1) {
				System.err.println("Error occurs");
			}
		}
		return result;
	} // end method findVerticalSeam

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		int w = pic.width();
		int h = pic.height();
		Picture newPic = new Picture(w, h - 1);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h - 1; j++) {
				if (j < seam[i]) {
					newPic.set(i, j, pic.get(i, j));
				} else {
					h--;
					newPic.set(i, j, pic.get(i, j + 1));
				}
			}
		}
		this.pic = newPic;

		return;
	}


	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		int w = pic.width();
		int h = pic.height();
		Picture newPic = new Picture(w, h - 1);
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w - 1; i++) {
				if (i < seam[j]) {
					newPic.set(i, j, pic.get(i, j));
				} else {
					w--;
					newPic.set(i, j, pic.get(i + 1, j));
				}
			}
		}
		this.pic = newPic;
		return;
	}
}