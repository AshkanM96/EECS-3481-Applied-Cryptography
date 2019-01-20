package util;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * JUnit4 tests for SinglyLinkedList.
 * 
 * @author Ashkan Moatamed
 */
public class SinglyLinkedListTest {
	/**
	 * Dependencies: <code>
	 * 		1. util.SinglyLinkedList
	 * </code>
	 */

	/**
	 * Testing SinglyLinkedList&lt;T&gt;() ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test01() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		assertTrue("Correct size", (sll.size == 0) && (sll.size() == 0));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next == null);
	}

	/**
	 * Testing 1 SinglyLinkedList.addFirst(T).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test02() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(2);
		assertTrue("Correct size", (sll.size == 1) && (sll.size() == 1));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 2));
		assertTrue("Correct header next next", sll.header.next.next == null);
	}

	/**
	 * Testing 1 SinglyLinkedList.addFirst(T) and then 1 SinglyLinkedList.removeFirst(T).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test03() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(2);
		final Integer firstData = sll.removeFirst();
		assertTrue("Correct size", (sll.size == 0) && (sll.size() == 0));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next == null);
		assertTrue("Correct first data", (firstData != null) && (firstData == 2));
	}

	/**
	 * Testing 2 SinglyLinkedList.addFirst(T).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test04() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(2);
		sll.addFirst(1);
		assertTrue("Correct size", (sll.size == 2) && (sll.size() == 2));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 1));
		assertTrue("Correct header next next", sll.header.next.next != null);
		assertTrue("Correct header next next data",
				(sll.header.next.next.data != null) && (sll.header.next.next.data == 2));
		assertTrue("Correct header next next next", sll.header.next.next.next == null);
	}

	/**
	 * Testing 2 SinglyLinkedList.addFirst(T) and then 2 SinglyLinkedList.removeFirst(T).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test05() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(2);
		sll.addFirst(1);
		assertTrue("Correct size", (sll.size == 2) && (sll.size() == 2));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 1));
		assertTrue("Correct header next next", sll.header.next.next != null);
		assertTrue("Correct header next next data",
				(sll.header.next.next.data != null) && (sll.header.next.next.data == 2));
		assertTrue("Correct header next next next", sll.header.next.next.next == null);
		Integer firstData = sll.removeFirst();
		assertTrue("Correct size", (sll.size == 1) && (sll.size() == 1));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 2));
		assertTrue("Correct header next next", sll.header.next.next == null);
		assertTrue("Correct first data", (firstData != null) && (firstData == 1));
		firstData = sll.removeFirst();
		assertTrue("Correct size", (sll.size == 0) && (sll.size() == 0));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next == null);
		assertTrue("Correct first data", (firstData != null) && (firstData == 2));
	}

	/**
	 * Testing SinglyLinkedList.toArray(T[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test06() {
		{
			final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
			sll.addFirst(4);
			sll.addFirst(3);
			sll.addFirst(2);
			sll.addFirst(1);
			final Integer[] data = new Integer[] { 0, 0, 0, 0, 0, 0 };
			sll.toArray(data);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
			assertTrue("Correct toArray() result[4]", data[4] == null);
			assertTrue("Correct toArray() result[5]", (data[5] != null) && (data[5] == 0));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
			assertTrue("Correct toArray() result[4]", data[4] == null);
			assertTrue("Correct toArray() result[5]", (data[5] != null) && (data[5] == 0));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
			assertTrue("Correct toArray() result[4]", data[4] == null);
			assertTrue("Correct toArray() result[5]", (data[5] != null) && (data[5] == 0));
		}

		{
			final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
			sll.addFirst(4);
			sll.addFirst(3);
			sll.addFirst(2);
			sll.addFirst(1);
			final Integer[] data = new Integer[] { 0, 0, 0, 0 };
			sll.toArray(data);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 3));
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 4));
		}

		{
			final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
			sll.addFirst(4);
			sll.addFirst(3);
			sll.addFirst(2);
			sll.addFirst(1);
			final Integer[] data = new Integer[] { 0, 0 };
			sll.toArray(data);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
			sll.removeFirst();
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 1));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 2));
		}
	}

	/**
	 * Testing SinglyLinkedList.toArray(T[], int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test07() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(4);
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		{
			final Integer[] data = new Integer[] { 0, 0, 0, 0, 0, 0 };
			sll.toArray(data, 2);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
			assertTrue("Correct toArray() result[2]", data[2] == null);
			assertTrue("Correct toArray() result[3]", (data[3] != null) && (data[3] == 0));
			assertTrue("Correct toArray() result[4]", (data[4] != null) && (data[4] == 0));
			assertTrue("Correct toArray() result[5]", (data[5] != null) && (data[5] == 0));
		}

		{
			final Integer[] data = new Integer[] { 0, 0 };
			sll.toArray(data, 2);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
		}

		{
			final Integer[] data = new Integer[] { 0 };
			sll.toArray(data, 2);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
		}
	}

	/**
	 * Testing SinglyLinkedList.toArray(T[], int, int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test08() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(7);
		sll.addFirst(6);
		sll.addFirst(5);
		sll.addFirst(4);
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		{
			final Integer[] data = new Integer[] { 0, 0, 0, 0, 0 };
			sll.toArray(data, 2, 5);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 5));
			assertTrue("Correct toArray() result[3]", data[3] == null);
			assertTrue("Correct toArray() result[4]", (data[4] != null) && (data[4] == 0));
		}

		{
			final Integer[] data = new Integer[] { 0, 0, 0, 0 };
			sll.toArray(data, 2, 5);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 5));
			assertTrue("Correct toArray() result[3]", data[3] == null);
		}

		{
			final Integer[] data = new Integer[] { 0, 0, 0 };
			sll.toArray(data, 2, 5);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
			assertTrue("Correct toArray() result[2]", (data[2] != null) && (data[2] == 5));
		}

		{
			final Integer[] data = new Integer[] { 0, 0 };
			sll.toArray(data, 2, 5);
			assertTrue("Correct toArray() result[0]", (data[0] != null) && (data[0] == 3));
			assertTrue("Correct toArray() result[1]", (data[1] != null) && (data[1] == 4));
		}
	}

	/**
	 * Testing SinglyLinkedList&lt;T&gt;(SinglyLinkedList&lt;T&gt;) ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test09() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		final SinglyLinkedList<Integer> copy = new SinglyLinkedList<Integer>(sll);
		assertTrue("Correct size", (sll.size() == copy.size()) && (copy.size() == 3));
		assertTrue("Correct header", (copy.header != null) && (copy.header != sll.header));
		assertTrue("Correct header next", (copy.header.next != null) && (copy.header.next != sll.header.next));
		assertTrue("Correct header next data", (copy.header.next.data != null) && (copy.header.next.data == 1));
		assertTrue("Correct header next next",
				(copy.header.next.next != null) && (copy.header.next.next != sll.header.next.next));
		assertTrue("Correct header next next data",
				(copy.header.next.next.data != null) && (copy.header.next.next.data == 2));
		assertTrue("Correct header next next next",
				(copy.header.next.next.next != null) && (copy.header.next.next.next != sll.header.next.next.next));
		assertTrue("Correct header next next next data",
				(copy.header.next.next.next.data != null) && (copy.header.next.next.next.data == 3));
		assertTrue("Correct header next next next next", copy.header.next.next.next.next == null);
	}

	/**
	 * Testing iteration through SinglyLinkedList.iterator().
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test10() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		int expected = 1;
		for (Integer i : sll) {
			assertTrue("Expect " + expected, (i != null) && (i == expected));
			++expected;
		}
		assertTrue("Iterated over all", expected == 4);
	}

	/**
	 * Testing iteration through SinglyLinkedList.iterator(false).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test11() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		final SinglyLinkedList.SLLIterator<Integer> it = sll.iterator(false);
		int expected = 1;
		for (Integer i = null; it.hasNext();) {
			i = it.next();
			assertTrue("Expect " + expected, (i != null) && (i == expected));
			++expected;
		}
		assertTrue("Iterated over all", expected == 4);
	}

	/**
	 * Testing mutation through SinglyLinkedList.iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test12() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(0);
		sll.addFirst(0);
		sll.addFirst(0);

		final int[] data = { 1, 2, 3 };
		final SinglyLinkedList.SLLIterator<Integer> it = sll.iterator(true);
		Integer item = null;
		for (int i = 0; it.hasNext(); ++i) {
			item = it.next(data[i]);
			assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
		}
		assertTrue("Correct size", (sll.size == 3) && (sll.size() == 3));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 1));
		assertTrue("Correct header next next", sll.header.next.next != null);
		assertTrue("Correct header next next data",
				(sll.header.next.next.data != null) && (sll.header.next.next.data == 2));
		assertTrue("Correct header next next next", sll.header.next.next.next != null);
		assertTrue("Correct header next next next data",
				(sll.header.next.next.next.data != null) && (sll.header.next.next.next.data == 3));
		assertTrue("Correct header next next next next", sll.header.next.next.next.next == null);
	}

	/**
	 * Testing insertion through SinglyLinkedList.iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test13() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();

		final SinglyLinkedList.SLLIterator<Integer> it = sll.iterator(true);
		it.insert(3);
		it.insert(2);
		it.insert(1);

		assertTrue("Correct size", (sll.size == 3) && (sll.size() == 3));
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 1));
		assertTrue("Correct header next next", sll.header.next.next != null);
		assertTrue("Correct header next next data",
				(sll.header.next.next.data != null) && (sll.header.next.next.data == 2));
		assertTrue("Correct header next next next", sll.header.next.next.next != null);
		assertTrue("Correct header next next next data",
				(sll.header.next.next.next.data != null) && (sll.header.next.next.next.data == 3));
		assertTrue("Correct header next next next next", sll.header.next.next.next.next == null);

		int expected = 1;
		for (Integer i = null; it.hasNext();) {
			i = it.next();
			assertTrue("Expect " + expected, (i != null) && (i == expected));
			++expected;
		}
		assertTrue("Iterated over all", expected == 4);
	}

	/**
	 * Testing removal through SinglyLinkedList.iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test14() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		final SinglyLinkedList.SLLIterator<Integer> it = sll.iterator(true);
		try {
			it.prune();
			assertTrue("Exception on header removal", false);
		} catch (NoSuchElementException ex) {
			assertTrue("Exception on header removal", true);
		}
		assertTrue("Correct size", sll.size() == 3);

		Integer i = it.next();
		assertTrue("Expect 1", (i != null) && (i == 1));
		assertTrue("Correct size", sll.size() == 3);

		Integer item = it.prune();
		assertTrue("Removed 1", (item != null) && (item == 1));
		assertTrue("Correct size", sll.size() == 2);

		i = it.curr();
		assertTrue("Expect 2", (i != null) && (i == 2));
		assertTrue("Correct size", sll.size() == 2);

		i = it.next();
		assertTrue("Expect 3", (i != null) && (i == 3));
		assertTrue("Correct size", sll.size() == 2);

		try {
			it.next();
			assertTrue("Iterated over all", false);
		} catch (NoSuchElementException ex) {
			assertTrue("Iterated over all", true);
		}
		assertTrue("Correct size", sll.size() == 2);
	}

	/**
	 * Testing removal and then insertion through SinglyLinkedList.iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test15() {
		final SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
		sll.addFirst(3);
		sll.addFirst(2);
		sll.addFirst(1);

		final SinglyLinkedList.SLLIterator<Integer> it = sll.iterator(true);
		try {
			it.prune();
			assertTrue("Exception on header removal", false);
		} catch (NoSuchElementException ex) {
			assertTrue("Exception on header removal", true);
		}
		assertTrue("Correct size", sll.size() == 3);

		Integer i = it.next();
		assertTrue("Expect 1", (i != null) && (i == 1));
		assertTrue("Correct size", sll.size() == 3);

		Integer item = it.prune();
		assertTrue("Removed 1", (item != null) && (item == 1));
		assertTrue("Correct size", sll.size() == 2);

		i = it.curr();
		assertTrue("Expect 2", (i != null) && (i == 2));
		assertTrue("Correct size", sll.size() == 2);

		item = it.prune();
		assertTrue("Removed 2", (item != null) && (item == 2));
		assertTrue("Correct size", sll.size() == 1);

		i = it.curr();
		assertTrue("Expect 3", (i != null) && (i == 3));
		assertTrue("Correct size", sll.size() == 1);

		item = it.prune();
		assertTrue("Removed 3", (item != null) && (item == 3));
		assertTrue("Correct size", sll.size() == 0);

		try {
			it.prune();
			assertTrue("Exception on header removal", false);
		} catch (NoSuchElementException ex) {
			assertTrue("Exception on header removal", true);
		}
		assertTrue("Correct size", sll.size() == 0);

		it.insert(3);
		it.insert(2);
		it.insert(1);

		assertTrue("Correct size", sll.size() == 3);
		assertTrue("Correct header", sll.header != null);
		assertTrue("Correct header next", sll.header.next != null);
		assertTrue("Correct header next data", (sll.header.next.data != null) && (sll.header.next.data == 1));
		assertTrue("Correct header next next", sll.header.next.next != null);
		assertTrue("Correct header next next data",
				(sll.header.next.next.data != null) && (sll.header.next.next.data == 2));
		assertTrue("Correct header next next next", sll.header.next.next.next != null);
		assertTrue("Correct header next next next data",
				(sll.header.next.next.next.data != null) && (sll.header.next.next.next.data == 3));
		assertTrue("Correct header next next next next", sll.header.next.next.next.next == null);

		int expected = 1;
		while (it.hasNext()) {
			i = it.next();
			assertTrue("Expect " + expected, (i != null) && (i == expected));
			++expected;
		}
		assertTrue("Iterated over all", expected == 4);
	}
}
