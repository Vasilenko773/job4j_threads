package ru.job4j.nonblocking;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class CacheTest {

    @Test
    public void hashMapAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);

        Assert.assertTrue(cache.add(base2));
    }

    @Test
    public void hashMapRemoveTrue() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base2);

        Assert.assertTrue(cache.add(base2));
    }

    @Test
    public void hashMapAddFalse() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);

        Assert.assertFalse(cache.add(base1));
    }

    @Test
    public void hashMapUpdate() throws Cache.OptimisticException {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        Base base3 = new Base(2, 1);
        Base base4 = new Base(2, 2);

        Assert.assertTrue(cache.update(base3));
        Assert.assertFalse(cache.add(base4));
    }
}