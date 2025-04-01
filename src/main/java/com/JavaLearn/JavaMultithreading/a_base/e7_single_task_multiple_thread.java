package com.JavaLearn.JavaMultithreading.a_base;

class SingleTaskMultipleThread implements Runnable {

  @Override
  public void run() {
    System.out.println("Task 1");
  }
}

public class e7_single_task_multiple_thread {

  public static void main(String[] args) {
    SingleTaskMultipleThread task1 = new SingleTaskMultipleThread();

    Thread thread1 = new Thread(task1);
    thread1.start();

    Thread thread2 = new Thread(task1);
    thread2.start();

    Thread thread3 = new Thread(task1);
    thread3.start();
  }
}
