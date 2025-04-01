package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.*;

public class F_CompletableFuture_1 {

    public static Future<String> getFuture() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("hello");
                return "bey";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();

        return future;
    }

    public static void futureMethod1() {
        System.out.println("main thread started working");
        Future<String> future = getFuture();
        System.out.println("main thread finished working");
        /*
        main thread started working
        main thread finished working
        hello
         */
    }

    public static void futureMethod2() {
        System.out.println("main thread started working");
        Future<String> future = getFuture();
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread finished working");

        /*
        main thread started working
        hello
        bey
        main thread finished working
         */
    }

    public static CompletableFuture<String> completableFutureMethod() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("hello");
                return "bey";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return completableFuture;
    }

    public static void completableFuture1() {
        System.out.println("main thread started working");
        CompletableFuture<String> completableFuture = completableFutureMethod();
        System.out.println("main thread finished working");
        /*
        main thread started working
        main thread finished working
         */
    }

    public static void completableFuture2() {
        System.out.println("main thread started working");
        CompletableFuture<String> completableFuture = completableFutureMethod();
        try {
            String result = completableFuture.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread finished working");
        /*
        main thread started working
        hello
        bey
        main thread finished working
         */
    }


    public static void main(String[] args) {
//        futureMethod1();
//        futureMethod2();
//        completableFuture1();
        completableFuture2();
    }

}
