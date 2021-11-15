package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int value;
        int nextValue;
        do {
            if (count.get() == null) {
                count.set(0);
            }
            value = count.get();
            nextValue = value + 1;
        } while (!count.compareAndSet(value, nextValue));
    }

    public int get() {
        int value;
        int nextValue;
        do {
            if (count.get() == null) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
            value = count.get();
            nextValue = value;
        } while (!count.compareAndSet(nextValue, value));

        return nextValue;
    }

    public static void main(String[] args) {
        CASCount casCount = new CASCount();
        casCount.increment();
        System.out.println(casCount.get());
        casCount.increment();
        System.out.println(casCount.get());
        casCount.increment();
        System.out.println(casCount.get());
        casCount.increment();
        System.out.println(casCount.get());
    }
}