package net.cmoaciopm.java.util;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestStack {

    @Test
    public void testPush() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.empty());
        stack.push("a");
        assertFalse(stack.empty());
        String element = stack.push("b");
        assertThat(element, is("b"));
    }

    @Test
    public void testPop() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertThat(stack.pop(), is("c"));
        assertThat(stack.pop(), is("b"));
        assertThat(stack.pop(), is("a"));
        assertTrue(stack.empty());
    }

    @Test
    public void testPeek() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertThat(stack.peek(), is("c"));
        assertThat(stack.peek(), is("c"));
    }

    @Test
    public void testSearch() {
        Stack<String> stack = new Stack<>();
        assertThat(stack.search("a"), is(-1));
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertThat(stack.search("a"), is(3));
        assertThat(stack.search("b"), is(2));
        assertThat(stack.search("c"), is(1));
    }
}
