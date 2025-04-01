package com.JavaLearn.JavaMultithreading.a_base;

public class e5_thread_restart_runnableInterface {

  public static class Test implements Runnable {

    @Override
    public void run() {
      System.out.println("created thread : " + Thread.currentThread().getName());
    }
  }

  public static void main(String[] args) {
    Test test = new Test();

    Thread t1 = new Thread(test);
    t1.start();
    // t1.start();  // illegalThreadStateException

    Thread t2 = new Thread(test);
    t2.start();
  }
}
