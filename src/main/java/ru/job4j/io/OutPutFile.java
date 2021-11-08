package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutPutFile {

    private final ParseFile parseFile;


    public OutPutFile(ParseFile parseFile) {
        this.parseFile = parseFile;
    }

    public void saveContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(parseFile.getContent()))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
