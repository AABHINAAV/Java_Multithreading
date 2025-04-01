package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

public class A_UsingSingleThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            int finalI = i;

            Thread thread = new Thread(() -> {
                performHighlyExpensiveAction(finalI);
            });
            thread.start();

            // making each thread to finish work
            // then only main thread will continue
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("time taken : " + (System.currentTimeMillis() - startTime));
    }

    public static void performHighlyExpensiveAction(int val) {
        try {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + " : " + val);
        } catch (InterruptedException e) {
        }
    }
}
