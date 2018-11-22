package util;

import java.util.NoSuchElementException;

/**
 * Backward iterator interface. The iterator can only move through the sequence once.
 * 
 * @author Ashkan Moatamed
 */
public interface BackwardIterator<T> {
	/**
	 * No dependencies.
	 */

	/**
	 * @return <code>true</code> if and only if <code>this</code> can mutate the iterated over object.
	 */
	public boolean supportsMutation();

	/**
	 * @return <code>true</code> if there are more elements when traversing in the backward direction.
	 */
	public boolean hasPrev();

	/**
	 * Returns the previous element and moves the cursor backward by one.
	 * 
	 * @return The previous element.
	 * 
	 * @throws NoSuchElementException
	 *             If <code>!this.hasPrev()</code>
	 */
	public T prev() throws NoSuchElementException;

	/**
	 * Sets the previous element to the given value and returns the old value. The cursor is also moved
	 * backward by one.
	 * 
	 * @param t
	 *            the given previous element's new value
	 * 
	 * @return The previous element's old value.
	 * 
	 * @throws UnsupportedOperationException
	 *             If <code>!this.supportsMutation()</code>
	 * 
	 * @throws NoSuchElementException
	 *             If <code>!this.hasPrev()</code>
	 */
	public T prev(T t) throws UnsupportedOperationException, NoSuchElementException;
}
