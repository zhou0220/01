package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom;
    private int numOpenSites;
    private int[][] surroundings = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /** Create N-by-N grid, with all site initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new boolean[N][N];
        size = N;
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    // transform (row, col) to 1D coordinate.
    private int xyTo1d(int row, int col) {
        return row * size + col + 1;
    }

    // validate the Index
    public void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException("out of range!");
        }
    }

    // open the site (row, col) if it is not open already.
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            uf.union(xyTo1d(row, col), top);
            ufExcludeBottom.union(xyTo1d(row, col), top);
        }
        if (col == size - 1) {
            uf.union(xyTo1d(row, col), bottom);
        }
        for (int[] surrounding : surroundings) {
            int adjacentRow = row + surrounding[0];
            int adjacentCol = col + surrounding[1];
            if (adjacentRow >= 0 && adjacentRow < size) {
                if (adjacentCol >= 0 && adjacentCol < size) {
                    if (isOpen(adjacentRow, adjacentCol)) {
                        uf.union(xyTo1d(row, col), xyTo1d(adjacentRow, adjacentCol));
                        ufExcludeBottom.union(xyTo1d(row, col), xyTo1d(adjacentRow, adjacentCol));
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufExcludeBottom.connected(xyTo1d(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // use for unit testing (just for autograding)
    public static void main(String[] args) {
        return;
    }
}
