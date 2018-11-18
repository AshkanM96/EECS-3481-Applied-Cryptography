package util;

import java.util.NoSuchElementException;

/**
 * Forward iterator interface. The iterator can only move once through the sequence.
 * 
 * @author Ashkan Moatamed
 */
public interface ForwardIterator<T> {
	/**
	 * No dependencies.
	 */

	/**
	 * @return <code>true</code> if and only if <code>this</code> can mutate the iterated over object.
	 */
	public boolean supportsMutation();

	/**
	 * @return <code>true</code> if there are more elements when traversing in the forward direction.
	 */
	public boolean hasNext();

	/**
	 * Returns the next element and moves the cursor forward by one.
	 * 
	 * @return The next element.
	 * 
	 * @throws NoSuchElementException
	 *             If <code>!this.hasNext()</code>
	 */
	public T next() throws NoSuchElementException;

	/**
	 * Sets the next element to the given value and returns the old value. The cursor is also moved
	 * forward by one.
	 * 
	 * @param t
	 *            the given next element's new value
	 * 
	 * @return The next element's old value.
	 * 
	 * @throws UnsupportedOperationException
	 *             If <code>!this.supportsMutation()</code>
	 * 
	 * @throws NoSuchElementException
	 *             If <code>!this.hasNext()</code>
	 */
	public T next(T t) throws UnsupportedOperationException, NoSuchElementException;
}
