package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int numOpen;
    private int N;
    private int topSite;
    private int bottomSite;
    private WeightedQuickUnionUF siteUnion;
    private WeightedQuickUnionUF backWashSol;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        numOpen = 0;
        topSite = N * N;
        bottomSite = N * N + 1;
        sites = new boolean[this.N][this.N];
        siteUnion = new WeightedQuickUnionUF(N * N + 2);
        backWashSol = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; ++i) {
            siteUnion.union(topSite, xyTo1D(0, i));
            siteUnion.union(bottomSite, xyTo1D(N - 1, i));
            backWashSol.union(topSite,xyTo1D(0, i));
        }
    }
    private int xyTo1D(int y, int x) {
        return y * N + x;
    }
    public void open(int row, int col) {
        checkOutOfBound(row, col);
        if (sites[row][col]) {
            return;
        }
        sites[row][col] = true;
        numOpen++;
        checkNeighborAndSet(row, col, row - 1, col);
        checkNeighborAndSet(row, col, row + 1, col);
        checkNeighborAndSet(row, col, row, col - 1);
        checkNeighborAndSet(row, col, row, col + 1);
    }
    private void checkNeighborAndSet(int row, int col, int neighborRow, int neighborCol) {
        if (neighborCol < 0 || neighborRow < 0 || neighborRow >= N || neighborCol >= N) {
            return;
        }
        if (sites[neighborRow][neighborCol]) {
            siteUnion.union(xyTo1D(row, col), xyTo1D(neighborRow, neighborCol));
            backWashSol.union(xyTo1D(row, col), xyTo1D(neighborRow, neighborCol));
        }
    }
    public boolean isOpen(int row, int col) {
        checkOutOfBound(row, col);
        return sites[row][col];
    }
    private void checkOutOfBound(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isFull(int row, int col) {
        checkOutOfBound(row, col);
        return sites[row][col] && backWashSol.connected(topSite, xyTo1D(row, col));
    }
    public int numberOfOpenSites() {
        return numOpen;
    }
    public boolean percolates() {
        return siteUnion.connected(topSite, bottomSite);
    }
    public static void main(String[] args) {
    }
}
