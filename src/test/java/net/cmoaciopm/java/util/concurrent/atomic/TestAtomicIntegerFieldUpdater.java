package net.cmoaciopm.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAtomicIntegerFieldUpdater {

   static class Demo {
      public volatile int publicValue = 1;
      volatile int defaultValue = 2;
      protected volatile int protectedValue = 3;
      private volatile int privateValue = 4;
      public static volatile int publicStaticValue = 5;
   }

   @Test
   public void testPublicField() {
      Demo demo = new Demo();
      AtomicIntegerFieldUpdater<Demo> updater =
         AtomicIntegerFieldUpdater.newUpdater(Demo.class, "publicValue");
      assertThat(updater.incrementAndGet(demo), is(2));
   }

   @Test
   public void testDefaultField() {
      Demo demo = new Demo();
      AtomicIntegerFieldUpdater<Demo> updater =
         AtomicIntegerFieldUpdater.newUpdater(Demo.class, "defaultValue");
      assertThat(updater.incrementAndGet(demo), is(3));
   }

   @Test(expected = RuntimeException.class)
   public void testProtectedField() {
      Demo demo = new Demo();
      AtomicIntegerFieldUpdater<Demo> updater =
         AtomicIntegerFieldUpdater.newUpdater(Demo.class, "protectedValue");
      assertThat(updater.incrementAndGet(demo), is(4));
   }

   @Test(expected = RuntimeException.class)
   public void testPrivateField() {
      Demo demo = new Demo();
      AtomicIntegerFieldUpdater<Demo> updater =
         AtomicIntegerFieldUpdater.newUpdater(Demo.class, "privateValue");
      assertThat(updater.incrementAndGet(demo), is(5));
   }

   @Test(expected = IllegalArgumentException.class)
   public void test2() {
      Demo demo = new Demo();
      AtomicIntegerFieldUpdater<Demo> updater =
         AtomicIntegerFieldUpdater.newUpdater(Demo.class, "publicStaticValue");
      assertThat(updater.incrementAndGet(demo), is(6));
   }
}
