package com.JavaLearn.JavaMultithreading.c_Locks.j_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B_withExecutorFramework {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        for(int i=1; i<=numberOfServices; i++) {
            executorService.submit(new DependentService(latch));
        }

        latch.await();
        System.out.println("work completed");
        executorService.shutdown();

        /*
        if we are using shutdown
        if latch wait time is 5seconds while the worker threads time is 7seconds
        then work completed will be printed and main thread will be shutdown and then after that
        the result of all worker threads will be printed
        it means it does not stops the worker threads

        if we use shutdownNow instead shutdown
        it will stop all the threads(main and side worker)
         */
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


