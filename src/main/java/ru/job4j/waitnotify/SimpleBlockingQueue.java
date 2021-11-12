package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int count;

    public SimpleBlockingQueue(Integer count) {
        this.count = count;
    }

    public synchronized void offer(T value) {
        while (queue.size() == count) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notify();
        queue.add(value);
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notify();
        return queue.remove();
    }


    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(7);

        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        smb.offer(1);
                        System.out.println("Добавленно");
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        smb.poll();
                        System.out.println("Полученно");
                    }
                }
        );
        second.start();
        first.start();
        first.join();
        second.join();
    }
}
