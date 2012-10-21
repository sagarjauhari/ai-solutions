package main;

import io.IO;

/**
 * 
 * @author sagarjauhari@gmail.com
 *
 */
public class Main {
	public static boolean debug = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		io.IO io = new IO();
		io.printPreamble();
		SugarKB kb = new SugarKB();
		while(true){
			kb.query();
		}
	}
}
