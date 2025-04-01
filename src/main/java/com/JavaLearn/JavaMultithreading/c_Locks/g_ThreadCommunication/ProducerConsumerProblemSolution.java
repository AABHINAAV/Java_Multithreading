package com.JavaLearn.JavaMultithreading.c_Locks.g_ThreadCommunication;

class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();  // wake thread2 from wait state
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // wake thread1 from wake state
    }
}

public class ProducerConsumerProblemSolution {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.produce(i);
            }
        });
        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.consume();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}