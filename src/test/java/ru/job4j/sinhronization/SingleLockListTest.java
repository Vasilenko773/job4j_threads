package ru.job4j.sinhronization;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void addString() throws InterruptedException {
        SingleLockList<String> list = new SingleLockList<>(new LinkedList<>());
        Thread first = new Thread(() -> list.add("112"));
        Thread second = new Thread(() -> list.add("221"));
        Thread third = new Thread(() -> list.get(1));
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        Set<String> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of("112", "221")));
    }
}