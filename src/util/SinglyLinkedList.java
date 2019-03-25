package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Singly linked list with header and no tail pointer.
 * 
 * @author Ashkan Moatamed
 *
 * @param <T>
 *            the node data type
 */
public class SinglyLinkedList<T> implements Iterable<T> {
	/**
	 * Dependencies: <code>
	 * 		1. util.ForwardIterator
	 * </code>
	 */

	/**
	 * List size.
	 */
	protected long size;

	/**
	 * List header sentinel node.
	 */
	protected SLLNode<T> header;

	/**
	 * Default ctor.
	 */
	public SinglyLinkedList() {
		this.size = 0;
		this.header = new SLLNode<T>();
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given SinglyLinkedList object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public SinglyLinkedList(SinglyLinkedList<T> other) throws NullPointerException {
		this();
		this.addFirst(other);
	}

	/**
	 * @return <code>this.size</code>.
	 */
	public long size() {
		return this.size;
	}

	/**
	 * @return <code>(int) this.size</code>.
	 * 
	 * @throws ArithmeticException
	 *             If <code>Integer.MAX_VALUE < this.size</code>
	 */
	public int sizeExact() throws ArithmeticException {
		if (Integer.MAX_VALUE < this.size) {
			throw new ArithmeticException();
		}
		return ((int) this.size);
	}

	/**
	 * Copy the data encapsulated by the nodes in the list with indices from
	 * <code>[beginIndex, endIndex)</code> into the given array. Set the first element in the given
	 * array after the end of the range to <code>null</code> if such an element exists.
	 * 
	 * @param a
	 *            the given array
	 * 
	 * @param beginIndex
	 *            the given begin index
	 * 
	 * @param endIndex
	 *            the given end index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(beginIndex < 0) || (this.size <= beginIndex)
	 *             || (endIndex < 0) || (this.size < endIndex)</code>
	 */
	public void toArray(T[] a, long beginIndex, long endIndex) throws NullPointerException, IndexOutOfBoundsException {
		if (a.length == 0) {
			return;
		} else if ((beginIndex < 0) || (this.size <= beginIndex)) {
			throw new IndexOutOfBoundsException();
		} else if ((endIndex < 0) || (this.size < endIndex)) {
			throw new IndexOutOfBoundsException();
		} else if (endIndex <= beginIndex) {
			a[0] = null;
			return;
		}
		// (a.length != 0) && (0 <= beginIndex) && (beginIndex < endIndex) && (endIndex <= this.size)

		// Advance the node pointer to the node with index beginIndex.
		SLLNode<T> node = this.header.next;
		for (long i = 0L; i != beginIndex; ++i) {
			node = node.next;
		}

		// Fill the given array with node data from [beginIndex, endIndex).
		final int maxI = (int) Math.min(a.length, endIndex - beginIndex);
		int i = 0;
		for (; (node != null) && (i != maxI); node = node.next, ++i) {
			a[i] = node.data;
		}
		// Leave a null marker at the end of the range in the given array if possible.
		if (i != a.length) {
			a[i] = null;
		}
	}

	/**
	 * <code>this.toArray(a, beginIndex, this.size)</code>.
	 * 
	 * @param a
	 *            the given array
	 * 
	 * @param beginIndex
	 *            the given begin index
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(beginIndex < 0) || (this.size <= beginIndex)</code>
	 */
	public void toArray(T[] a, long beginIndex) throws NullPointerException, IndexOutOfBoundsException {
		this.toArray(a, beginIndex, this.size);
	}

	/**
	 * <code>this.toArray(a, 0)</code>.
	 * 
	 * @param a
	 *            the given array
	 * 
	 * @throws NullPointerException
	 *             If <code>a == null</code>
	 */
	public void toArray(T[] a) throws NullPointerException {
		this.toArray(a, 0);
	}

	/**
	 * @return The data encapsulated by the first node in the list.
	 * 
	 * @throws NoSuchElementException
	 *             If <code>this.size == 0</code>
	 */
	public T getFirst() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}
		return this.header.next.data;
	}

	/**
	 * Add a new node with the given data as the first node in the list.
	 * 
	 * @param data
	 *            the given data
	 */
	public void addFirst(T data) {
		final SLLNode<T> second = this.header.next;
		final SLLNode<T> first = new SLLNode<T>(data, second);
		this.header.next = first;
		++this.size;
	}

	/**
	 * Add new nodes with the data encapsulated by the given list as the first nodes in the list.
	 * 
	 * @param other
	 *            the given SinglyLinkedList object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public void addFirst(SinglyLinkedList<T> other) throws NullPointerException {
		for (SLLNode<T> curr = this.header, other_curr = other.header.next; other_curr != null; other_curr = other_curr.next, curr = curr.next) {
			curr.next = new SLLNode<T>(other_curr.data);
		}
		this.size += other.size;
	}

	/**
	 * Removes the first node in the list.
	 * 
	 * @return The data encapsulated by the first node in the list.
	 * 
	 * @throws NoSuchElementException
	 *             If <code>this.size == 0</code>
	 */
	public T removeFirst() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		// Save the needed pointers.
		SLLNode<T> first = this.header.next;
		final SLLNode<T> second = first.next;
		final T result = first.data;

		// Set the first node pointers to null for garbage collection.
		first.data = null;
		first.next = null;
		first = null;

		// Set the list attributes to reflect first node removal.
		this.header.next = second;
		--this.size;

		// Return the old first node's data.
		return result;
	}

	/**
	 * Removes all of the nodes in the list.
	 * 
	 * @param nullifyAllPtrs
	 *            specifies whether all pointers should be set to <code>null</code>
	 */
	public void clear(boolean nullifyAllPtrs) {
		if (nullifyAllPtrs) {
			for (SLLNode<T> curr = this.header.next, next = null; curr != null; curr = next) {
				next = curr.next;
				curr.data = null;
				curr.next = null;
			}
		}
		this.size = 0;
		this.header.next = null;
	}

	/**
	 * <code>this.clear(true)</code>.
	 */
	public void clear() {
		this.clear(true);
	}

	@Override
	public String toString() {
		if (this.size == 0) {
			return "[]";
		}

		SLLNode<T> curr = this.header.next;
		final StringBuilder sb = new StringBuilder();
		sb.append('[').append(curr.data);
		for (curr = curr.next; curr != null; curr = curr.next) {
			sb.append(", ").append(curr.data);
		}
		return sb.append(']').toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (SLLNode<T> curr = this.header.next; curr != null; curr = curr.next) {
			result = prime * result + ((curr.data == null) ? 0 : curr.data.hashCode());
		}
		result = prime * result + Long.hashCode(this.size);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof SinglyLinkedList<?>) ? this.equals((SinglyLinkedList<?>) obj) : false);
	}

	/**
	 * @param other
	 *            the given SinglyLinkedList object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(SinglyLinkedList<?> other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		}

		// Check size.
		if (this.size != other.size) {
			return false;
		}
		// Check individual entries.
		SLLNode<?> other_curr = other.header.next;
		for (SLLNode<T> curr = this.header.next; curr != null; curr = curr.next, other_curr = other_curr.next) {
			if (curr.data == null) {
				if (other_curr.data != null) {
					return false;
				}
			} else if (!curr.data.equals(other_curr.data)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param supportsMutation
	 *            indicates whether the iterator can mutate <code>this</code>
	 * 
	 * @return <code>new SLLIterator&lt;T&gt;(this, supportsMutation)</code>.
	 */
	public SLLIterator<T> iterator(boolean supportsMutation) {
		return new SLLIterator<T>(this, supportsMutation);
	}

	/**
	 * @return <code>new SLLIterator&lt;T&gt;(this, true)</code>.
	 */
	@Override
	public SLLIterator<T> iterator() {
		return new SLLIterator<T>(this, true);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper class for singly linked list nodes.
	 * 
	 * @author Ashkan Moatamed
	 *
	 * @param <T>
	 *            the node data type
	 */
	public static class SLLNode<T> {
		/**
		 * Node data.
		 */
		public T data;

		/**
		 * Node next pointer.
		 */
		public SLLNode<T> next;

		/**
		 * Construct an SLLNode object with the given data and the given next pointer.
		 * 
		 * @param data
		 *            the given data
		 * 
		 * @param next
		 *            the given next pointer
		 */
		public SLLNode(T data, SLLNode<T> next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Data ctor. <br>
		 * Equivalent to <code>new SLLNode&lt;T&gt;(data, null)</code>.
		 * 
		 * @param data
		 *            the given data
		 */
		public SLLNode(T data) {
			this(data, null);
		}

		/**
		 * Default ctor. <br>
		 * Equivalent to <code>new SLLNode&lt;T&gt;(null)</code>.
		 */
		public SLLNode() {
			this(null, null);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given SLLNode object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public SLLNode(SLLNode<T> other) throws NullPointerException {
			this(other.data, other.next);
		}
	}

	// --------------------------------------------------
	// Nested iterator class.
	// --------------------------------------------------

	/**
	 * Simple iterator class. <br>
	 * 
	 * It's main usage is for the return type of <code>SinglyLinkedList.iterator(boolean)</code> and
	 * <code>SinglyLinkedList.iterator()</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class SLLIterator<T> implements ForwardIterator<T>, Iterator<T> {
		/**
		 * Indicates whether <code>this</code> can mutate the iterated over SinglyLinkedList object.
		 */
		protected final boolean supportsMutation;

		/**
		 * List pointer used to update the size of the original list when inserting or removing.
		 */
		protected final SinglyLinkedList<T> sll;

		/**
		 * List header sentinel node.
		 */
		protected final SLLNode<T> header;

		/**
		 * Previous cursor node.
		 */
		protected SLLNode<T> prev;

		/**
		 * Current cursor node.
		 */
		protected SLLNode<T> curr;

		/**
		 * Construct an SLLIterator object from the given SinglyLinkedList object.
		 * 
		 * @param sll
		 *            the given SinglyLinkedList object
		 * 
		 * @param supportsMutation
		 *            indicates whether the iterator can mutate the given SinglyLinkedList object
		 * 
		 * @throws NullPointerException
		 *             If <code>sll == null</code>
		 */
		public SLLIterator(SinglyLinkedList<T> sll, boolean supportsMutation) throws NullPointerException {
			// The following is meant to be an assignment of this.supportsMutation.
			if (this.supportsMutation = supportsMutation) {
				this.header = (this.sll = sll).header;
			} else {
				this.header = (this.sll = new SinglyLinkedList<T>(sll)).header;
			}
			this.begin();
		}

		/**
		 * Construct an SLLIterator object from the given SinglyLinkedList object.
		 * 
		 * @param sll
		 *            the given SinglyLinkedList object
		 * 
		 * @throws NullPointerException
		 *             If <code>sll == null</code>
		 */
		public SLLIterator(SinglyLinkedList<T> sll) throws NullPointerException {
			this(sll, true);
		}

		/**
		 * Cannot provide a copy ctor without iterating in the other SinglyLinkedList saved in other.sll to
		 * find the index of other.curr and then iterating in this SLLIterator to match the index.
		 * Therefore, due to overhead of at least one iteration (in this SLLIterator) and either iteration
		 * through other.sll or the addition of an extra index variable to the SLLIterator object
		 * definition, the copy ctor is not included.
		 */

		@Override
		public boolean supportsMutation() {
			return this.supportsMutation;
		}

		/**
		 * Move the cursor to the first position so that if the list is not empty, <code>this.next()</code>
		 * returns the first element.
		 * 
		 * @return <code>this</code>.
		 */
		public SLLIterator<T> begin() {
			this.prev = null;
			this.curr = this.header;
			return this;
		}

		/**
		 * @return <code>true</code> if and only if the iterator is currently on a node.
		 */
		public boolean hasCurr() {
			// i.e., this.curr != this.header
			return (this.prev != null);
		}

		/**
		 * Returns the current node's value
		 * 
		 * @return The current node's value.
		 * 
		 * @throws NoSuchElementException
		 *             If <code>!this.hasCurr()</code>
		 */
		public T curr() throws NoSuchElementException {
			if (!this.hasCurr()) {
				throw new NoSuchElementException();
			}
			return this.curr.data;
		}

		/**
		 * Sets the current node's value to the given value and returns the old value.
		 * 
		 * @param t
		 *            the given current node's new value
		 * 
		 * @return The current node's old value.
		 * 
		 * @throws UnsupportedOperationException
		 *             If <code>!this.supportsMutation()</code>
		 * 
		 * @throws NoSuchElementException
		 *             If <code>!this.hasCurr()</code>
		 */
		public T curr(T t) throws UnsupportedOperationException, NoSuchElementException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasCurr()) {
				throw new NoSuchElementException();
			}

			final T result = this.curr.data;
			this.curr.data = t;
			return result;
		}

		@Override
		public boolean hasNext() {
			return (this.curr.next != null);
		}

		@Override
		public T next() throws NoSuchElementException {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}

			// Move the cursor and then return the element.
			this.prev = this.curr;
			this.curr = this.curr.next;
			return this.curr.data;
		}

		@Override
		public T next(T t) throws UnsupportedOperationException, NoSuchElementException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasNext()) {
				throw new NoSuchElementException();
			}

			// Move the cursor, set the element and then return the old value.
			this.prev = this.curr;
			this.curr = this.curr.next;
			final T result = this.curr.data;
			this.curr.data = t;
			return result;
		}

		/**
		 * Insert a new node with the given data after the node the iterator is currently on.
		 * 
		 * @param t
		 *            the given data
		 * 
		 * @throws UnsupportedOperationException
		 *             If <code>!this.supportsMutation()</code>
		 */
		public void insert(T t) throws UnsupportedOperationException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			}

			final SLLNode<T> second = this.curr.next;
			final SLLNode<T> first = new SLLNode<T>(t, second);
			this.curr.next = first;
			++this.sll.size;
		}

		/**
		 * Remove the node the iterator is currently on.
		 * 
		 * @return The current node's value.
		 * 
		 * @throws UnsupportedOperationException
		 *             If <code>!this.supportsMutation()</code>
		 * 
		 * @throws NoSuchElementException
		 *             If <code>!this.hasCurr()</code>
		 */
		public T prune() throws UnsupportedOperationException, NoSuchElementException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasCurr()) {
				throw new NoSuchElementException();
			}

			// Save the needed pointers.
			final T result = this.curr.data;
			this.prev.next = this.curr.next;

			// Set the curr node pointers to null for garbage collection.
			this.curr.data = null;
			this.curr.next = null;

			// Set the list attributes to reflect curr node removal.
			this.curr = this.prev.next;
			if (--this.sll.size == 0) {
				this.begin();
			}

			// Return the old curr node's data.
			return result;
		}

		/**
		 * Same as <code>this.prune()</code> but without return.
		 */
		@Override
		public void remove() throws UnsupportedOperationException, NoSuchElementException {
			this.prune();
		}
	}
}
