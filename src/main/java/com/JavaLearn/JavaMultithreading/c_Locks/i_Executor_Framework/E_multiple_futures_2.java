package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class E_multiple_futures_2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable1 = () -> {
            System.out.println("callable 1");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("callable 2");
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("callable 3");
            return 3;
        };

        List<Callable<Integer>> callableList = Arrays.asList(callable1, callable2, callable3);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.invokeAll(callableList);
        // it is same as performing get method on each callable task
        // makes main thread to stop and wait for all callables tasks to finish
        // and then main thread will resume its working
        // it does not shutdown executor service after completing its work
        // we need to call shutdown() explicitly

        // other way
        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        for(Future<Integer> future : futureList) {
            Integer i = future.get();
            System.out.println("callable value : " + i);
        }

        executorService.shutdown();
        System.out.println("program end");
    }
}
