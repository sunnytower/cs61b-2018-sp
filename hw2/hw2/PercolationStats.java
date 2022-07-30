package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int T;
    private double[] result;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        result = new double[T];
        for (int i = 0; i < T; ++i) {
            Percolation per = pf.make(N);
            while (!per.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                if (!per.isOpen(x, y)) {
                    per.open(x, y);
                }
            }
            result[i] = (double) per.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {
        return StdStats.mean(result);
    }
    public double stddev() {
        return StdStats.stddev(result);
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
