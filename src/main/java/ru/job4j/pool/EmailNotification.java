package ru.job4j.pool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Метод через ExecutorService отправляет сообщение
     * @param user - параметр у которого берутся данные
     */
    public void emailTo(User user) {
        pool.submit(new Thread(
                () -> send(user.getUsername() + " " + user.getEmail(),
                        "lalala" + Thread.currentThread().getName(), user.getEmail())));
    }

    /**
     * Метод закрывает pool
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }
}

