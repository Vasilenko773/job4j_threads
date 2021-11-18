package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int firstElement;
    private final int lastElement;
    private final T value;

    public SearchIndex(T[] array, int firstElement, int lastElement, T value) {
        this.array = array;
        this.firstElement = firstElement;
        this.lastElement = lastElement;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (lastElement - firstElement <= 10) {
            return indexOf();
        }
        int mid = (firstElement + lastElement) / 2;
        SearchIndex<T> s1 = new SearchIndex<T>(array, firstElement, mid, value);
        SearchIndex<T> s2 = new SearchIndex<T>(array, mid + 1, lastElement, value);

        s1.fork();
        s2.fork();
        return Math.max(s1.join(), s2.join());

    }


    private Integer indexOf() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> Integer search(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchIndex<>(array, 0, array.length - 1, value));
    }


    public static void main(String[] args) {
        Integer[] arrays = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(search(arrays, 11));
    }
}
