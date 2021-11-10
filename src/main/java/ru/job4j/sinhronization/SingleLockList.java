package ru.job4j.sinhronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.swing.text.Element;
import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = Collections.synchronizedList(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    public synchronized List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }
}