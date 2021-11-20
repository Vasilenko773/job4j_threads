package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = sumRowAndColumn(matrix, i);
        }
        return rsl;
    }

    private static Sums sumRowAndColumn(int[][] matrix, int index) {
        int c = 0;
        int sumJ = 0;
        int sumI = 0;
        while (c < matrix.length) {
            sumJ += matrix[c++][index];
            c--;
            sumI += matrix[index][c++];
        }
        Sums sums = new Sums();
        sums.setRowSum(sumI);
        sums.setColSum(sumJ);
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        CompletableFuture<Sums[]> rsl = CompletableFuture.supplyAsync(
                () -> {
                    Sums[] exp = new Sums[matrix.length];
                    for (int i = 0; i < matrix.length; i++) {
                        exp[i] = sumRowAndColumn(matrix, i);
                    }
                    return exp;
                }
        );
        return rsl.join();
    }
}