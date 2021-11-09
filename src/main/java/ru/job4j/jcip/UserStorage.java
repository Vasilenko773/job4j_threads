package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage {

    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        for (User exp : users) {
            if (exp.getId() == user.getId() && exp.getAmount() != user.getAmount()) {
                return true;
            }
        }
        return false;
    }


    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized void transfer(int fromId, int told, int amount) {
       if (users.get(fromId) != null && users.get(told) != null
               && users.get(fromId).getAmount() >= amount) {

          update(User.of(told, users.get(told).getAmount() + amount));
          update(User.of(fromId, users.get(fromId).getAmount() - amount));
       }
    }
}

