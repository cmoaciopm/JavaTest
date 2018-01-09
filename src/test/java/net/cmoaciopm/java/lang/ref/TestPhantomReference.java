package net.cmoaciopm.java.lang.ref;


import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestPhantomReference {

    @Test(expected = NoSuchFieldException.class)
    public void test() throws InterruptedException, NoSuchFieldException {
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        PhantomReference<Integer> phantomReference = new PhantomReference<>(new Integer(999), queue);
        assertThat(phantomReference.get(), is(nullValue()));
        assertThat(phantomReference.isEnqueued(), is(false));
        System.gc();
        // Wait for gc
        Thread.sleep(1000);
        assertThat(phantomReference.isEnqueued(), is(true));
        Reference reference;
        while ((reference = queue.remove()) != null) {
            assertThat(reference.get(), is(nullValue()));
            reference.getClass().getDeclaredField("referent");
        }
    }
}
