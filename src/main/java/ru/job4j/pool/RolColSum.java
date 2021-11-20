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
        return sumRowAndColumn(matrix);
    }

    private static Sums[] sumRowAndColumn(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        int size = 0;
        for (int i = 0; i < matrix.length; i++) {

            int c = 0;
            int sumJ = 0;
            while (c < matrix.length) {
                sumJ += matrix[c++][i];
            }
            int sumI = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sumI += matrix[i][j];
            }
            Sums sums = new Sums();
            sums.setRowSum(sumI);
            sums.setColSum(sumJ);
            rsl[size++] = sums;
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        CompletableFuture<Sums[]> rsl = CompletableFuture.completedFuture(sumRowAndColumn(matrix));
        return rsl.join();
    }
}