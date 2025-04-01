package com.JavaLearn.JavaMultithreading.c_Locks.b_Explicit_Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExtrinsicBankAccount {
    private Lock lock = new ReentrantLock();

    private int balance = 100;

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding to withdraw " + amount);
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed to withdraw. Remaining balance: " + balance);
                    } catch (InterruptedException e) {

                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock");
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        final ExtrinsicBankAccount extrinsicBankAccount = new ExtrinsicBankAccount();

        Runnable task = new Runnable() {
            public void run() {
                extrinsicBankAccount.withdraw(50);
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
