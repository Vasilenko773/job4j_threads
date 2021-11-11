package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutPutFile {

    private final File file;


    public OutPutFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
