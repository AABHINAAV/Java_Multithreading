package com.JavaLearn.JavaMultithreading.a_base;

public class e11_2_thread_methods extends Thread {

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("Thread inturrepted : " + e);
    }
  }

  public static void main(String[] args) {
    e11_2_thread_methods thread = new e11_2_thread_methods();
    thread.start();
    thread.interrupt(); // main thread interrupts the execution of thread
    // Thread inturrepted : java.lang.InterruptedException: sleep interrupted
  }
}
