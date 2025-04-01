package com.JavaLearn.JavaMultithreading.c_Locks.h_multiples_ways_to_create_thread;

public class MultipleWaysThread {
    public void anonymousClass() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void lambdaExpression() {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void lambdaExpressionWithinThread() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        thread.start();
    }
}
