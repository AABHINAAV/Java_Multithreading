package com.JavaLearn.JavaMultithreading.a_base;

public class e15_daemon_thread_2 extends Thread {

  @Override
  public void run() {
    for (;;) {
      System.out.println("hello");
    }
  }

  public static void main(String[] args) {
    e15_daemon_thread_2 thread = new e15_daemon_thread_2();
    thread.setDaemon(true);
    thread.start();
    System.out.println("world");
  }
}
// world
// hello
// hello
// hello
