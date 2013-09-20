package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import data.RomaniaMap;

/**
 * 
 * @author Sagar Jauhari, sjauhar@ncsu.edu
 * 
 */
public class TreeSearch {
	RomaniaMap romaniamap;
	HashMap<String, HashSet<String>> hashmap;
	TreeSearchNode root;
	Queue queue;
	HashSet<String> expanded; // keeps track of node labels already
								// expanded

	HashMap<String, HashSet<String>> createRomanianMapUnweighted() {

		HashMap<String, HashSet<String>> roads = new HashMap<String, HashSet<String>>();
		romaniamap = new RomaniaMap();
		for (int i = 0; i < romaniamap.roads.length; i++) {
			String key = romaniamap.roads[i][0];
			String value = romaniamap.roads[i][1];
			if (roads.containsKey(key)) {
				roads.get(key).add(value);
			} else {
				HashSet<String> set = new HashSet<String>();
				set.add(value);
				roads.put(key, set);
			}
		}
		return roads;
	}

	void initializeSearchTree(TreeSearchState source) {
		hashmap = createRomanianMapUnweighted();

		expanded = new HashSet<String>();

		root = new TreeSearchNode(new TreeSearchState(null), null);
		root.parentNode = null;
		root.depth = 0;
		root.pathCost = 0;

		for (String key : hashmap.keySet()) {
			if (key.equals(source.label)
					|| hashmap.get(key).contains(source.label)) {
				root.state.label = source.label;
				break;
			}
		}

		assert root.state.label != null;

		queue = new Queue();
		queue.insert(root);
	}

	public TreeSearch(String searchtype, TreeSearchState source,
			TreeSearchState goal) {
		initializeSearchTree(source);
		TreeSearchNode ans = doTreeSearch(searchtype, goal);
		if (ans == null) {
			System.out.println("Cannot reach destination");
		} else { // Display path
			ArrayList<String> solutionPath = new ArrayList<String>();
			while (ans != null) {
				solutionPath.add(ans.state.label);
				ans = ans.parentNode;
			}
			for (int i = solutionPath.size() - 1; i >= 0; i--) {
				System.out.print(solutionPath.get(i));
				if (i != 0) {
					System.out.print(" -> ");
				}
			}
		}
	}

	/**
	 * @return True if goal found, False otherwise
	 */
	public TreeSearchNode doTreeSearch(String searchtype, TreeSearchState goal) {
		while (true) {

//			for (TreeSearchNode ts : queue) {
//				System.out.print(ts.state.label + ",");
//			}
//			System.out.println();

			if (queue.isEmpty()) {
				return null; // Not found
			}
			TreeSearchNode node = queue.removeFirst();
			if (goalTest(node, goal)) {
				return node; // Found!
			}
			/* Add entry in 'expanded' hashset */
			expanded.add(node.state.label);

			expandAndInsertWithStrategy(queue, searchtype, node);
		}
	}

	/**
	 * Selective insert on a queue depending on search strategy
	 * 
	 * @param queue
	 * @param searchtype
	 * @param nodes
	 */
	public void expandAndInsertWithStrategy(Queue queue, String searchtype,
			TreeSearchNode node) {
		if (searchtype.equals("BFS")) {
			queue.insertAll(-1, expand(node));
		} else if (searchtype.equals("DFS")) {
			queue.insertAll(0, expand(node));
		}
	}

	public boolean goalTest(TreeSearchNode node, TreeSearchState goal) {
		if (node.state.label.equals(goal.label))
			return true;
		else
			return false;
	}

	/**
	 * Expands the node and returns an array of the children
	 * 
	 * @param node
	 *            Node to be expanded
	 * @return
	 */
	public TreeSearchNode[] expand(TreeSearchNode node) {
		ArrayList<TreeSearchNode> list = new ArrayList<TreeSearchNode>();

		for (String key : hashmap.keySet()) {
			if (key.equals(node.state.label)) {
				for (String value : hashmap.get(key)) {
					if (node.parentNode == null
							|| (!node.parentNode.state.label.equals(value))
							&& !expanded.contains(value)) {
						list.add(new TreeSearchNode(new TreeSearchState(value),
								node));
					}
				}
			} else if (hashmap.get(key).contains(node.state.label)) {
				if (node.parentNode == null
						|| (!node.parentNode.state.label.equals(key) && !expanded
								.contains(key))) {
					list.add(new TreeSearchNode(new TreeSearchState(key), node));
				}
			}
		}
		return list.toArray(new TreeSearchNode[list.size()]);
	}
}
