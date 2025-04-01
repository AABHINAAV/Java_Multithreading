package com.JavaLearn.JavaMultithreading.a_base;

public class e3_thread_class {

  public static class Test extends Thread {

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        System.out.println("created thread : " + Thread.currentThread().getName());
      }
    }
  }

  public static void main(String[] args) {
    Test test = new Test();
    test.start();

    for (int i = 0; i < 1000; i++) {
      System.out.println("main thread : " + Thread.currentThread().getName());
    }
  }
}
