package com.JavaLearn.JavaMultithreading.c_Locks.a_Intrinsic_Lock;

public class IntrinsicBankAccount {
    private int balance = 100;

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding to withdraw " + amount);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }

            balance -= amount;

            System.out.println(Thread.currentThread().getName() + " completed to withdraw. Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public static void main(String[] args) {
        final IntrinsicBankAccount intrinsicBankAccount = new IntrinsicBankAccount();

        Runnable task = new Runnable() {
            public void run() {
                intrinsicBankAccount.withdraw(50);
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
