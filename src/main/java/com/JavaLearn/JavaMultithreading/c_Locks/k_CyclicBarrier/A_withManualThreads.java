package com.JavaLearn.JavaMultithreading.c_Locks.k_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class A_withManualThreads {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(
                numberOfServices,
                () -> System.out.println("All systems are up!")
        );
        Thread thread1 = new Thread(new DependentService("DB", cyclicBarrier));
        thread1.start();
        Thread thread2 = new Thread(new DependentService("Kafka", cyclicBarrier));
        thread2.start();
        Thread thread3 = new Thread(new DependentService("Websocket", cyclicBarrier));
        thread3.start();

        System.out.println("Main");
        /*
         cyclic barrier does not blocks the main thread
         */
    }

    static class DependentService implements Runnable {
        private final String work;
        private final CyclicBarrier cyclicBarrier;

        public DependentService(String work, CyclicBarrier cyclicBarrier) {
            this.work = work;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(work + " : service initialization started");
            try {
                Thread.sleep(100);
                System.out.println(work + " : service initialization completed");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


