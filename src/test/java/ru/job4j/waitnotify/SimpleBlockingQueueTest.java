package ru.job4j.waitnotify;

import junit.framework.TestCase;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void logicSimpleBlocking() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(() -> smb.offer(3));
        Thread second = new Thread(smb::poll);
        first.start();
        second.start();
        first.join();
        second.join();
    }


    @Test
    public void logicSimpleBlockingTwo() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(8);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 8) {
                        i++;
                        smb.offer(3);
                    }
                }
        );

        Thread second = new Thread(smb::poll);
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void logicSimpleBlockingThree() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        smb.offer(3);
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 6) {
                        i++;
                        smb.poll();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}