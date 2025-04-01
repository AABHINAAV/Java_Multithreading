package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.Callable;

public class D_Runnable_VS_Callable {
    public static class RunnableExample implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class CallableExample implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            return "Hello";
        }
    }
}
