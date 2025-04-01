package com.JavaLearn.JavaMultithreading.c_Locks.f_deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockResolved_trylock {

    public static class Pen {
        public final Lock penLock = new ReentrantLock();

        public void writeWithPenAndPaper(Paper paper) {
            boolean penAcquired = false;
            boolean paperAcquired = false;

            try {
                penAcquired = penLock.tryLock(100, TimeUnit.MILLISECONDS);
                if (penAcquired) {
                    System.out.println(Thread.currentThread().getName() + " acquired pen ");
                    paperAcquired = paper.paperLock.tryLock(100, TimeUnit.MILLISECONDS);

                    if (paperAcquired) {
                        System.out.println(Thread.currentThread().getName() + " acquired paper ");
                        System.out.println(Thread.currentThread().getName() + " is using pen and paper ");
                        paper.finishWriting();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " couldn't acquire paper. Releasing pen.");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (paperAcquired) {
                    System.out.println(Thread.currentThread().getName() + " releasing paper");
                    paper.paperLock.unlock();
                }
                if (penAcquired) {
                    System.out.println(Thread.currentThread().getName() + " releasing pen");
                    penLock.unlock();
                }
            }
        }

        public void finishWriting() {
            penLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
            } finally {
                penLock.unlock();
            }
        }
    }

    public static class Paper {
        public final Lock paperLock = new ReentrantLock();

        public void writeWithPaperAndPen(Pen pen) {
            boolean paperAcquired = false;
            boolean penAcquired = false;

            try {
                paperAcquired = paperLock.tryLock(100, TimeUnit.MILLISECONDS);
                if (paperAcquired) {
                    System.out.println(Thread.currentThread().getName() + " acquired paper ");
                    penAcquired = pen.penLock.tryLock(100, TimeUnit.MILLISECONDS);

                    if (penAcquired) {
                        System.out.println(Thread.currentThread().getName() + " acquired pen ");
                        System.out.println(Thread.currentThread().getName() + " is using pen and paper ");
                        pen.finishWriting();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " couldn't acquire pen. Releasing paper.");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (penAcquired) {
                    System.out.println(Thread.currentThread().getName() + " releasing pen");
                    pen.penLock.unlock();
                }
                if (paperAcquired) {
                    System.out.println(Thread.currentThread().getName() + " releasing paper");
                    paperLock.unlock();
                }
            }
        }

        public void finishWriting() {
            paperLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " finished using paper ");
            } finally {
                paperLock.unlock();
            }
        }
    }

    // Rest of the code remains the same (Task1, Task2, main method)
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
            paper.writeWithPaperAndPen(pen);
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