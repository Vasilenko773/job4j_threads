package ru.job4j.concurrent;

import java.time.Year;

public class Wget {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\rLoading : " + i + "%");
        }
    }
}
