package net.cmoaciopm.java.lang;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestThreadLocal {

    static class ThreadID {
        private static final AtomicInteger nextID = new AtomicInteger(0);
        private static final ThreadLocal<Integer> threadID = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return nextID.getAndIncrement();
            }
        };

        public static int get() {
            return threadID.get();
        }

        public static void set(Integer integer) {
            threadID.set(integer);
        }
    }

    @Test
    public void test() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                assertThat(ThreadID.get(), anyOf(is(0), is(1)));
                ThreadID.set(100);
                assertThat(ThreadID.get(), is(100));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                assertThat(ThreadID.get(), anyOf(is(0), is(1)));
                ThreadID.set(200);
                assertThat(ThreadID.get(), is(200));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(ThreadID.get(), is(2));
    }
}
