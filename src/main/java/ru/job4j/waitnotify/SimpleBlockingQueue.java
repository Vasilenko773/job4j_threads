package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final int count;

    public SimpleBlockingQueue(Integer count) {
        this.count = count;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == count) {
            wait();
        }
        queue.add(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        notify();
        return queue.remove();
    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(7);

        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        try {
                            smb.offer(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Добавленно");
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        try {
                            smb.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
