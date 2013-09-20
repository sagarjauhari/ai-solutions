package search;

/**
 * 
 * @author Sagar Jauhari <sjauhar@ncsu.edu>
 *
 */
public class TreeSearchNode {
	TreeSearchNode parentNode;
	TreeSearchAction action;
	TreeSearchState state;
	int pathCost;
	int depth;
	String label;
	
	public TreeSearchNode(TreeSearchState state, TreeSearchNode parentNode){
		this.state = state;
		this.parentNode = parentNode;
	}
	
	public TreeSearchNode(String label){
		this.label = label;
	}

}
