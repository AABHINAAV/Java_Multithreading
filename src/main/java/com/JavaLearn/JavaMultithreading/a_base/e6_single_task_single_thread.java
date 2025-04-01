package com.JavaLearn.JavaMultithreading.a_base;

class SingleTaskSingleThread implements Runnable {

  @Override
  public void run() {
    System.out.println("Task 1");
  }
}

public class e6_single_task_single_thread {

  public static void main(String[] args) {
    SingleTaskSingleThread task1 = new SingleTaskSingleThread();
    Thread thread1 = new Thread(task1);
    thread1.start();
  }
}
