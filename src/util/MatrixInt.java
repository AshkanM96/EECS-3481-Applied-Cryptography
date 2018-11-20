package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Matrix of integers.
 * 
 * @author Ashkan Moatamed
 */
public class MatrixInt implements Iterable<Integer> {
	/**
	 * Dependencies: <code>
	 * 		1. util.BidirectionalIterator
	 * 		2. util.ArrayUtil
	 * 		3. util.MathUtil
	 * </code>
	 */

	/**
	 * ints are immutable. Therefore, it is "safe" to make the following final class attributes public.
	 */

	/**
	 * Number of rows.
	 */
	public final int numRows;

	/**
	 * Number of columns.
	 */
	public final int numCols;

	/**
	 * Two dimensional integer array containing the matrix entries.
	 */
	protected final int[][] data;

	/**
	 * Construct a MatrixInt object with the given number of rows and the given number of columns and
	 * the given integer in every entry.
	 * 
	 * @param numRows
	 *            the given number of rows
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numRows <= 0) || (numCols <= 0)</code>
	 */
	public MatrixInt(int numRows, int numCols, int n) throws IllegalArgumentException {
		if ((numRows <= 0) || (numCols <= 0)) {
			throw new IllegalArgumentException();
		}

		// Set this.
		this.data = new int[this.numRows = numRows][this.numCols = numCols];
		if (n != 0) {
			// Java new automatically initializes every entry to 0
			// therefore, only call fill if the fill value is not 0.
			this.fill(n);
		}
	}

	/**
	 * Construct a MatrixInt object with the given number of rows and the given number of columns and
	 * zeroes in every entry.
	 * 
	 * @param numRows
	 *            the given number of rows
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numRows <= 0) || (numCols <= 0)</code>
	 */
	public MatrixInt(int numRows, int numCols) throws IllegalArgumentException {
		this(numRows, numCols, 0);
	}

	/**
	 * Construct a MatrixInt object with <code>1</code> row and <code>1</code> column and the given
	 * integer in that entry.
	 * 
	 * @param n
	 *            the given integer
	 */
	public MatrixInt(int n) {
		this(1, 1, n);
	}

	/**
	 * Default ctor. <br>
	 * Equivalent to <code>new MatrixInt(1, 1, 0)</code>.
	 */
	public MatrixInt() {
		this(1, 1, 0);
	}

	/**
	 * Construct a MatrixInt object from the given two dimensional integer array.
	 * 
	 * @param data
	 *            the given two dimensional integer array
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (data[0].length == 0) || ((valid i) implies (data[i].length != data[0].length))</code>
	 */
	public MatrixInt(int[][] data) throws NullPointerException, IllegalArgumentException {
		// Number of rows must be positive.
		final int numRows = data.length;
		if (numRows == 0) {
			throw new IllegalArgumentException();
		}

		// Number of columns must be positive and the same on every row.
		final int numCols = data[0].length;
		if (numCols == 0) {
			throw new IllegalArgumentException();
		}
		for (int rowNum = 1; rowNum != numRows; ++rowNum) {
			if (data[rowNum].length != numCols) {
				throw new IllegalArgumentException();
			}
		}

		// Set this.
		this.data = new int[this.numRows = numRows][this.numCols = numCols];
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			System.arraycopy(data[rowNum], 0, this.data[rowNum], 0, this.numCols);
		}
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public MatrixInt(MatrixInt other) throws NullPointerException {
		this.data = new int[this.numRows = other.numRows][this.numCols = other.numCols];
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			System.arraycopy(other.data[rowNum], 0, this.data[rowNum], 0, this.numCols);
		}
	}

	/**
	 * Row matrix static factory: construct a MatrixInt object from the given one dimensional integer
	 * array. The resulting object will take <code>1</code> as its number of rows and
	 * <code>data.length</code> as its number of columns. It will contain all of the entries of the
	 * given integer array.
	 * 
	 * @param data
	 *            the given one dimensional integer array
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length == 0</code>
	 */
	public static MatrixInt row(int[] data) throws NullPointerException, IllegalArgumentException {
		final MatrixInt result = new MatrixInt(1, data.length);
		final int[] result_row = result.data[0];
		for (int colNum = 0; colNum != data.length; ++colNum) {
			result_row[colNum] = data[colNum];
		}
		return result;
	}

	/**
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @return <code>new MatrixInt(1, numCols)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>numCols <= 0</code>
	 */
	public static MatrixInt row(int numCols) throws IllegalArgumentException {
		return new MatrixInt(1, numCols);
	}

	/**
	 * Column matrix static factory: construct a MatrixInt object from the given one dimensional integer
	 * array. The resulting object will take <code>data.length</code> as its number of rows and
	 * <code>1</code> as its number of columns. It will contain all of the entries of the given integer
	 * array.
	 * 
	 * @param data
	 *            the given one dimensional integer array
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length == 0</code>
	 */
	public static MatrixInt column(int[] data) throws NullPointerException, IllegalArgumentException {
		final MatrixInt result = new MatrixInt(data.length, 1);
		for (int rowNum = 0; rowNum != data.length; ++rowNum) {
			result.data[rowNum][0] = data[rowNum];
		}
		return result;
	}

	/**
	 * @param numRows
	 *            the given number of rows
	 * 
	 * @return <code>new MatrixInt(numRows, 1)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>numRows <= 0</code>
	 */
	public static MatrixInt column(int numRows) throws IllegalArgumentException {
		return new MatrixInt(numRows, 1);
	}

	/**
	 * Square matrix static factory: construct a MatrixInt object from the given one dimensional integer
	 * array. The resulting object will take <code>(int) Math.sqrt(data.length)</code> as its number of
	 * rows and number of columns. Therefore, some of the right-end entries of the given integer array
	 * will be ignored if its length is not a perfect square. Specifically, the last accessed index will
	 * be <code>((int) Math.sqrt(data.length)) * ((int) Math.sqrt(data.length)) - 1</code>.
	 * 
	 * @param data
	 *            the given one dimensional integer array
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length == 0</code>
	 */
	public static MatrixInt square(int[] data) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		}

		final int side = (int) Math.sqrt(data.length);
		final MatrixInt result = new MatrixInt(side, side);
		int[] result_row = null;
		for (int rowNum = 0, data_index = 0; rowNum != side; data_index = (++rowNum) * side) {
			result_row = result.data[rowNum];
			for (int colNum = 0; colNum != side; ++colNum, ++data_index) {
				result_row[colNum] = data[data_index];
			}
		}
		return result;
	}

	/**
	 * @param side
	 *            the given number of rows/columns
	 * 
	 * @return <code>new MatrixInt(side, side)</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public static MatrixInt square(int side) throws IllegalArgumentException {
		return new MatrixInt(side, side);
	}

	/**
	 * Identity matrix static factory: construct a MatrixInt object which is an identity matrix with the
	 * given number of rows/columns.
	 * 
	 * @param side
	 *            the given number of rows/columns
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public static MatrixInt identity(int side) throws IllegalArgumentException {
		// Identity matrix has ones on the main diagonal and zeroes everywhere else.
		final MatrixInt result = new MatrixInt(side, side);
		for (int rowNum = 0; rowNum != side; ++rowNum) {
			result.data[rowNum][rowNum] = 1;
		}
		return result;
	}

	/**
	 * @return <code>this.numRows == 1</code>.
	 */
	public boolean isRow() {
		return (this.numRows == 1);
	}

	/**
	 * @return <code>this.numCols == 1</code>.
	 */
	public boolean isColumn() {
		return (this.numCols == 1);
	}

	/**
	 * @return <code>(this.numRows == 1) && (this.numCols == 1)</code>.
	 */
	public boolean isEntry() {
		return ((this.numRows == 1) && (this.numCols == 1));
	}

	/**
	 * @return <code>this.numRows == this.numCols</code>.
	 */
	public boolean isSquare() {
		return (this.numRows == this.numCols);
	}

	/**
	 * @return <code>((long) this.numRows) * this.numCols</code>.
	 */
	public long size() {
		/**
		 * The maximum value for <code>this.numRows</code> and <code>this.numCols</code> is
		 * <code>2<sup>31</sup> - 1 (i.e., Integer.MAX_VALUE)</code> which when squared gives
		 * <code>2<sup>62</sup> + 1 - 2<sup>32</sup></code>. However, this value is not representable by an
		 * int but is representable by a long since <code>Long.MAX_VALUE == 2<sup>63</sup> - 1</code>.
		 */
		return (((long) this.numRows) * this.numCols);
	}

	/**
	 * @return <code>Math.multiplyExact(this.numRows, this.numCols)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>(((long) this.numRows) * this.numCols) > Integer.MAX_VALUE</code>
	 */
	public int sizeExact() throws ArithmeticException {
		return Math.multiplyExact(this.numRows, this.numCols);
	}

	/**
	 * Compute the unknown matrix dimension given the size and one of the dimensions.
	 * 
	 * @param size
	 *            the size of the matrix (i.e., numRows * numCols)
	 * 
	 * @param dim
	 *            the known matrix dimension (i.e., either numRows or numCols)
	 * 
	 * @return The unknown matrix dimension (i.e., <code>(int) Math.ceil(((double) size) / dim)</code>).
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(size <= 0) || (dim <= 0) || (dim > size)</code>
	 */
	public static int otherDim(long size, int dim) throws IllegalArgumentException {
		if (size <= 0) {
			throw new IllegalArgumentException();
		} else if ((dim <= 0) || (dim > size)) {
			throw new IllegalArgumentException();
		}
		return ((int) Math.ceil(((double) size) / dim));
	}

	/**
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @return <code>(this.numRows == other.numRows) && (this.numCols == other.numCols)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public boolean sameDim(MatrixInt other) throws NullPointerException {
		return ((this.numRows == other.numRows) && (this.numCols == other.numCols));
	}

	/**
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.sameDim(other)</code>
	 */
	public void sameDimEnforce(MatrixInt other) throws NullPointerException, IllegalArgumentException {
		if (!this.sameDim(other)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return A shallow copy of <code>this.data</code>.
	 */
	public int[][] data() {
		final int[][] result = new int[this.numRows][this.numCols];
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			System.arraycopy(this.data[rowNum], 0, result[rowNum], 0, this.numCols);
		}
		return result;
	}

	/**
	 * @param rowNum
	 *            the given row index
	 * 
	 * @param colNum
	 *            the given column index
	 * 
	 * @return <code>this.data[rowNum][colNum]</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If
	 *             <code>(rowNum < 0) || (rowNum >= this.numRows) || (colNum < 0) || (colNum >= this.numCols)</code>
	 */
	public int get(int rowNum, int colNum) throws IndexOutOfBoundsException {
		return this.data[rowNum][colNum];
	}

	/**
	 * <code>this.data[rowNum][colNum] = entry</code>.
	 * 
	 * @param rowNum
	 *            the given row index
	 * 
	 * @param colNum
	 *            the given column index
	 * 
	 * @param entry
	 *            the given entry
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If
	 *             <code>(rowNum < 0) || (rowNum >= this.numRows) || (colNum < 0) || (colNum >= this.numCols)</code>
	 */
	public void set(int rowNum, int colNum, int entry) throws IndexOutOfBoundsException {
		this.data[rowNum][colNum] = entry;
	}

	/**
	 * Set every entry of <code>this.data</code> to the given integer.
	 * 
	 * @param n
	 *            the given integer
	 */
	public void fill(int n) {
		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] = n;
			}
		}
	}

	/**
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>true</code> if and only if every entry of <code>this.data</code> is equal to the
	 *         given integer.
	 */
	public boolean isAllNumber(int n) {
		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				if (row[colNum] != n) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return <code>this.isAllNumber(0)</code>.
	 */
	public boolean isAllZero() {
		return this.isAllNumber(0);
	}

	/**
	 * @return <code>true</code> if and only if <code>this</code> is an identity matrix.
	 */
	public boolean isIdentity() {
		// Identity matrix is a square.
		if (!this.isSquare()) {
			return false;
		}

		// Identity matrix has ones on the main diagonal and zeroes everywhere else.
		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				if (rowNum == colNum) {
					// Ones on the main diagonal.
					if (row[colNum] != 1) {
						return false;
					}
				} else { // rowNum != colNum
					// Zeroes everywhere else.
					if (row[colNum] != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * A row transposition matrix, when multiplied by a row matrix, results in a new row matrix that
	 * contains the same entries as the original with a potentially different order.
	 * 
	 * @return <code>true</code> if and only if <code>this</code> is a row transposition matrix (i.e., a
	 *         square matrix that has exactly one <code>1</code> on each row and zeroes everywhere
	 *         else).
	 */
	public boolean isTranspositionRow() {
		// Row transposition matrix is a square.
		if (!this.isSquare()) {
			return false;
		}

		// Row transposition matrix has one 1 on each row and zeroes everywhere else.
		boolean haveSeenOneOnRow = false;
		int[] row = null;
		for (int rowNum = 0, entry = 0; rowNum != this.numRows; ++rowNum, haveSeenOneOnRow = false) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				entry = row[colNum];
				if (entry == 1) {
					// Matrix has at least two 1 on row indexed by rowNum.
					if (haveSeenOneOnRow) {
						return false;
					}
					haveSeenOneOnRow = true;
				} else if (entry != 0) {
					return false;
				}
			}
			// Matrix does not have any 1 on row indexed by rowNum.
			if (!haveSeenOneOnRow) {
				return false;
			}
		}
		return true;
	}

	/**
	 * A column transposition matrix, when multiplied by a column matrix, results in a new column matrix
	 * that contains the same entries as the original with a potentially different order. <br>
	 * This method has two possible implementations. The caller can decide which to execute using the
	 * boolean argument. <br>
	 * <br>
	 * 
	 * The first is the column by column iteration algorithm that uses <code>Big-Theta(1)</code> extra
	 * memory and its runtime is <code>Big-O(this.numCols * this.numRows)</code>. It, however, will have
	 * many more page faults when compared to the second implementation since it iterates column by
	 * column. <br>
	 * <br>
	 * 
	 * The second is the row by row iteration algorithm that uses <code>Big-Theta(this.numCols)</code>
	 * extra memory and its runtime is
	 * <code>Big-O(this.numCols * this.numRows) + Big-O(this.numCols) = Big-O(this.numCols * this.numRows)</code>.
	 * 
	 * @param iterateColByCol
	 *            specifies whether the column by column iteration algorithm should be used
	 * 
	 * @return <code>true</code> if and only if <code>this</code> is a column transposition matrix
	 *         (i.e., a square matrix that has exactly one <code>1</code> on each column and zeroes
	 *         everywhere else).
	 */
	public boolean isTranspositionCol(boolean iterateColByCol) {
		// Column transposition matrix is a square.
		if (!this.isSquare()) {
			return false;
		}

		if (iterateColByCol) {
			// Column transposition matrix has one 1 on each row and zeroes everywhere else.
			boolean haveSeenOneOnCol = false;
			for (int colNum = 0, entry = 0; colNum != this.numCols; ++colNum, haveSeenOneOnCol = false) {
				for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
					entry = this.data[rowNum][colNum];
					if (entry == 1) {
						// Matrix has at least two 1 on column indexed by colNum.
						if (haveSeenOneOnCol) {
							return false;
						}
						haveSeenOneOnCol = true;
					} else if (entry != 0) {
						return false;
					}
				}
				// Matrix does not have any 1 on column indexed by colNum.
				if (!haveSeenOneOnCol) {
					return false;
				}
			}
			return true;
		}

		// Column transposition matrix has one 1 on each row and zeroes everywhere else.
		final boolean[] haveSeenOneOnCol = new boolean[this.numCols];
		int[] row = null;
		for (int rowNum = 0, entry = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				entry = row[colNum];
				if (entry == 1) {
					// Matrix has at least two 1 on column indexed by colNum.
					if (haveSeenOneOnCol[colNum]) {
						return false;
					}
					haveSeenOneOnCol[colNum] = true;
				} else if (entry != 0) {
					return false;
				}
			}
		}
		for (int colNum = 0; colNum != this.numCols; ++colNum) {
			// Matrix does not have any 1 on column indexed by colNum.
			if (!haveSeenOneOnCol[colNum]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <code>operator+=(MatrixInt)</code>.
	 * 
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @return <code>this</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.sameDim(other)</code>
	 */
	public MatrixInt plusEquals(MatrixInt other) throws NullPointerException, IllegalArgumentException {
		this.sameDimEnforce(other);
		int[] row = null, other_row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			other_row = other.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] += other_row[colNum];
			}
		}
		return this;
	}

	/**
	 * <code>operator+(MatrixInt)</code>.
	 * 
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @return <code>new MatrixInt(this).plusEquals(other)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.sameDim(other)</code>
	 */
	public MatrixInt plus(MatrixInt other) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		this.sameDimEnforce(other);
		return new MatrixInt(this).plusEquals(other);
	}

	/**
	 * <code>operator+=(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>this</code>.
	 */
	public MatrixInt plusEquals(int n) {
		if (n != 0) {
			int[] row = null;
			for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
				row = this.data[rowNum];
				for (int colNum = 0; colNum != this.numCols; ++colNum) {
					row[colNum] += n;
				}
			}
		}
		return this;
	}

	/**
	 * <code>operator+(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>new MatrixInt(this).plusEquals(n)</code>.
	 */
	public MatrixInt plus(int n) {
		return new MatrixInt(this).plusEquals(n);
	}

	/**
	 * <code>operator-=(MatrixInt)</code>.
	 * 
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @return <code>this</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.sameDim(other)</code>
	 */
	public MatrixInt minusEquals(MatrixInt other) throws NullPointerException, IllegalArgumentException {
		this.sameDimEnforce(other);
		int[] row = null, other_row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			other_row = other.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] -= other_row[colNum];
			}
		}
		return this;
	}

	/**
	 * <code>operator-(MatrixInt)</code>.
	 * 
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @return <code>new MatrixInt(this).minusEquals(other)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.sameDim(other)</code>
	 */
	public MatrixInt minus(MatrixInt other) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a copy construction.
		this.sameDimEnforce(other);
		return new MatrixInt(this).minusEquals(other);
	}

	/**
	 * <code>operator-=(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>this</code>.
	 */
	public MatrixInt minusEquals(int n) {
		if (n != 0) {
			int[] row = null;
			for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
				row = this.data[rowNum];
				for (int colNum = 0; colNum != this.numCols; ++colNum) {
					row[colNum] -= n;
				}
			}
		}
		return this;
	}

	/**
	 * <code>operator-(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>new MatrixInt(this).minusEquals(n)</code>.
	 */
	public MatrixInt minus(int n) {
		return new MatrixInt(this).minusEquals(n);
	}

	/**
	 * <code>operator*(MatrixInt, MatrixInt)</code>. Run time is
	 * <code>Big-Theta(lhs.numRows * rhs.numCols * lhs.numCols)</code>.
	 * 
	 * @param lhs
	 *            the given left hand side matrix
	 * 
	 * @param rhs
	 *            the given right hand side matrix
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.numCols != rhs.numRows</code>
	 */
	public static MatrixInt times(MatrixInt lhs, MatrixInt rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.numCols != rhs.numRows) {
			throw new IllegalArgumentException();
		}

		final MatrixInt result = new MatrixInt(lhs.numRows, rhs.numCols);
		int[] result_row = null, lhs_row = null;
		for (int i = 0; i != lhs.numRows; ++i) {
			result_row = result.data[i];
			lhs_row = lhs.data[i];
			for (int j = 0; j != rhs.numCols; ++j) {
				for (int k = 0; k != lhs.numCols; ++k) {
					result_row[j] += lhs_row[k] * rhs.data[k][j];
				}
			}
		}
		return result;
	}

	/**
	 * <code>operator*=(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>this</code>.
	 */
	public MatrixInt timesEquals(int n) {
		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] *= n;
			}
		}
		return this;
	}

	/**
	 * <code>operator*(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>new MatrixInt(this).timesEquals(n)</code>.
	 */
	public MatrixInt times(int n) {
		return new MatrixInt(this).timesEquals(n);
	}

	/**
	 * <code>operator/=(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>this</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>n == 0</code>
	 */
	public MatrixInt divideEquals(int n) throws ArithmeticException {
		if (n == 0) {
			throw new ArithmeticException();
		}

		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] /= n;
			}
		}
		return this;
	}

	/**
	 * <code>operator/(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>new MatrixInt(this).divideEquals(n)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>n == 0</code>
	 */
	public MatrixInt divide(int n) throws ArithmeticException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (n == 0) {
			throw new ArithmeticException();
		}
		return new MatrixInt(this).divideEquals(n);
	}

	/**
	 * <code>operator%=(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>this</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>n <= 0</code>
	 */
	public MatrixInt modEquals(int n) throws ArithmeticException {
		if (n <= 0) {
			throw new ArithmeticException();
		}

		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				row[colNum] = MathUtil.mod(row[colNum], n);
			}
		}
		return this;
	}

	/**
	 * <code>operator%(int)</code>.
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return <code>new MatrixInt(this).modEquals(n)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>n <= 0</code>
	 */
	public MatrixInt mod(int n) throws ArithmeticException {
		// Even though the following is a repeated check, it'll save a copy construction.
		if (n <= 0) {
			throw new ArithmeticException();
		}
		return new MatrixInt(this).modEquals(n);
	}

	/**
	 * @return The transpose of <code>this</code>.
	 */
	public MatrixInt transpose() {
		final MatrixInt result = new MatrixInt(this.numCols, this.numRows);
		int[] result_row = null;
		for (int rowNum = 0; rowNum != result.numRows; ++rowNum) {
			result_row = result.data[rowNum];
			for (int colNum = 0; colNum != result.numCols; ++colNum) {
				result_row[colNum] = this.data[colNum][rowNum];
			}
		}
		return result;
	}

	/**
	 * @return The trace of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public long trace() throws IllegalStateException {
		if (!this.isSquare()) {
			throw new IllegalStateException();
		}

		long result = 0;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			result += this.data[rowNum][rowNum];
		}
		return result;
	}

	/**
	 * Get the submatrix of <code>this</code> having excluded the row and the column indexed by the
	 * given indices respectively. If the row exclusion index is equal to the number of rows, then no
	 * row will be excluded. Similarly, if the column exclusion index is equal to the number of columns,
	 * then no column will be excluded.
	 * 
	 * @param excludingRow
	 *            the given index of the row to be excluded
	 * 
	 * @param excludingCol
	 *            the given index of the column to be excluded
	 * 
	 * @return The resulting MatrixInt object.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(excludingRow < 0) || (excludingRow > this.numRows)
	 *             || (excludingCol < 0) || (excludingCol > this.numCols)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>((excludingRow != this.numRows) && (this.numRows == 1))
	 *             || ((excludingCol != this.numCols) && (this.numCols == 1))</code>
	 */
	public MatrixInt submatrix(int excludingRow, int excludingCol)
			throws IndexOutOfBoundsException, IllegalArgumentException {
		if ((excludingRow < 0) || (excludingRow > this.numRows) || (excludingCol < 0)
				|| (excludingCol > this.numCols)) {
			throw new IndexOutOfBoundsException();
		}

		// Special cases.
		if (excludingRow == this.numRows) {
			if (excludingCol == this.numCols) {
				// Special case denoting no row exclusion and no column exclusion.
				return new MatrixInt(this);
			} // excludingCol != this.numCols

			// Special case denoting no row exclusion.
			final MatrixInt result = new MatrixInt(this.numRows, this.numCols - 1);
			int[] row = null, result_row = null;
			for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
				row = this.data[rowNum];
				result_row = result.data[rowNum];
				for (int colNum = 0; colNum != excludingCol; ++colNum) {
					result_row[colNum] = row[colNum];
				}
				// Not copying row[excludingCol].
				for (int colNum = excludingCol + 1; colNum != this.numCols; ++colNum) {
					result_row[colNum - 1] = row[colNum];
				}
			}
			return result;
		} else if (excludingCol == this.numCols) { // excludingRow != this.numRows
			// Special case denoting no column exclusion.
			final MatrixInt result = new MatrixInt(this.numRows - 1, this.numCols);
			int[] row = null, result_row = null;
			for (int rowNum = 0; rowNum != excludingRow; ++rowNum) {
				row = this.data[rowNum];
				result_row = result.data[rowNum];
				for (int colNum = 0; colNum != this.numCols; ++colNum) {
					result_row[colNum] = row[colNum];
				}
			}
			// Not copying this.data[excludingRow][colNum].
			for (int rowNum = excludingRow + 1; rowNum != this.numRows; ++rowNum) {
				row = this.data[rowNum];
				result_row = result.data[rowNum - 1];
				for (int colNum = 0; colNum != this.numCols; ++colNum) {
					result_row[colNum] = row[colNum];
				}
			}
			return result;
		} // (excludingRow != this.numRows) && (excludingCol != this.numCols)

		// General case denoting excluding row indexed by excludingRow
		// and excluding column indexed by excludingCol.
		final MatrixInt result = new MatrixInt(this.numRows - 1, this.numCols - 1);
		int[] row = null, result_row = null;
		for (int rowNum = 0, result_rowNum = -1; rowNum != this.numRows; ++rowNum) {
			if (rowNum != excludingRow) {
				row = this.data[rowNum];
				result_row = result.data[++result_rowNum];
				for (int colNum = 0, result_colNum = -1; colNum != this.numCols; ++colNum) {
					if (colNum != excludingCol) {
						result_row[++result_colNum] = row[colNum];
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get the submatrix of <code>this</code> having excluded the row indexed by the given index. If the
	 * row exclusion index is equal to the number of rows, then no row will be excluded.
	 * 
	 * @param row
	 *            the given index of the row to be excluded
	 * 
	 * @return <code>this.submatrix(row, this.numCols)</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(row < 0) || (row > this.numRows)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(row != this.numRows) && (this.numRows == 1)</code>
	 */
	public MatrixInt excludingRow(int row) throws IndexOutOfBoundsException, IllegalArgumentException {
		return this.submatrix(row, this.numCols);
	}

	/**
	 * Get the submatrix of <code>this</code> having excluded the column indexed by the given index. If
	 * the column exclusion index is equal to the number of columns, then no column will be excluded.
	 * 
	 * @param col
	 *            the given index of the column to be excluded
	 * 
	 * @return <code>this.submatrix(this.numRows, col)</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(col < 0) || (col > this.numCols)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(col != this.numCols) && (this.numCols == 1)</code>
	 */
	public MatrixInt excludingCol(int col) throws IndexOutOfBoundsException, IllegalArgumentException {
		return this.submatrix(this.numRows, col);
	}

	/**
	 * @return The determinant of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public int determinant() throws IllegalStateException {
		if (!this.isSquare()) {
			throw new IllegalStateException();
		}
		return ((this.numRows == 1) ? this.data[0][0] : MatrixInt.determinantHelper(this.numRows, this.data));
	}

	/**
	 * Precondition: <code>side > 1</code> <br>
	 * Precondition: <code>data != null</code> <br>
	 * Precondition: <code>data.length == side</code> <br>
	 * Precondition: <code>(valid i) implies (data[i].length == side)</code>
	 * 
	 * @param side
	 *            the given side
	 * 
	 * @param data
	 *            the given two dimensional integer array
	 * 
	 * @return The determinant of <code>data</code>.
	 */
	protected static int determinantHelper(int side, int[][] data) {
		// Base case.
		if (side == 2) {
			final int[] first_row = data[0], second_row = data[1];
			return (first_row[0] * second_row[1] - first_row[1] * second_row[0]);
		}

		// General recursive case.
		int result = 0;
		final int[] first_row = data[0];
		final int side_minus_one = side - 1;
		final int[][] sub = new int[side_minus_one][side_minus_one];
		int[] row = null, sub_row = null;
		for (int colNum = 0, sign = 1; colNum != side; ++colNum, sign *= -1 /* Alternate signs. */) {
			for (int rowNum = 1; rowNum != side; ++rowNum) {
				row = data[rowNum];
				sub_row = sub[rowNum - 1];
				for (int data_colNum = 0, sub_colNum = 0; data_colNum != side; ++data_colNum) {
					if (data_colNum != colNum) {
						sub_row[sub_colNum++] = row[data_colNum];
					}
				}
			}
			result += sign * first_row[colNum] * MatrixInt.determinantHelper(side_minus_one, sub);
		}
		return result;
	}

	/**
	 * @return The minors of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public MatrixInt minors() throws IllegalStateException {
		if (!this.isSquare()) {
			throw new IllegalStateException();
		}

		final MatrixInt result = new MatrixInt(this.numRows, this.numCols);
		int[] result_row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			result_row = result.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				result_row[colNum] = this.submatrix(rowNum, colNum).determinant();
			}
		}
		return result;
	}

	/**
	 * @return The cofactor of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public MatrixInt cofactor() throws IllegalStateException {
		if (!this.isSquare()) {
			throw new IllegalStateException();
		}

		final MatrixInt result = new MatrixInt(this.numRows, this.numCols);
		int[] result_row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			result_row = result.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				result_row[colNum] = (MathUtil.isEven(rowNum + colNum) ? 1 : -1)
						* this.submatrix(rowNum, colNum).determinant();
			}
		}
		return result;
	}

	/**
	 * @return The adjugate of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public MatrixInt adjugate() throws IllegalStateException {
		// return this.cofactor().transpose();

		if (!this.isSquare()) {
			throw new IllegalStateException();
		}

		final MatrixInt result = new MatrixInt(this.numRows, this.numCols);
		int[] result_row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			result_row = result.data[rowNum];
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				result_row[colNum] = (MathUtil.isEven(colNum + rowNum) ? 1 : -1)
						* this.submatrix(colNum, rowNum).determinant();
			}
		}
		return result;
	}

	/**
	 * @return <code>this.isSquare() && (this.determinant() != 0)</code>.
	 */
	public boolean isInvertible() {
		return (this.isSquare() && (this.determinant() != 0));
	}

	/**
	 * Compute the determinant and the inverse of <code>this</code> and return them encapsulated in an
	 * InverseInfo object. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>(Result.determinant != 0) if and only if (Result.inverse != null)</code>
	 * 
	 * @return The resulting InverseInfo object.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public InverseInfo detInv() throws IllegalStateException {
		final int determinant = this.determinant();
		if (determinant == 0) {
			// null to denote the nonexistence of this matrix's inverse.
			return new InverseInfo(0, null);
		}
		return new InverseInfo(determinant, this.adjugate().divideEquals(determinant));
	}

	/**
	 * @return The inverse of <code>this</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isInvertible()</code>
	 */
	public MatrixInt inverse() throws IllegalStateException {
		final InverseInfo info = this.detInv();
		if (info.inverse == null) {
			throw new IllegalStateException();
		}
		return info.inverse;
	}

	/**
	 * @param m
	 *            the given modulus
	 * 
	 * @return <code>this.isSquare() && (MathUtil.gcd(this.determinant(), m) == 1)</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 */
	public boolean isInvertibleMod(int m) throws ArithmeticException {
		if (m <= 0) {
			throw new ArithmeticException();
		}
		return (this.isSquare() && (MathUtil.gcd(this.determinant(), m) == 1));
	}

	/**
	 * Compute the determinant and the inverse of <code>this</code> and return them encapsulated in an
	 * InverseInfo object. The inverse will be computed using <code>(mod m)</code> arithmetic. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition:
	 * <code>(gcd(Result.determinant, m) == 1) if and only if (Result.inverse != null)</code>
	 * 
	 * @param m
	 *            the given modulus
	 * 
	 * @return The resulting InverseInfo object.
	 * 
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isSquare()</code>
	 */
	public InverseInfo detInvMod(int m) throws ArithmeticException, IllegalStateException {
		// Even though the following is a repeated check, it'll save a determinant calculation.
		if (m <= 0) {
			throw new ArithmeticException();
		}

		final int determinant = this.determinant();
		try {
			/**
			 * Save the inverse of the determinant in (mod m) as a long instead of an int to ensure that the
			 * multiplications do NOT overflow. This is guaranteed by the fact that the absolute maximum value
			 * representable by an int, is <code>2<sup>31</sup> (i.e., abs(Integer.MIN_VALUE))</code> which when
			 * squared results in <code>2<sup>62</sup></code>. However, this value is not representable by an
			 * int which means that if determinantInverse was saved as an int, the result of the multiplications
			 * may have overflowed and caused an int wrap thus changing the actual value and making the
			 * computation incorrect.
			 */
			final long determinantInverse = MathUtil.modInverse(determinant, m);

			// this.adjugate().timesEquals(determinantInverse).modEquals(m);
			final MatrixInt inverse = this.adjugate();
			int[] inverse_row = null;
			for (int rowNum = 0; rowNum != inverse.numRows; ++rowNum) {
				inverse_row = inverse.data[rowNum];
				for (int colNum = 0; colNum != inverse.numCols; ++colNum) {
					inverse_row[colNum] = (int) (MathUtil.mod(inverse_row[colNum] * determinantInverse, m));
				}
			}
			return new InverseInfo(determinant, inverse);
		} catch (ArithmeticException ex) {
			// This will only happen if gcd(determinant, m) != 1.

			// null to denote the nonexistence of this matrix's inverse.
			return new InverseInfo(determinant, null);
		}
	}

	/**
	 * @param m
	 *            the given modulus
	 * 
	 * @return The inverse of <code>this</code> using <code>(mod m)</code> arithmetic.
	 * 
	 * @throws ArithmeticException
	 *             If <code>m <= 0</code>
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isInvertibleMod(m)</code>
	 */
	public MatrixInt inverseMod(int m) throws ArithmeticException, IllegalStateException {
		final InverseInfo info = this.detInvMod(m);
		if (info.inverse == null) {
			throw new IllegalStateException();
		}
		return info.inverse;
	}

	/**
	 * Result will contain a newline character as its first and last character.
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append('\n');
		int[] row = null;
		for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
			row = this.data[rowNum];
			sb.append('|');
			for (int colNum = 0; colNum != this.numCols; ++colNum) {
				// Integer.MAX_VALUE takes up 10 chars but Integer.MIN_VALUE takes up 11 chars.
				sb.append(' ').append(String.format("%11d", row[colNum]));
			}
			sb.append(' ').append(' ').append('|').append('\n');
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ArrayUtil.hashCode(this.data);
		result = prime * result + this.numCols;
		result = prime * result + this.numRows;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof MatrixInt) ? this.equals((MatrixInt) obj) : false);
	}

	/**
	 * @param other
	 *            the given MatrixInt object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(MatrixInt other) {
		if (this == other) {
			return true;
		}

		// Check dimensions.
		if ((this.numRows != other.numRows) || (this.numCols != other.numCols)) {
			return false;
		}
		// Check individual entries.
		return this.equals(other.data);
	}

	/**
	 * @param data
	 *            the given two dimensional integer array
	 * 
	 * @return <code>ArrayUtil.equals(this.data, data)</code>.
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(int[][] data) {
		return ArrayUtil.equals(this.data, data);
	}

	/**
	 * @param supportsMutation
	 *            indicates whether the iterator can mutate <code>this</code>
	 * 
	 * @return <code>new MatrixIntIterator(this, supportsMutation)</code>.
	 */
	public MatrixIntIterator iterator(boolean supportsMutation) {
		return new MatrixIntIterator(this, supportsMutation);
	}

	/**
	 * @return <code>new MatrixIntIterator(this, true)</code>.
	 */
	@Override
	public MatrixIntIterator iterator() {
		return new MatrixIntIterator(this, true);
	}

	// --------------------------------------------------
	// Nested iterator class.
	// --------------------------------------------------

	/**
	 * Simple iterator class. <br>
	 * 
	 * It's main usage is for the return type of <code>MatrixInt.iterator(boolean)</code> and
	 * <code>MatrixInt.iterator()</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class MatrixIntIterator implements BidirectionalIterator<Integer>, Iterator<Integer> {
		/**
		 * Indicates whether <code>this</code> can mutate the iterated over MatrixInt object.
		 */
		protected final boolean supportsMutation;

		/**
		 * Number of rows.
		 */
		public final int numRows;

		/**
		 * Number of columns.
		 */
		public final int numCols;

		/**
		 * Two dimensional array containing the matrix entries.
		 */
		protected final int[][] data;

		/**
		 * Current cursor row number.
		 */
		protected int rowNum;

		/**
		 * Current cursor column number.
		 */
		protected int colNum;

		/**
		 * Construct a MatrixIntIterator object from the given MatrixInt object.
		 * 
		 * @param m
		 *            the given MatrixInt object
		 * 
		 * @param supportsMutation
		 *            indicates whether the iterator can mutate the given MatrixInt object
		 * 
		 * @throws NullPointerException
		 *             If <code>m == null</code>
		 */
		public MatrixIntIterator(MatrixInt m, boolean supportsMutation) throws NullPointerException {
			this.numRows = m.numRows;
			this.numCols = m.numCols;
			// The following is meant to be an assignment of this.supportsMutation and this.data.
			this.data = (this.supportsMutation = supportsMutation) ? m.data : m.data();
			this.begin();
		}

		/**
		 * Construct a MatrixIntIterator object from the given MatrixInt object.
		 * 
		 * @param m
		 *            the given MatrixInt object
		 * 
		 * @throws NullPointerException
		 *             If <code>m == null</code>
		 */
		public MatrixIntIterator(MatrixInt m) throws NullPointerException {
			this(m, true);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given MatrixIntIterator object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public MatrixIntIterator(MatrixIntIterator other) throws NullPointerException {
			// The following is meant to be an assignment of this.supportsMutation.
			if (this.supportsMutation = other.supportsMutation) {
				this.numRows = other.numRows;
				this.numCols = other.numCols;
				this.data = other.data;
			} else {
				this.data = new int[this.numRows = other.numRows][this.numCols = other.numCols];
				for (int rowNum = 0; rowNum != this.numRows; ++rowNum) {
					System.arraycopy(other.data[rowNum], 0, this.data[rowNum], 0, this.numCols);
				}
			}
			this.rowNum = other.rowNum;
			this.colNum = other.colNum;
		}

		@Override
		public boolean supportsMutation() {
			return this.supportsMutation;
		}

		@Override
		public MatrixIntIterator begin() {
			this.rowNum = this.colNum = 0;
			return this;
		}

		@Override
		public MatrixIntIterator end() {
			this.rowNum = this.numRows - 1;
			this.colNum = this.numCols;
			return this;
		}

		@Override
		public boolean hasNext() {
			return ((this.rowNum != this.numRows - 1) || (this.colNum != this.numCols));
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 */
		@Override
		public Integer next() throws NoSuchElementException {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}

			// Return the element and then move the cursor.
			final int result = this.data[this.rowNum][this.colNum];
			if (++this.colNum == this.numCols) {
				if (++this.rowNum == this.numRows) {
					// Reached the end of the matrix.
					--this.rowNum;
					this.colNum = this.numCols;
				} else {
					// Go to the first entry of the next row.
					this.colNum = 0;
				}
			}
			return result;
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 * 
		 * @throws NullPointerException
		 *             If <code>t == null</code>
		 */
		@Override
		public Integer next(Integer t)
				throws UnsupportedOperationException, NoSuchElementException, NullPointerException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasNext()) {
				throw new NoSuchElementException();
			} else if (t == null) {
				throw new NullPointerException();
			}

			// Set the element, return the old value and then move the cursor.
			final int[] row = this.data[this.rowNum];
			final int result = row[this.colNum];
			row[this.colNum] = t;
			if (++this.colNum == this.numCols) {
				if (++this.rowNum == this.numRows) {
					// Reached the end of the matrix.
					--this.rowNum;
					this.colNum = this.numCols;
				} else {
					// Go to the first entry of the next row.
					this.colNum = 0;
				}
			}
			return result;
		}

		@Override
		public boolean hasPrev() {
			return ((this.rowNum != 0) || (this.colNum != 0));
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 */
		@Override
		public Integer prev() throws NoSuchElementException {
			if (!this.hasPrev()) {
				throw new NoSuchElementException();
			}

			// Move the cursor and then return the element.
			if (--this.colNum == -1) {
				this.colNum = this.numCols - 1;
				--this.rowNum;
			}
			return this.data[this.rowNum][this.colNum];
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 * 
		 * @throws NullPointerException
		 *             If <code>t == null</code>
		 */
		@Override
		public Integer prev(Integer t)
				throws UnsupportedOperationException, NoSuchElementException, NullPointerException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasPrev()) {
				throw new NoSuchElementException();
			} else if (t == null) {
				throw new NullPointerException();
			}

			// Move the cursor, set the element and then return the old value.
			if (--this.colNum == -1) {
				this.colNum = this.numCols - 1;
				--this.rowNum;
			}
			final int[] row = this.data[this.rowNum];
			final int result = row[this.colNum];
			row[this.colNum] = t;
			return result;
		}
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper class for matrix inverse results. Saves matrix determinant and matrix inverse.
	 * <br>
	 * 
	 * This class does not encapsulate the information. It in fact saves direct mutable pointers passed
	 * to its ctors without creating copies therefore it is not "safe" in terms of OOP. <br>
	 * 
	 * It's main usage is for the return type of <code>MatrixInt.detInv()</code> and
	 * <code>MatrixInt.detInvMod()</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class InverseInfo {
		/**
		 * The matrix determinant.
		 */
		public final int determinant;

		/**
		 * The matrix inverse.
		 */
		public final MatrixInt inverse;

		/**
		 * @param determinant
		 *            the given matrix determinant
		 * 
		 * @param inverse
		 *            the given matrix inverse
		 */
		public InverseInfo(int determinant, MatrixInt inverse) {
			this.determinant = determinant;
			this.inverse = inverse;
		}
	}
}
