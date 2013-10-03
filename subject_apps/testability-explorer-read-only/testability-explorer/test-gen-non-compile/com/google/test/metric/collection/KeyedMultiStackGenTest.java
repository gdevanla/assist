package com.google.test.metric.collection;


import com.google.test.metric.collection.PopClosure;
import java.util.TreeSet;
import java.util.List;
import java.lang.Override;
import java.lang.String;
import java.lang.Integer;
import java.util.Set;
import com.google.test.metric.collection.KeyedMultiStack;
import com.google.test.metric.collection.KeyedMultiStack.ValueCompactor;
import junit.framework.TestCase;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class KeyedMultiStackGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.collection.KeyedMultiStack
	 */
	public void testKeyedMultiStack1095() throws Exception {
		KeyedMultiStack<String, Integer> var3617 = new KeyedMultiStack<String, Integer>(
				"", new KeyedMultiStack.ValueCompactor<Integer>());
		var3617.apply("", new Push(0));
		Set<String> var3619 = new TreeSet<String>();
		var3617.apply("", new PopClosure<String, Integer>() {
			@Override
			public List<Integer> pop(String var3620, List<Integer> var3621) {
				assertEquals("", var3620);
				assertEquals(1, var3621.size());
				assertEquals(new Integer(0), var3621.get(0));
				var3619.add(var3621.get(0).toString());
				return emptyList();
			}

			@Override
			public int getSize() {
				return 1;
			}
		});
		var3617.assertEmpty();
		String[] var3622 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p" };
		var3617.split("", asList(var3622));
		int var3623 = 0;
		var3617.apply(var3624, new Push(var3623++));
		var3617.join(asList(var3622), "L1");
		var3617.split("L1", asList(var3622));
		var3617.apply(var3625, new Push(var3623++));
		var3617.join(asList(var3622), "L2");
		var3617.split("L2", asList(var3622));
		var3617.apply(var3626, new Push(var3623++));
		var3617.join(asList(var3622), "L3");
		var3617.apply("L3", new NoopClosure(3));
	}

	/**
	 * Test method for the class com.google.test.metric.collection.KeyedMultiStack
	 */
	public void testKeyedMultiStack1096() throws Exception {
		KeyedMultiStack<String, Integer> var3617 = new KeyedMultiStack<String, Integer>(
				"", new KeyedMultiStack.ValueCompactor<Integer>());
		var3617.apply("", new Push(0));
		var3617.split("", asList("a", "b"));
		var3617.apply("a", new Push(1));
		var3617.apply("b", new Push(2));
		var3617.split("a", asList("join"));
		var3617.split("b", asList("join"));
		var3617.apply("join", new LoggingClosure(2));
		var3617.split("", asList("a", "b"));
		var3617.apply("a", new Push(0));
		var3617.join(asList("a", "b"), "c");
	}

	/**
	 * Test method for the class com.google.test.metric.collection.KeyedMultiStack
	 */
	public void testKeyedMultiStack1097() throws Exception {
		KeyedMultiStack<String, Integer> var3617 = new KeyedMultiStack<String, Integer>(
				"", new KeyedMultiStack.ValueCompactor<Integer>());
		var3617.apply("", new Push(0));
		var3617.toString();
		var3617.apply("", new Push(0));
		var3617.toString();
	}

	/**
	 * Test method for the class com.google.test.metric.collection.KeyedMultiStack
	 */
	public void testKeyedMultiStack1098() throws Exception {
		KeyedMultiStack<String, Integer> var3617 = new KeyedMultiStack<String, Integer>(
				"", new KeyedMultiStack.ValueCompactor<Integer>());
		var3617.split("X", asList("A", "B"));
		var3617.apply("", new Push(0));
		var3617.split("", asList("a", "b"));
		var3617.apply("a", new Push(1));
		var3617.apply("b", new Push(2));
		var3617.split("a", asList("join"));
		var3617.split("b", asList("join"));
		var3617.apply("join", new LoggingClosure(2));
	}

	/**
	 * Test method for the class com.google.test.metric.collection.KeyedMultiStack
	 */
	public void testKeyedMultiStack1099() throws Exception {
		KeyedMultiStack<String, Integer> var3617 = new KeyedMultiStack<String, Integer>(
				"", new KeyedMultiStack.ValueCompactor<Integer>());
		var3617.split("", asList("a", "b"));
		var3617.apply("a", new Push(0));
		var3617.join(asList("a", "b"), "c");
		var3617.apply("", new Push(0));
		Set<String> var3619 = new TreeSet<String>();
		var3617.apply("", new PopClosure<String, Integer>() {
			@Override
			public List<Integer> pop(String var3620, List<Integer> var3621) {
				assertEquals("", var3620);
				assertEquals(1, var3621.size());
				assertEquals(new Integer(0), var3621.get(0));
				var3619.add(var3621.get(0).toString());
				return emptyList();
			}

			@Override
			public int getSize() {
				return 1;
			}
		});
		var3617.assertEmpty();
	}

	public static class Push extends PopClosure<String, Integer> {
		private final List<Integer> items;

		public Push(Integer... integers) {
			items = Arrays.asList(integers);
		}

		@Override
		public int getSize() {
			return 0;
		}

		@Override
		public List<Integer> pop(String key, List<Integer> list) {
			return items;
		}
	}

	private class NoopClosure extends PopClosure<String, Integer> {
		private final int size;

		public NoopClosure(int size) {
			this.size = size;
		}

		@Override
		public int getSize() {
			return size;
		}

		@Override
		public List<Integer> pop(String key, List<Integer> list) {
			return emptyList();
		}
	}

	private class LoggingClosure extends PopClosure<String, Integer> {
		private final int size;

		public LoggingClosure(int size) {
			this.size = size;
		}

		@Override
		public List<Integer> pop(String key, List<Integer> value) {
			log.add(value.toString());
			return emptyList();
		}

		@Override
		public int getSize() {
			return size;
		}
	}
}