package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    public int numOfExperiments;
    public double[] fractions;

    //perform T independent experiments on an N-by-N grid.
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numOfExperiments = T;
        fractions = new double[N];
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            int numOpenSites = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    numOpenSites += 1;
                }
            }
            fractions[i] = (double) numOpenSites / totalSites;
        }
    }

    // sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard devation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double miu = mean();
        double sigma = stddev();
        double lowEndpoint = miu - 1.96 * sigma / (Math.sqrt(numOfExperiments));
        return lowEndpoint;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double miu = mean();
        double sigma = stddev();
        double highEndpoint = miu + 1.96 * sigma / (Math.sqrt(numOfExperiments));
        return highEndpoint;
    }
}
