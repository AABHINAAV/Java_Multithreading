package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

public class B_ManualMultithreadThreadManagement {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                performHighlyExpensiveAction(finalI);
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
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
