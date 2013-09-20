package ai;

import search.TreeSearch;
import search.TreeSearchState;

/**
 * 
 * @author Sagar Jauhari <sjauhar@ncsu.edu>
 *
 */
public class SearchRomania {

	/**
	 * @param args searchtype srccityname destcityname
	 */
	public static void main(String[] args) {
		String searchtype = args[0];
		String srccityname = args[1];
		String destcityname = args[2];

		TreeSearch treesearch = new TreeSearch(
				searchtype,
				new TreeSearchState(srccityname),
				new TreeSearchState(destcityname));
	}
}
