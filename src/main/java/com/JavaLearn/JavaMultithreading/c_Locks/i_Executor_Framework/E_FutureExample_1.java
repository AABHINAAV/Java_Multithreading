package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.*;

public class E_FutureExample_1 {

    public static void usingRunnableWithFuture() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //
//        Runnable runnable = () -> {
//            System.out.println("runnable task");
//        };
//        Future<?> runnableFuture = executorService.submit(runnable);
        //
        //
        // or passing runnable directly using lambda expressions
        Future<?> future = executorService.submit(() -> {
            System.out.println("runnable task");
        });

        if (future.isDone()) {
            System.out.println("work done 1");
        }

        try {
            future.get();
            /*
            the get() method of future blocks the current thread until
            it receives or finishes the asynchronous operation
             */
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (future.isDone()) {
            System.out.println("work done 2");
        }
    }

    public static void usingCallableWithFuture() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

//        Callable<String> callableTask = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "";
//            }
//        };
//        Future<String> future = executor.submit(callableTask);
        //
        //
        // or passing runnable directly using lambda expressions
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000); // Simulate computation
            return "Task Completed!";
        });

        System.out.println("Task Submitted... Doing other work.");

        try {
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
            String result = future.get(1, TimeUnit.SECONDS); // Wait max 1 second
            System.out.println("Result: " + result);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Task took too long to complete!");
        }

        boolean canceled = future.cancel(true); // Attempt to cancel the task
        System.out.println("Task cancelled: " + canceled);

        if (future.isDone()) {
            System.out.println("work done 2");
        }

        executor.shutdown();
    }
}
