package ru.job4j.jcip;

import java.util.Objects;
import java.util.function.BinaryOperator;

public class User {

    private final int id;
    private final int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public static User setAmount(int id, BinaryOperator<Integer> binaryOperator, int i, int j) {
        return new User(id, binaryOperator.apply(i, j));
    }


    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}