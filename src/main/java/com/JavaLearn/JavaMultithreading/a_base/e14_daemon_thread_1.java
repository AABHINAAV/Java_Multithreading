package com.JavaLearn.JavaMultithreading.a_base;

public class e14_daemon_thread_1 extends Thread {

  @Override
  public void run() {
  }

  public static void main(String[] args) {
    Thread.currentThread().setDaemon(true);
  }
}
// java.lang.IllegalThreadStateException
