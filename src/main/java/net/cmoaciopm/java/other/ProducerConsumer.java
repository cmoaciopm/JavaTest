package net.cmoaciopm.java.other;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer {

   private static final int MAX = 10;
   private static LinkedList<String> queue = new LinkedList<>();
   private static final int SPEED_PRODUCER = 5;
   private static final int SPEED_CONSUMER = 2;

   public static void main(String[] args) {
      new ProducerThread().start();
      new ConsumerThread().start();
   }

   static class ProducerThread extends Thread {
      private int id = 0;
      @Override
      public void run() {
         System.out.println("Producer started.");
         while (true) {
            try {
               long interval = getRandomInterval(SPEED_PRODUCER);
               Thread.sleep(interval);
               System.out.println("Spent " + interval + " on producing");
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

            synchronized (queue) {
               if (queue.size() >= MAX) {
                  System.out.println("Full, wait for consumer.");
                  try {
                     queue.wait();
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               queue.add(String.valueOf(++id));
               System.out.println("After producing, size is " + queue.size());
               queue.notifyAll();
            }
         }
      }
   }

   static class ConsumerThread extends Thread {

      @Override
      public void run() {
         System.out.println("Consumer started.");

         while (true) {
            synchronized (queue) {
               if (queue.size() <= 0) {
                  System.out.println("Empty, wait for producer.");
                  try {
                     queue.wait();
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               queue.poll();
               System.out.println("After consuming, size is " + queue.size());
               queue.notifyAll();
            }

            try {
               long interval = getRandomInterval(SPEED_CONSUMER);
               Thread.sleep(interval);
               System.out.println("Spent " + interval + " on consuming");
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }

   }

   private static long getRandomInterval(int max) {
      return new Random().nextInt(max) * 1000L;
   }
}
