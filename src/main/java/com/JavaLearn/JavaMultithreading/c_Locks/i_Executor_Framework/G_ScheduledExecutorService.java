package com.JavaLearn.JavaMultithreading.c_Locks.i_Executor_Framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class G_ScheduledExecutorService {
    public static void usingSchedule() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // You create a scheduled thread pool with a single thread.

        scheduledExecutorService.schedule(
                () -> {
                    System.out.println("task started at " + System.currentTimeMillis());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("task finished at " + System.currentTimeMillis());
                },
                3,
                TimeUnit.SECONDS
        );
        /*
        You schedule a task to start after a delay of 3 seconds.
        prints task started message
        The task sleeps for 3 seconds
        then prints the task finished message.
         */

        scheduledExecutorService.shutdown();
    }

    public static void usingScheduleAtFixedRate() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
                    System.out.println("Task started at: " + System.currentTimeMillis());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    System.out.println("Task finished at: " + System.currentTimeMillis());
                },
                0,
                3,
                TimeUnit.SECONDS
        );
        /*
        it says to wait for time initialDelay and then
        perform runnable task after every time period
        If task takes longer than the period, tasks may overlap.
        but it will not work until we have to provide the handling for its termination
        because scheduledExecutorService.shutdown() might close it before it even start working

        if task running time is more than period
        then it starts the next execution just after the completion of first execution
        Task started at: 1742666733115
        Task finished at: 1742666738128
        Task started at: 1742666738129  <-- No wait, runs back-to-back
        Task finished at: 1742666743131
        Task started at: 1742666743131  <-- No wait, runs back-to-back
        Task finished at: 1742666748132
        Task started at: 1742666748132  <-- No wait, runs back-to-back
        Task finished at: 1742666753137
        shutting down
         */

        scheduledExecutorService.schedule(
                () -> {
                    System.out.println("shutting down");
                    scheduledExecutorService.shutdown();
                },
                10,
                TimeUnit.SECONDS
        );
    }

    public static void usingScheduledAtFixedDelay() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> System.out.println("Hello"),
                0,
                2,
                TimeUnit.SECONDS
        );
        /*
        start first task execution after initial delay
        and then wait for given delay time after completion of execution to start the next execution
         */

        scheduledExecutorService.schedule(
                () -> {
                    System.out.println("shutting down");
                    scheduledExecutorService.shutdown();
                },
                10,
                TimeUnit.SECONDS
        );
    }

    public static void main(String[] args) {
        usingSchedule();
//        usingScheduleAtFixedRate();
//        usingScheduledAtFixedDelay();
    }

}
