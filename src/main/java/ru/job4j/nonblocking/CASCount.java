package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int value) {
        this.count.set(value);
    }

    public void increment() {
        int value;
        int nextValue;
        do {
            value = count.get();
            nextValue = value + 1;
        } while (!count.compareAndSet(value, nextValue));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASCount casCount = new CASCount(2);
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