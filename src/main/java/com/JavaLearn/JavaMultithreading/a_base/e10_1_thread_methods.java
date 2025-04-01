package com.JavaLearn.JavaMultithreading.a_base;

public class e10_1_thread_methods extends Thread {

  @Override
  public void run() {}

  public static void main(String[] args) throws InterruptedException {
    Thread.currentThread();
    // returns instance of main current thread

    e10_1_thread_methods thread = new e10_1_thread_methods();

    System.out.println(thread.getName());
    // get thread name

    thread.setName("new thread name");
    // set thread name

    //
    System.out.println("old priority : " + thread.getPriority());
    thread.setPriority(3);
    thread.setPriority(MAX_PRIORITY); // 10
    thread.setPriority(MIN_PRIORITY); // 1
    thread.setPriority(NORM_PRIORITY); // 5
    System.out.println("new priority : " + thread.getPriority());
    // set and get priority of a thread

    thread.sleep(1000);
    // sleep thread for 1000 miliseconds
  }
}
