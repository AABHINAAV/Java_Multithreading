package com.JavaLearn.JavaMultithreading.c_Locks.c_Reentrant_Example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();

        try {
            System.out.println("Outer method called by " + Thread.currentThread().getName());
            innerMethod();
        } catch (Exception e) {
        } finally {
            System.out.println("Outer method unlocked by : " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        // Reentrant Lock

        try {
            System.out.println("Inner method called by " + Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (Exception e) {
        } finally {
            System.out.println("Inner method unlocked by : " + Thread.currentThread().getName());
            /*
            always print before unlocking
            if not done then we unlock, then another thread will start its working
            thus printing work of thread-1 might be done after some work done by thread-2

            if we have multiple thread(thread1 to thread10) calling our working method
            then any thread which requests first will get the method working first

            Unfair Lock does not guarantee First-Come, First-Served (FCFS).
            A newer thread may "jump the queue" and acquire the lock before an older waiting thread.

            When true is passed, the lock is fair, meaning it follows a First-Come, First-Served (FCFS) order.
             */
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                reentrantExample.outerMethod();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
