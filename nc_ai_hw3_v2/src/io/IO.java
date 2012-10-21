package io;

import main.Main;

/**
 *  @author Sagar Jauhari <sagarjauhari@gmail.com>
 */
public class IO {
	boolean debug;
	static int promptCount = 1;
	
	public IO(){
		this.debug = Main.debug;
	}

	public void printBinding(String s, String v, Boolean end) {
		if (end)
			System.out.println(s + " = " + v + ";\n");
		else
			System.out.println(s + " = " + v + ",");
	}
	
	public void printBinding(String s, String v) {
		System.out.println(s + " = " + v + ",");
	}

	public void printPrompt() {
		System.out.print(promptCount++ + " ?- ");
	}

	public void printY() {
		System.out.println("true.\n");
	}

	public void printN() {
		System.out.println("false.\n");
	}

	public void printTerminate(){
		System.out.println("\nProcess prolog-java finished");
	}

	public void printUndefProc(String bad, int bad_arity, String good,
			int good_arity) {
		System.out
				.println("ERROR: Undefined procedure: " + bad + "/" + bad_arity
						+ "\n"
						+ "ERROR:     However, there are definitions for:\n"
						+ "ERROR:         " + good + "/" + good_arity + "\n"
						+ "false.");
	}

	public void printUndefProc(String bad, int bad_arity) {
		System.out.println("ERROR: Undefined procedure: " + bad + "/"
				+ bad_arity + "\n" + "false.");
		System.out.println("ERROR: To halt the program use 'halt.' (without quotes)");
		System.out.println("ERROR: To enable/disable trace use 'trace.' (without quotes)");
	}
	

	/**
	 * Splits the query and returns it in the form of a string array:
	 * {predicate_symbol, arg1, arg2, arg3}
	 * @return
	 */
	public String[] readQuery(int arity){
		InputScanner i_scan = new InputScanner(System.in);
		String[] query = new String[arity+2];
		query[0] = i_scan.next(); 				//predicate symbol
		
		/* Check for Termination symbol 'halt' */
		if(query[0].equalsIgnoreCase("halt")){
			printTerminate();
			System.exit(0);
		}
		
		/* Check for Termination symbol 'trace' */
		if(query[0].equalsIgnoreCase("trace")){
			if(Main.debug){
				Main.debug=false;
				System.out.println("Trace disabled");
			}
			else{
				Main.debug=true;
				System.out.println("Trace enabled");
			}
			return null;
		}
		
		query[1] = String.valueOf(arity);		//arity
		for(int i = 0; i < arity; i++){
			query[i+2] = i_scan.next();
		}
		return query;
	}

	public void printArgsDebug(String mode, String func, String[] args, int depth){
		if(!debug) return;
		for(int i = 0; i<depth; i++){
			System.out.print(" ");
		}
		System.out.print(mode + ": ("+depth+") "+func+"(");
		for(String s:args){
			System.out.print(s + " ");
		}
		System.out.println(")");
	}

	public void printPreamble(){
		System.out.println("Welcome to JAVA-Prolog\n" +
				"By default, all possible values of variables are found (unlike swi-prolog)\n"+
				"For trace, use ?- trace. (toggles tracing)\n" +
				"For exiting, use ?- halt.\n\n" +
				"Exmaple queries: \n" +
				"?- value(Who,shouldAvoid,What).\n" +
				"?- value(X,Y,Z).\n" +
				"?- value(david,shouldAvoid,snickers).\n" +
				"More example queries in example_queries.txt file");
	}
}
