package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String[] process = {"\\", "|", "/"};
            try {
                for (String s : process) {
                    Thread.sleep(500);
                    System.out.print("\r load: " + s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
