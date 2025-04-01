package com.JavaLearn.JavaMultithreading.c_Locks.j_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class A_withManualThreads {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        for (int i = 1; i <= numberOfServices; i++) {
            Thread thread = new Thread(new DependentService(latch));
            thread.start();
        }

//        latch.await();
        /*
        main thread will wait infinitely for the threads to finish work
        and decrement the value of latch
        and in end put latch count to 0
        so that main thread can continue to its work

        we cannot reuse latch once it is countdown to 0
         */

        latch.await(5, TimeUnit.SECONDS);
        /*
        main thread will wait only for given time like here 5 seconds only
        and then will continue to perform its further operations
        while the operations to be performed on other threads will be executed also
         */
        System.out.println("work completed");
    }

    static class DependentService implements Runnable {

        private final CountDownLatch latch;

        public DependentService(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " : service started");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        }
    }

}


