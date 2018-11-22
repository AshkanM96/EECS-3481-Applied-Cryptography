package util;

/**
 * Bidirectional iterator interface. A simpler version of Java's ListIterator. <br>
 * The iterator can move through the sequence infinitely many times.
 * 
 * @author Ashkan Moatamed
 */
public interface BidirectionalIterator<T> extends ForwardIterator<T>, BackwardIterator<T> {
	/**
	 * Dependencies: <code>
	 * 		1. util.ForwardIterator
	 * 		2. util.BackwardIterator
	 * </code>
	 */

	/**
	 * Move the cursor to the first position so that if the iterated over object is not empty,
	 * <code>this.next()</code> returns the first element.
	 * 
	 * @return <code>this</code>.
	 */
	public BidirectionalIterator<T> begin();

	/**
	 * Move the cursor to the last position so that if the iterated over object is not empty,
	 * <code>this.prev()</code> returns the last element.
	 * 
	 * @return <code>this</code>.
	 */
	public BidirectionalIterator<T> end();
}
