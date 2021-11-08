package ru.job4j.io;

import java.util.function.Predicate;

public interface Connector {

   public String content(Predicate<Character> filter);
}
