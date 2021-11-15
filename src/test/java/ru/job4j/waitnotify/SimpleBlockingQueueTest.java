package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
    final SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);

    @Test
    public void logicSimpleBlocking() throws InterruptedException {

        Thread first = new Thread(() -> {
            try {
                smb.offer(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(
                () -> {
                    try {
                        buffer.add(smb.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.interrupt();
        second.join();
        assertThat(buffer, is(Collections.singletonList(3)));
    }

    @Test
    public void logicSimpleBlockingTwo() throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 5) {
                        i++;
                        try {
                            smb.offer(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    try {
                        buffer.add(smb.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.interrupt();
        second.join();
        assertThat(buffer, is(Arrays.asList(3)));
    }

    @Test
    public void logicSimpleBlockingThree() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 3) {
                        i++;
                        try {
                            smb.offer(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.currentThread().interrupt();
                }
        );

        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 3) {
                        i++;
                        try {
                            buffer.add(smb.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.interrupt();
        second.join();
        assertThat(buffer, is(Arrays.asList(3, 3, 3)));
    }
}