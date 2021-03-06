package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }


    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        User first = users.get(fromId);
        User second = users.get(told);
        if (first != null && second != null
                && first.getAmount() >= amount) {

            first.setAmount(users.get(fromId).getAmount() - amount);
            second.setAmount(users.get(told).getAmount() + amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        User user1 = new User(1, 100);
        User user2 = new User(2, 50);
        UserStorage userStorage = new UserStorage();
        System.out.println(userStorage.add(user1));
        System.out.println(userStorage.add(user2));
        System.out.println(userStorage.transfer(1, 2, 50));
        System.out.println(userStorage.update(user1));
        System.out.println(userStorage.update(user2));
        System.out.println(userStorage.delete(user1));
        System.out.println(userStorage.delete(user2));

    }
}