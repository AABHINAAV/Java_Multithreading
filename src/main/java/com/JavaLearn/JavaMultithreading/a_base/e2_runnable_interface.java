package com.JavaLearn.JavaMultithreading.a_base;

public class e2_runnable_interface {

  public static class Test implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        System.out.println("created thread : " + Thread.currentThread().getName());
      }
    }
  }

  public static void main(String[] args) {
    Test test = new Test();
    Thread thread1 = new Thread(test);
    thread1.start();

    for (int i = 0; i < 1000; i++) {
      System.out.println("main thread : " + Thread.currentThread().getName());
    }
  }
}
