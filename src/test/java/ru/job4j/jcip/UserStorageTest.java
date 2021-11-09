package ru.job4j.jcip;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    private class UserStorageCount extends Thread {
        private final UserStorage userStorage;

        private UserStorageCount(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            this.userStorage.add(new User(1, 100));
        }
    }

    @Test
    public void whenAdd() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();

        Thread first = new UserStorageCount(userStorage);
        Thread second = new UserStorageCount(userStorage);

        first.start();
        second.start();

        first.join();
        second.join();

        Assert.assertThat(userStorage.delete(new User(1, 100)), is(true));
    }
}