package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C_ExecutorFramework {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                performHighlyExpensiveAction(finalI);
            });
        }
        /*
        as soon as task is submitted to ExecutorService using submit() method
        the task starts
         */

        executorService.shutdown();

        try {
//            executorService.awaitTermination(100, TimeUnit.SECONDS);

            while(!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        System.out.println("time taken : " + (System.currentTimeMillis() - startTime));

        System.out.println("is all threads terminated : " + executorService.isTerminated());
    }

    public static void performHighlyExpensiveAction(int val) {
        try {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + " : " + val);
        } catch (InterruptedException e) {
        }
    }
}
