package search;

import java.util.ArrayList;

/**
 * Extends the ArrayList class and implements useful functions
 * 
 * @author Sagar Jauhari <sjauhar@ncsu.edu>
 * 
 */
public class Queue extends ArrayList<TreeSearchNode> {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public void makeQueue(TreeSearchNode... nodes) {
		for (TreeSearchNode node : nodes) {
			this.add(node);
		}
	}

	public TreeSearchNode first() {
		return this.get(0);
	}

	/**
	 * Remove the element at '0' index
	 * 
	 * @return
	 */
	public TreeSearchNode removeFirst() {
		TreeSearchNode first = this.get(0);
		this.remove(0);
		return first;
	}

	public void insert(TreeSearchNode node) {
		this.add(node);
	}

	/**
	 * 
	 * @param nodes
	 *            The array of nodes to be inserted
	 * @param index
	 *            Index of the queue where nodes are inserted. If index = -1,
	 *            nodes are appended at the end of the queue
	 */
	public void insertAll(int index, TreeSearchNode... nodes) {
		if (index == -1) { // append to the end of the queue
			makeQueue(nodes);
		} else { // iteratively insert at a particular index
			for (TreeSearchNode node : nodes) {
				this.add(index, node);
			}
		}
	}
}
