package ru.job4j.io;

import java.io.*;
import java.util.Objects;
import java.util.function.Predicate;

public class ParseFile implements Connector {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content(Objects::nonNull);
    }

    public String getContentWithoutUnicode() {
        return content((i) -> i < 0X80);
    }

    @Override
    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                output.append((char) data);
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}