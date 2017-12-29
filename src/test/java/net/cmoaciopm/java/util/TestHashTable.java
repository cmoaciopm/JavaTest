package net.cmoaciopm.java.util;

import org.junit.Test;

import java.util.Hashtable;

public class TestHashTable {

   @Test(expected = NullPointerException.class)
   public void testNullKey() {
      Hashtable<String, String> table = new Hashtable<>();
      table.put(null, "abc");
   }

   @Test(expected = NullPointerException.class)
   public void testNullValue() {
      Hashtable<String, String> table = new Hashtable<>();
      table.put("abc", null);
   }
}
