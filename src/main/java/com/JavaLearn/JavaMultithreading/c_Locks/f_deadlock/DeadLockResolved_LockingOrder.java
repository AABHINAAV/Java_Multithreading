package com.JavaLearn.JavaMultithreading.c_Locks.f_deadlock;

public class DeadLockResolved_LockingOrder {

    public static class Pen {
        public synchronized void writeWithPenAndPaper(Paper paper) {
            System.out.println(Thread.currentThread().getName() + " is using pen " + " and trying to use paper ");
            paper.finishWriting();
        }

        public synchronized void finishWriting() {
            System.out.println(Thread.currentThread().getName() + " finished using pen ");
        }
    }

    public static class Paper {
        public synchronized void writeWithPaperAndPen(Pen pen) {
            System.out.println(Thread.currentThread().getName() + " is using paper " + " and trying to use pen ");
            pen.finishWriting();
        }

        public synchronized void finishWriting() {
            System.out.println(Thread.currentThread().getName() + " finished using paper ");
        }
    }

    public static class Task1 implements Runnable {
        private Pen pen;
        private Paper paper;

        public Task1(Pen pen, Paper paper) {
            this.pen = pen;
            this.paper = paper;
        }

        @Override
        public void run() {
            pen.writeWithPenAndPaper(paper);
            // thread1 locks pen object and tries to lock paper
        }
    }

    public static class Task2 implements Runnable {
        private Pen pen;
        private Paper paper;

        public Task2(Pen pen, Paper paper) {
            this.pen = pen;
            this.paper = paper;
        }

        @Override
        public void run() {
            synchronized (pen) {
                /*
                since thread1 has already locked pen object
                so when thread2 tries to aquire lock on pen object
                it will enter the BLOCKED state
                until thread1 releases the lock on pen object
                 */
                paper.writeWithPaperAndPen(pen);
                // thread2 locks paper object and tries to lock pen
            }
        }
    }

    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();
    }
}