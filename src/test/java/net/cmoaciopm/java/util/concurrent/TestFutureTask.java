package net.cmoaciopm.java.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestFutureTask {
    class MyCallable implements Callable {
        @Override
        public String call() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            String result = "Hello";
            return result;
        }
    }

    @Test
    public void test() {
        FutureTask<String> task = new FutureTask(new MyCallable());
        try {
            task.run();
            String result = task.get();
            assertThat(result, is("Hello"));
        } catch(CancellationException e) {

        } catch(InterruptedException e) {

        } catch(ExecutionException e) {

        }
    }
}