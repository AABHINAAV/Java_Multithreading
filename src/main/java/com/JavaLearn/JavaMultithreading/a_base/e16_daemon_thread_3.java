package com.JavaLearn.JavaMultithreading.a_base;

public class e16_daemon_thread_3 extends Thread {

  @Override
  public void run() {
    if (Thread.currentThread().isDaemon()) {
      System.out.println("Daemon Thread");
    } else {
      System.out.println("Child Thread");
    }
  }

  public static void main(String[] args) {
    System.out.println("Main Thread");
    e16_daemon_thread_3 t = new e16_daemon_thread_3();
    t.start();
    // Main Thread
    // Child Thread
    //
    //
    //
    //
    //
    // System.out.println("Main Thread");
    // e16_daemon_thread_3 t = new e16_daemon_thread_3();
    // t.setDaemon(true);
    // t.start();
    // Main Thread
    // Daemon Thread
    //
    //
    //
    //
    //
    // System.out.println("Main Thread");
    // e16_daemon_thread_3 t = new e16_daemon_thread_3();
    // t.start();
    // t.setDaemon(true);
    /*
        Main Thread
        Child Thread
        Exception in thread "main" java.lang.IllegalThreadStateException
    */
    //
    //
    //
    //
    //
    // Thread currentThread = Thread.currentThread();
    // System.out.println(currentThread.getName());
    // currentThread.setDaemon(true);
    /*
        main
        Exception in thread "main" java.lang.IllegalThreadStateException
    */
    //
    //
    //
    //
    //
    // e16_daemon_thread_3 t = new e16_daemon_thread_3();
    // t.setDaemon(true);
    // t.start();
    /*
     * daemon thread is built to provide service to main thread
     * but main thread is started but not doing anything
     * so daemon thread will also not do anything thus it will not provide any service to main thread
     * thus there will be no output in this case
     */
  }
}
