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
        int sumI = 0;
        Sums[] sum = column(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sumI += matrix[i][j];
            }
            Sums sums = sum[i];
            sums.setRowSum(sumI);
            sum[i] = sums;
            sumI = 0;
        }
        return sum;
    }

    private static Sums[] column(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int j = 0; j < matrix[0].length; j++) {
            int c = 0;
            int sumJ = 0;
            while (c < matrix.length) {
                sumJ += matrix[c++][j];
            }
            Sums sums = new Sums();
            sums.setColSum(sumJ);
            rsl[j] = sums;
        }
        return rsl;
    }


    public static Sums[] asyncSum(int[][] matrix)  {
        CompletableFuture<Sums[]> rsl = CompletableFuture.completedFuture(sum(matrix));
        return rsl.join();
    }

    public static void main(String[] args) {
        Sums[] array = sum(new int[][]
                {
                        {1, 2},
                        {2, 4}
                }
        );

        Sums[] array1 = asyncSum(new int[][]
                        {
                                {2, 2, 3},
                                {1, 4, 7},
                                {6, 2, 5}
                        }
                );

        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i].getRowSum() + " Строка" + i);
            System.out.println(array1[i].getColSum() + " столбец" + i);
        }
    }
}