package com.JavaLearn.JavaMultithreading.a_base;

public class e13_4_thread_methods extends Thread {

  @Override
  public void run() {
    try {
      Thread.currentThread().setName("side thread");
      Thread.sleep(2000);
      System.out.println(
        "\nSide thread name : " + Thread.currentThread().getName()
      );
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    e13_4_thread_methods sideThread = new e13_4_thread_methods();

    sideThread.start();
    sideThread.join();
    // if dont add this then sout of next line will be printed
    // by main thread and after 2 seconds side thread will be printed
    // join() tells the main thread to let side thread
    // complete all of its work first

    System.out.println(
      "Main thread name : " + Thread.currentThread().getName()
    );
  }
}
