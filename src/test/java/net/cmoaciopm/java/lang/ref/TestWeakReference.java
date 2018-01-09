package net.cmoaciopm.java.lang.ref;

import org.junit.Test;

import java.lang.ref.WeakReference;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestWeakReference {

   private static final int M = 1024 * 1024;

   @Test
   public void test() {
      Runtime runtime = Runtime.getRuntime();
      long freeMemory = runtime.freeMemory()/M;

      WeakReference<Object> weakReference = new WeakReference<>(new byte[10 * M]);
      long freeMemory2 = runtime.freeMemory()/M;
      assertThat(freeMemory - freeMemory2, is(greaterThanOrEqualTo(10L)));

      System.gc();

      long freeMemory3 = runtime.freeMemory()/M;
      assertThat(freeMemory3, greaterThanOrEqualTo(freeMemory));
   }

   @Test
   public void testGet() throws InterruptedException {
      WeakReference<Object> weakReference = new WeakReference<>(new byte[10 * M]);
      assertThat(weakReference.get(), is(notNullValue()));
      System.gc();
      assertThat(weakReference.get(), is(nullValue()));
   }
}
