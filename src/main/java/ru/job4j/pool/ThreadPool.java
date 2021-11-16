package ru.job4j.pool;

import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(1);

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        threads.add(new Thread(tasks.poll()));

    }

    public void shutdown() throws InterruptedException {

        for (Thread thread : threads) {
            while (!Thread.currentThread().isInterrupted()) {
                thread.wait();
            }
            thread.interrupt();
        }
    }
}


