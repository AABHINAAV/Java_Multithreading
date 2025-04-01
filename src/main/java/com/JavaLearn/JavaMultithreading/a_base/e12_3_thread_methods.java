package com.JavaLearn.JavaMultithreading.a_base;

public class e12_3_thread_methods extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName());
      Thread.yield();
      //   A hint to the scheduler that the current thread is willing to
      //   yield(pause) its current use of a processor.
      //   The processor can use another thread meanwhile.
      //   The scheduler is free to ignore this hint.
    }
  }

  public static void main(String[] args) {
    e12_3_thread_methods t1 = new e12_3_thread_methods();
    e12_3_thread_methods t2 = new e12_3_thread_methods();

    t1.start();
    t2.start();
  }
}
// without yield
// Thread-1
// Thread-1
// Thread-0
// Thread-0
// Thread-0
// Thread-0
// Thread-1
// Thread-1
// Thread-1

// with yield
// Thread-0
// Thread-1
// Thread-0
// Thread-1
// Thread-0
// Thread-1
// Thread-0
// Thread-1
// Thread-0
// Thread-1
