package com.JavaLearn.JavaMultithreading.a_base;

public class e9_thread_life_cycle extends Thread {

  @Override
  public void run() {
    System.out.println("RUNNING"); // RUNNING
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    e9_thread_life_cycle t1 = new e9_thread_life_cycle();

    System.out.println(t1.getState()); // NEW
    t1.start();
    System.out.println(t1.getState()); // RUNNABLE
    Thread.sleep(100);
    System.out.println(t1.getState()); // TIMED_WAITING
    t1.join();
    System.out.println(t1.getState()); // TERMINATED
  }
}
