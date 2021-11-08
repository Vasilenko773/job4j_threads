package ru.job4j.sinhronization;

public final class Cache {

    private static Cache cache;

    public synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
