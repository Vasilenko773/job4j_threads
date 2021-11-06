package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();


        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println("First: " + first.getName()
                    + ". Second: " + second.getName());
        }
        System.out.println("Работа завершена");
    }
}
