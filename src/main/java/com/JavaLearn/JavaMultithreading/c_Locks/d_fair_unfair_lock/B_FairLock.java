package com.JavaLearn.JavaMultithreading.c_Locks.d_fair_unfair_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B_FairLock {
    private Lock lock = new ReentrantLock(true);

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
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        B_FairLock fairLock = new B_FairLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                fairLock.outerMethod();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
