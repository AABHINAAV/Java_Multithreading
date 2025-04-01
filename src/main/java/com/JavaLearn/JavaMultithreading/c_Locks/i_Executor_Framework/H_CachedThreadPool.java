package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class H_CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executorService.execute(() -> {
                System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Task " + taskId + " finished by " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown(); // Gracefully shut down the executor
    }
}
