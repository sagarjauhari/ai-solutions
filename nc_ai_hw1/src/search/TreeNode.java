package search;

import java.util.ArrayList;

/**
 * Generic node for an n-ary tree
 * @author Sagar Jauhari <sjauhar@ncsu.edu>
 *
 */
public class TreeNode {
	Object value;
	ArrayList<TreeNode> children;
	
	public TreeNode(){
		children = new ArrayList<TreeNode>();
		value = null;
	}
}
