package com.JavaLearn.JavaMultithreading.a_base;

public class e4_thread_restart_threadClass {

  public static class Test extends Thread {

    @Override
    public void run() {
      System.out.println("created thread : " + Thread.currentThread().getName());
    }
  }

  public static void main(String[] args) {
    Test test = new Test();
    test.start();
    test.start();
  }
}
