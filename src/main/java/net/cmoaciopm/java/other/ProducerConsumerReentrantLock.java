package net.cmoaciopm.java.other;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerReentrantLock {

   private static final int MAX = 10;
   private static LinkedList<String> queue = new LinkedList<>();
   private static final int SPEED_PRODUCER = 2;
   private static final int SPEED_CONSUMER = 2;
   private static ReentrantLock lock = new ReentrantLock();

   public static void main(String[] args) {
      new ProducerThread().start();
      new ConsumerThread().start();
   }

   static class ProducerThread extends Thread {
      private int id = 0;
      @Override
      public void run() {
         while (true) {
            ProducerConsumerReentrantLock.sleep(getRandomInterval(SPEED_PRODUCER));

            lock.lock();
            queue.add(String.valueOf(++id));
            System.out.println("After producing, size is " + queue.size());
            lock.unlock();
         }
      }
   }

   static class ConsumerThread extends Thread {

      @Override
      public void run() {
         lock.lock();
         queue.poll();
         System.out.println("After consuming, size is " + queue.size());
         lock.unlock();
         ProducerConsumerReentrantLock.sleep(getRandomInterval(SPEED_CONSUMER));
      }

   }

   private static long getRandomInterval(int max) {
      return new Random().nextInt(max) * 1000L;
   }

   private static void sleep(long waitms) {
      try {
         Thread.sleep(waitms);
         System.out.println("Spent " + waitms + " on waiting");
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
