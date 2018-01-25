package net.cmoaciopm.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TestAtomicInteger {

   @Test
   public void test() {
      AtomicInteger number = new AtomicInteger();
      assertThat(number.get(), is(0));
      assertThat(number.incrementAndGet(), is(1));
      assertThat(number.decrementAndGet(), is(0));

      assertThat(number.getAndIncrement(), is(0));
      assertThat(number.get(), is(1));

      assertThat(number.getAndDecrement(), is(1));
      assertThat(number.get(), is(0));

      assertThat(number.getAndAdd(3), is(0));
      assertThat(number.get(), is(3));

      assertThat(number.addAndGet(2), is(5));
      assertThat(number.get(), is(5));

      assertThat(number.getAndSet(8), is(5));
      assertThat(number.get(), is(8));
   }

   @Test
   public void test2() {
      AtomicInteger number = new AtomicInteger(10);
      assertThat(number.compareAndSet(10, 16), is(true));
      assertThat(number.compareAndSet(16, 19), is(true));

      assertThat(number.compareAndSet(0, 23), is(false));
      assertThat(number.get(), is(19));
   }


   int rawNumber = 10;
   @Test
   public void test3() {
      AtomicInteger number = new AtomicInteger(10);
      int threadCount = 1000;
      Thread[] threads = new Thread[threadCount];
      for (int i = 0; i < threads.length; i++) {
         threads[i] = new Thread() {
            @Override
            public void run() {
               // Add random delay to increase race condition
               try {
                  Thread.sleep(new Random().nextInt(2) * 1000L);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               number.incrementAndGet();
               rawNumber++;
            }
         };
      }
      for (int i = 0; i < threads.length; i++) {
         threads[i].start();
      }
      for (int i = 0; i < threads.length; i++) {
         try {
            threads[i].join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      assertThat(number.get(), is(1010));
      assertThat(rawNumber, is(not(1010)));
   }
}
