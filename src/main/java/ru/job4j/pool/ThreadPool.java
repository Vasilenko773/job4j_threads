package ru.job4j.pool;

import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(1);

    public ThreadPool() {
        int i = 0;
        while (i < size) {
            i++;
            Thread rsl = new Thread(Thread.currentThread().getName());
            rsl.start();
            threads.add(rsl);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("i");
            }
        };
        threadPool.work(run);
        threadPool.shutdown();
    }
}


