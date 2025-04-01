package com.JavaLearn.JavaMultithreading.a_base;

class MultipleTaskMultipleThreadTask1 implements Runnable {

  @Override
  public void run() {
    System.out.println("Task 1");
  }
}

class MultipleTaskMultipleThreadTask2 implements Runnable {

  @Override
  public void run() {
    System.out.println("Task 2");
  }
}

class MultipleTaskMultipleThreadTask3 implements Runnable {

  @Override
  public void run() {
    System.out.println("Task 3");
  }
}

public class e8_multiple_task_multiple_thread {

  public static void main(String[] args) {
    MultipleTaskMultipleThreadTask1 task1 = new MultipleTaskMultipleThreadTask1();
    Thread thread1 = new Thread(task1);
    thread1.start();

    MultipleTaskMultipleThreadTask2 task2 = new MultipleTaskMultipleThreadTask2();
    Thread thread2 = new Thread(task2);
    thread2.start();

    MultipleTaskMultipleThreadTask3 task3 = new MultipleTaskMultipleThreadTask3();
    Thread thread3 = new Thread(task3);
    thread3.start();
  }
}
