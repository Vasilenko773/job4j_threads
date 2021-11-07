package ru.job4j.concurrent;

import java.time.Year;

public class Wget {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        first.start();
    }
}