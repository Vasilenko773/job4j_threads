package ru.job4j.waitnotify;

public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread first = new Thread(
                () -> {
                    while (countBarrier.count <= countBarrier.total) {
                        countBarrier.count();
                        System.out.println("First");
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println("Second");
                }
        );
        first.start();
        second.start();
    }
}

