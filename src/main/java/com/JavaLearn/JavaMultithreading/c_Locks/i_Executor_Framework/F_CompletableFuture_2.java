package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class F_CompletableFuture_2 {
    public static void allOfMethod() {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 1");
            return "result 1";
        });
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 2");
            return "result 2";
        });

        CompletableFuture<?> allTasks = CompletableFuture.allOf(
                completableFuture1,
                completableFuture2
        );

        try {
            allTasks.get();
            /*
            it will run the tasks so this will be printed
            Task 1
            Task 2
            if i dont use get method, main thread will not blocked and as soon as main thread will
            finish work, jvm will close.
            allOf() method does not returns any thing
            After calling get(), i will have to call get() on each completable future
            individually to get the result of each one of them.
             */
            System.out.println(completableFuture1.get());
            System.out.println(completableFuture2.get());
            /*
            upon calling them will help to get the result of each one of them
             */
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void anyOfMethod() {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 1");
            return "result 1";
        });
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 2");
            return "result 2";
        });

        CompletableFuture<Object> allTasks = CompletableFuture.anyOf(
                completableFuture1,
                completableFuture2
        );

        try {
            System.out.println(allTasks.get());
            /*
            it will let both of the task to and keep main thread block until all
            of them finishes their tasks
            then it prints the result of task which it gets first
            if i dont use get method, main thread will not blocked and as soon as main thread will
            finish work, jvm will close.
            After calling get(), i will have to call get() on each completable future
            individually to get the result of each one of them.
             */
            System.out.println(completableFuture1.get());
            System.out.println(completableFuture2.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        allOfMethod();
        anyOfMethod();
    }
}
