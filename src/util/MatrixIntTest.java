package util;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit4 tests for MatrixInt.
 * 
 * @author Ashkan Moatamed
 */
public class MatrixIntTest {
	/**
	 * Dependencies: <code>
	 * 		1. util.MatrixInt
	 * 		2. util.ArrayUtil
	 * </code>
	 */

	/**
	 * Testing MatrixInt(int, int, int) ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test01() {
		final int[][] data = { { 5, 5, 5, 5 }, { 5, 5, 5, 5 }, { 5, 5, 5, 5 } };
		final MatrixInt m = new MatrixInt(3, 4, 5);
		assertTrue("Correct numRows", m.numRows == 3);
		assertTrue("Correct numCols", m.numCols == 4);
		assertTrue("Correct entries using isAllNumber", m.isAllNumber(5));
		assertTrue("Correct entries using data accessor", ArrayUtil.equals(m.data, m.data()));
		assertTrue("Correct entries using hardcoded array", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt(int, int) ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test02() {
		final int[][] data = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		final MatrixInt m = new MatrixInt(5, 3);
		assertTrue("Correct numRows", m.numRows == 5);
		assertTrue("Correct numCols", m.numCols == 3);
		assertTrue("Correct entries using isAllNumber", m.isAllNumber(0));
		assertTrue("Correct entries using isAllZero", m.isAllZero());
		assertTrue("Correct entries using data accessor", ArrayUtil.equals(m.data, m.data()));
		assertTrue("Correct entries using hardcoded array", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt(int[][]) ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test03() {
		final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final MatrixInt m = new MatrixInt(data);
		assertTrue("Equal data", ArrayUtil.equals(data, m.data));
	}

	/**
	 * Testing MatrixInt(MatrixInt) ctor.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test04() {
		final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final MatrixInt m = new MatrixInt(data);
		final MatrixInt copy = new MatrixInt(m);
		assertTrue("Equal data", ArrayUtil.equals(copy.data, m.data));
	}

	/**
	 * Testing MatrixInt.row(int[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test05() {
		final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		final MatrixInt m = MatrixInt.row(data);
		assertTrue("Correct numRows", m.numRows == 1);
		assertTrue("isRow", m.isRow());
		assertTrue("Correct numCols", m.numCols == data.length);
		assertTrue("Equal data", Arrays.equals(m.data[0], data));
	}

	/**
	 * Testing MatrixInt.column(int[]).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test06() {
		final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		final MatrixInt m = MatrixInt.column(data);
		assertTrue("Correct numRows", m.numRows == data.length);
		assertTrue("Correct numCols", m.numCols == 1);
		assertTrue("isColumn", m.isColumn());
		for (int i = 0; i != data.length; ++i) {
			assertTrue("Correct at (" + i + ", 0)", m.data[i][0] == data[i]);
		}
	}

	/**
	 * Testing MatrixInt.square(int[]) with 3-by-3 square.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test07() {
		final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		MatrixInt m = null;
		{
			final int[] tmp = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			m = MatrixInt.square(tmp);
		}
		assertTrue("Correct numRows", m.numRows == 3);
		assertTrue("Correct numCols", m.numCols == 3);
		assertTrue("isSquare", m.isSquare());
		assertTrue("Equal data", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt.square(int[]) with between 2-by-2 and 3-by-3 square.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test08() {
		final int[][] data = { { 1, 2 }, { 3, 4 } };
		MatrixInt m = null;
		{
			final int[] tmp = { 1, 2, 3, 4, 5, 6, 7, 8 };
			m = MatrixInt.square(tmp);
		}
		assertTrue("Correct numRows", m.numRows == 2);
		assertTrue("Correct numCols", m.numCols == 2);
		assertTrue("isSquare", m.isSquare());
		assertTrue("Equal data", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt.square(int[]) with between 3-by-3 and 4-by-4 square.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test09() {
		final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		MatrixInt m = null;
		{
			final int[] tmp = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			m = MatrixInt.square(tmp);
		}
		assertTrue("Correct numRows", m.numRows == 3);
		assertTrue("Correct numCols", m.numCols == 3);
		assertTrue("isSquare", m.isSquare());
		assertTrue("Equal data", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt.identity(int).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test10() {
		final int[][] data = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		final MatrixInt m = MatrixInt.identity(3);
		assertTrue("Correct numRows", m.numRows == 3);
		assertTrue("Correct numCols", m.numCols == 3);
		assertTrue("isSquare", m.isSquare());
		assertTrue("isIdentity", m.isIdentity());
		assertTrue("isTranspositionRow", m.isTranspositionRow());
		assertTrue("isTranspositionCol(true)", m.isTranspositionCol(true));
		assertTrue("isTranspositionCol(false)", m.isTranspositionCol(false));
		assertTrue("isInvertible", m.isInvertible());
		assertTrue("Correct determinant", m.determinant() == 1);
		assertTrue("Equal data", ArrayUtil.equals(m.data, data));
	}

	/**
	 * Testing MatrixInt::plusEquals(MatrixInt).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test11() {
		final int[][] m1_data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final MatrixInt m1 = new MatrixInt(m1_data);

		final int[][] m2_data = { { -1, 20, 73 }, { 47, -5, 64 }, { -78, 18, 109 } };
		final MatrixInt m2 = new MatrixInt(m2_data);

		final int[][] m3_data = { { 0, 22, 76 }, { 51, 0, 70 }, { -71, 26, 118 } };
		final MatrixInt m3 = m1.plusEquals(m2);
		assertTrue("m1: Not equal data", !ArrayUtil.equals(m1.data, m1_data));
		assertTrue("m2: Equal data", ArrayUtil.equals(m2.data, m2_data));
		assertTrue("m3: Equal data", ArrayUtil.equals(m3.data, m3_data));
		assertTrue("m1.data equals m3.data", ArrayUtil.equals(m1.data, m3.data));
		assertTrue("m1 == m3", m1 == m3);
		assertTrue("m1 equals m3", m1.equals(m3));
		assertTrue("m3 equals m1", m3.equals(m1));
	}

	/**
	 * Testing MatrixInt::plus(MatrixInt).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test12() {
		final int[][] m1_data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final MatrixInt m1 = new MatrixInt(m1_data);

		final int[][] m2_data = { { -1, 20, 73 }, { 47, -5, 64 }, { -78, 18, 109 } };
		final MatrixInt m2 = new MatrixInt(m2_data);

		final int[][] m3_data = { { 0, 22, 76 }, { 51, 0, 70 }, { -71, 26, 118 } };
		final MatrixInt m3 = m1.plus(m2);
		assertTrue("m1: Equal data", ArrayUtil.equals(m1.data, m1_data));
		assertTrue("m2: Equal data", ArrayUtil.equals(m2.data, m2_data));
		assertTrue("m3: Equal data", ArrayUtil.equals(m3.data, m3_data));
	}

	/**
	 * Testing forward iteration through MatrixInt::iterator().
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test13() {
		{
			final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
			final MatrixInt m = new MatrixInt(data);
			int expected = 1;
			for (Integer i : m) {
				assertTrue("Expect " + expected, (i != null) && (i == expected));
				++expected;
			}
			assertTrue("Iterated over all", expected == 10);
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.row(data);
			int expected = 1;
			for (Integer i : m) {
				assertTrue("Expect " + expected, (i != null) && (i == expected));
				++expected;
			}
			assertTrue("Iterated over all", expected == 10);
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.column(data);
			int expected = 1;
			for (Integer i : m) {
				assertTrue("Expect " + expected, (i != null) && (i == expected));
				++expected;
			}
			assertTrue("Iterated over all", expected == 10);
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.square(data);
			int expected = 1;
			for (Integer i : m) {
				assertTrue("Expect " + expected, (i != null) && (i == expected));
				++expected;
			}
			assertTrue("Iterated over all", expected == 10);
		}
	}

	/**
	 * Testing backward iteration through MatrixInt::iterator().
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test14() {
		{
			final int[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
			final MatrixInt m = new MatrixInt(data);
			final MatrixInt.MatrixIntIterator it = m.iterator().end();
			Integer i = null;
			for (int expected = 9; it.hasPrev(); --expected) {
				i = it.prev();
				assertTrue("Expect " + expected, (i != null) && (i == expected));
			}
			assertTrue("Iterated over all", (i != null) && (i == 1));
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.row(data);
			final MatrixInt.MatrixIntIterator it = m.iterator().end();
			Integer i = null;
			for (int expected = 9; it.hasPrev(); --expected) {
				i = it.prev();
				assertTrue("Expect " + expected, (i != null) && (i == expected));
			}
			assertTrue("Iterated over all", (i != null) && (i == 1));
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.column(data);
			final MatrixInt.MatrixIntIterator it = m.iterator().end();
			Integer i = null;
			for (int expected = 9; it.hasPrev(); --expected) {
				i = it.prev();
				assertTrue("Expect " + expected, (i != null) && (i == expected));
			}
			assertTrue("Iterated over all", (i != null) && (i == 1));
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.square(data);
			final MatrixInt.MatrixIntIterator it = m.iterator().end();
			Integer i = null;
			for (int expected = 9; it.hasPrev(); --expected) {
				i = it.prev();
				assertTrue("Expect " + expected, (i != null) && (i == expected));
			}
			assertTrue("Iterated over all", (i != null) && (i == 1));
		}
	}

	/**
	 * Testing MatrixInt::determinant(), MatrixInt::isInvertible().
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test15() {
		final int[][] m1_data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final MatrixInt m1 = new MatrixInt(m1_data);
		assertTrue("m1: Correct determinant", m1.determinant() == 0);
		assertTrue("m1: Not invertible", !m1.isInvertible());

		final int[][] m2_data = { { -1, 20, 73 }, { 47, -5, 64 }, { -78, 18, 109 } };
		final MatrixInt m2 = new MatrixInt(m2_data);
		assertTrue("m2: Correct determinant", m2.determinant() == -167315);
		assertTrue("m2: Invertible", m2.isInvertible());
		{
			final int[][] m2_inverse_data = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
			assertTrue("m2: Correct inverse", ArrayUtil.equals(m2.inverse().data, m2_inverse_data));
		}

		{
			final int[][] m3_data = { { -141, 64, 528 }, { -237, 163, 1266 }, { -333, 262, 2004 } };
			final MatrixInt m3 = MatrixInt.times(m1, m2);
			assertTrue("m1: Equal data", ArrayUtil.equals(m1.data, m1_data));
			assertTrue("m2: Equal data", ArrayUtil.equals(m2.data, m2_data));

			assertTrue("m3: Equal data", ArrayUtil.equals(m3.data, m3_data));
			assertTrue("m3: Correct determinant", m3.determinant() == 0);
			assertTrue("m3: Not invertible", !m3.isInvertible());
		}

		{
			final int[][] m3_data = { { 590, 682, 774 }, { 475, 581, 687 }, { 757, 806, 855 } };
			final MatrixInt m3 = MatrixInt.times(m2, m1);
			assertTrue("m1: Equal data", ArrayUtil.equals(m1.data, m1_data));
			assertTrue("m2: Equal data", ArrayUtil.equals(m2.data, m2_data));

			assertTrue("m3: Equal data", ArrayUtil.equals(m3.data, m3_data));
			assertTrue("m3: Correct determinant", m3.determinant() == 0);
			assertTrue("m3: Not invertible", !m3.isInvertible());
		}
	}

	/**
	 * Testing forward mutation through MatrixInt::iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test16() {
		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.row(data.length);
			final MatrixInt.MatrixIntIterator it = m.iterator(true);
			Integer item = null;
			for (int i = 0; it.hasNext(); ++i) {
				item = it.next(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			assertTrue("Equal data", Arrays.equals(m.data[0], data));
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.column(data.length);
			final MatrixInt.MatrixIntIterator it = m.iterator(true);
			Integer item = null;
			for (int i = 0; it.hasNext(); ++i) {
				item = it.next(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			for (int i = 0; i != data.length; ++i) {
				assertTrue("Correct at (" + i + ", 0)", m.data[i][0] == data[i]);
			}
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.square((int) Math.sqrt(data.length));
			final MatrixInt.MatrixIntIterator it = m.iterator(true);
			Integer item = null;
			for (int i = 0; it.hasNext(); ++i) {
				item = it.next(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			for (int rowNum = 0, i = 0; rowNum != m.numRows; ++rowNum) {
				for (int colNum = 0; colNum != m.numCols; ++colNum) {
					assertTrue("Correct at (" + rowNum + ", " + colNum + ")", m.data[rowNum][colNum] == data[i++]);
				}
			}
		}
	}

	/**
	 * Testing backward mutation through MatrixInt::iterator(true).
	 */
	@SuppressWarnings("static-method")
	@Test
	public void test17() {
		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.row(data.length);
			final MatrixInt.MatrixIntIterator it = m.iterator(true).end();
			Integer item = null;
			for (int i = data.length - 1; it.hasPrev(); --i) {
				item = it.prev(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			assertTrue("Equal data", Arrays.equals(m.data[0], data));
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.column(data.length);
			final MatrixInt.MatrixIntIterator it = m.iterator(true).end();
			Integer item = null;
			for (int i = data.length - 1; it.hasPrev(); --i) {
				item = it.prev(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			for (int i = 0; i != data.length; ++i) {
				assertTrue("Correct at (" + i + ", 0)", m.data[i][0] == data[i]);
			}
		}

		{
			final int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			final MatrixInt m = MatrixInt.square((int) Math.sqrt(data.length));
			final MatrixInt.MatrixIntIterator it = m.iterator(true).end();
			Integer item = null;
			for (int i = data.length - 1; it.hasPrev(); --i) {
				item = it.prev(data[i]);
				assertTrue("Correct entry at location " + i, (item != null) && (item == 0));
			}
			for (int rowNum = 0, i = 0; rowNum != m.numRows; ++rowNum) {
				for (int colNum = 0; colNum != m.numCols; ++colNum) {
					assertTrue("Correct at (" + rowNum + ", " + colNum + ")", m.data[rowNum][colNum] == data[i++]);
				}
			}
		}
	}
}
