package kb;

import java.util.ArrayList;

import io.IO;
import parser.Parser;

/**
 * 
 * @author Sagar Jauhari <sagarjauhari@gmail.com>
 *
 */
public class Sugar3 {
	Parser parser = new Parser();
	IO io = new IO();
	String[] constList = { "david", "diabetics", "sugar", "candy", "snickers",
			"isa", "shouldAvoid", "contains", "ako"};
	String[][] bindings;
	int bindingsSize = 0;
	
	public Sugar3(){
		bindings = new String[10][3];
	}
	
	public boolean callEdge(String[] args, int depth, ArrayList<String[]> binding) {
		io.printArgsDebug("Call","callEdge",args, depth);
		String[] old_args = args.clone();

		if (depth == 0) {
			ArrayList<String[]> new_bind = new ArrayList<String[]>();
			while (edge(args, new_bind)) {
				io.printArgsDebug("Exit","callEdge",args, depth);
				addToBindings(args, new_bind);
				printBindings(old_args, args);
				if(parser.parsePredArguments(old_args, 3).equals("ccc")){
					return true;
				}
				
				//reset args
				args = old_args.clone();
				io.printArgsDebug("Redo","callEdge",args, depth);
			}
		}else{ //when called from value or call-value
			if(edge(args, binding)){
				io.printArgsDebug("Exit","callEdge",args, depth);
				addToBindings(args, binding); //will give new value when called again during redo
				return true;
			}
		}
		
		io.printArgsDebug("Fail","callEdge",old_args, depth);
		return false;
	}

	private void printBindings(String[] old_args, String[] new_args) {
		if (parser.parsePredArguments(old_args, 3).equals("ccc")) {
			//do nothing
		}
		else if (parser.parsePredArguments(old_args, 3).equals("ccv")) {
			io.printBinding(old_args[2], new_args[2], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("cvc")) {
			io.printBinding(old_args[1], new_args[1], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("vcc")) {
			io.printBinding(old_args[0], new_args[0], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("vvc")) {
			io.printBinding(old_args[0], new_args[0] );
			io.printBinding(old_args[1], new_args[1], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("vcv")) {
			io.printBinding(old_args[0], new_args[0] );
			io.printBinding(old_args[2], new_args[2], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("cvv")) {
			io.printBinding(old_args[1], new_args[1] );
			io.printBinding(old_args[2], new_args[2], true);
		} 
		else if (parser.parsePredArguments(old_args, 3).equals("vvv")) {
			io.printBinding(old_args[0], new_args[0]);
			io.printBinding(old_args[1], new_args[1]);
			io.printBinding(old_args[2], new_args[2], true);
		}

	}

	public boolean edge(String[] args, ArrayList<String[]> binding){
		boolean result = false;
		
		if(parser.parsePredArguments(args, 3).equals("ccc")){ //all constants
			
			if(
			(args[0].equals(edges[0][0]) && args[1].equals(edges[0][1]) && args[2].equals(edges[0][2]))
			||
			(args[0].equals(edges[1][0]) && args[1].equals(edges[1][1]) && args[2].equals(edges[1][2]))
			||
			(args[0].equals(edges[2][0]) && args[1].equals(edges[2][1]) && args[2].equals(edges[2][2]))
			||
			(args[0].equals(edges[3][0]) && args[1].equals(edges[3][1]) && args[2].equals(edges[3][2]))
			){
				if(inBindings(args, binding)){
					return false;
				}else{
//					addToBindings(args, binding);
					return true;
				}
			}
		}
		else if(parser.parsePredArguments(args, 3).equals("ccv")){
			for(String cons:constList){
				args[2] = cons;
				if(edge(args, binding)){// && !inBindings(args, binding)){
					return true;
				}
			}
		}else if(parser.parsePredArguments(args, 3).equals("cvc")){
			for(String cons:constList){
				args[1] = cons;
				if(edge(args, binding)){// && !inBindings(args, binding)){
					return true;
				}
			}
		}else if(parser.parsePredArguments(args, 3).equals("vcc")){
			for(String cons:constList){
				args[0] = cons;
				if(edge(args, binding)){// && !inBindings(args, binding)){
					return true;
				}
			}
		} else if (parser.parsePredArguments(args, 3).equals("vvc")) {
			for (String cons0 : constList) {
				args[0] = cons0;
				for (String cons1 : constList) {
					args[1] = cons1;
					if (edge(args, binding)){// && !inBindings(args, binding)) {
						return true;
					}
				}
			}
		} else if (parser.parsePredArguments(args, 3).equals("vcv")) {
			for (String cons0 : constList) {
				args[0] = cons0;
				for (String cons2 : constList) {
					args[2] = cons2;
					if (edge(args, binding)){// && !inBindings(args, binding)) {
						return true;
					}
				}
			}
		} else if (parser.parsePredArguments(args, 3).equals("cvv")) {
			for (String cons1 : constList) {
				args[1] = cons1;
				for (String cons2 : constList) {
					args[2] = cons2;
					if (edge(args, binding)){// && !inBindings(args, binding)) {
						return true;
					}
				}
			}
		} else if (parser.parsePredArguments(args, 3).equals("vvv")) {
			
			for (String cons0 : constList) {
				args[0] = cons0;
				for (String cons1 : constList) {
					args[1] = cons1;
					for (String cons2 : constList) {
						args[2] = cons2;
						if (edge(args, binding)) {
							return true;
						}
					}
				}
			}
		}
		return result;
	}
	
	public boolean value(String[] args, int depth, ArrayList<String[]> binding){
		io.printArgsDebug("Call","value",args, depth);
		String[] old_args = args.clone();
		
		/* 1
		 * value(Node, Slot, Value):- edge(Node, Slot, Value).
		 */
		if(callEdge(args, depth, binding)){
			if(depth>0 || parser.parsePredArguments(old_args, 3).equals("ccc")){
				io.printArgsDebug("Exit","value",args, depth);
				return true;
			}
		}
		io.printArgsDebug("Redo","value",old_args, depth);
//		else if(parser.parsePredArguments(old_args, 3).equals("ccc")){
//			return false;
//		}
		
		
		/* 2
		 * value(Node, Slot, Value):-
    	 *		edge(Node, isa, Node1),
    	 *		value(Node1, Slot, Value).
		 */
		String[] args2 = old_args.clone();
		String[] args3 = old_args.clone();
		args2[1] = "isa"; args2[2] = "Node1";
		
//		if(depth==0){
		ArrayList<String[]> bind1 = new ArrayList<String[]>();
		ArrayList<String[]> bind2 = new ArrayList<String[]>();
		while(callEdge(args2, depth+1, bind1)){
			args3[0] = args2[2];
			while(value(args3, depth+1, bind2)){
				io.printArgsDebug("Exit","value",args3, depth);
				if(depth>0 || parser.parsePredArguments(old_args, 3).equals("ccc")){
					if(parser.parsePredArguments(old_args, 3).equals("ccc") && binding==null){
						binding = new ArrayList<String[]>();
					}
					args[0] = args2[0];
					args[1] = args3[1];
					args[2] = args3[2];
					if(!inBindings(args, binding)){
						addToBindings(args, binding);
						
						return true;
					}
					//reset args3
					args3 = old_args.clone();
					args3[0] = args2[2];
				}else{ //depth==0
					String[] args4 = old_args.clone();
					args4[0] = args2[0];
					args4[1] = args3[1];
					args4[2] = args3[2];
					if(binding==null){
						binding = new ArrayList<String[]>();
					}
					
					if(!inBindings(args4, binding)){
						addToBindings(args4, binding);
						printBindings(old_args, args4);
					}
					
					args3 = old_args.clone();
					args3[0] = args2[2];
				}
				io.printArgsDebug("Redo","value",args3, depth);
			}
			io.printArgsDebug("Redo","value",args2, depth);
		}
		io.printArgsDebug("Redo","value",old_args, depth);
//		}
		
		/* 3
		 * value(Node, Slot, Value):-
    	 *		edge(Node, ako, Node1),
    	 *		value(Node1, Slot, Value).
		 */
		args2 = old_args.clone();
		args3 = old_args.clone();
		args2[1] = "ako"; args2[2] = "Node1";
		
//		if(depth==0){
		ArrayList<String[]> bind3 = new ArrayList<String[]>();
		ArrayList<String[]> bind4 = new ArrayList<String[]>();
		while(callEdge(args2, depth+1, bind3)){
			args3[0] = args2[2];
			while(value(args3, depth+1, bind4)){
				io.printArgsDebug("Exit","value",args3, depth);
				if(depth>0 || parser.parsePredArguments(old_args, 3).equals("ccc")){
					if(parser.parsePredArguments(old_args, 3).equals("ccc") && binding==null){
						binding = new ArrayList<String[]>();
					}
					args[0] = args2[0];
					args[1] = args3[1];
					args[2] = args3[2];
					
					if(binding==null){
						binding = new ArrayList<String[]>();
					}
					
					if(!inBindings(args, binding)){
						addToBindings(args, binding);
						return true;
					}
					
					//reset args3 for next iteration
					args3 = old_args.clone();
					args3[0] = args2[2];
					
				}else{ //depth==0
					String[] args4 = old_args.clone();
					args4[0] = args2[0];
					args4[1] = args3[1];
					args4[2] = args3[2];
					
					if(binding==null){
						binding = new ArrayList<String[]>();
					}
					if(!inBindings(args4, binding)){
						addToBindings(args4, binding);
						printBindings(old_args, args4);
					}
					
					//reset args3 for next iteration
					args3 = old_args.clone();
					args3[0] = args2[2];
				}
				io.printArgsDebug("Redo","value",args3, depth);
			}
			io.printArgsDebug("Redo","value",args2, depth);
		}
		io.printArgsDebug("Redo","value",old_args, depth);

//		}
		
		/* 4
		 * value(Node, shouldAvoid, Node1):-
    	 *		value(Node1, contains, Stuff),
    	 *		value(Node, shouldAvoid, Stuff).
		 */
		if (old_args[1].equals("shouldAvoid") ||
				parser.parsePredArguments(old_args, 3).equals("vvv") ||
				parser.parsePredArguments(old_args, 3).equals("cvv") ||
				parser.parsePredArguments(old_args, 3).equals("vvc") ||
				parser.parsePredArguments(old_args, 3).equals("cvc")
				) {
			args2 = old_args.clone();
			args3 = old_args.clone();

			args2[0] = old_args[2];
			args2[1] = "contains";
			args2[2] = "Stuff";
			args3[1] = "shouldAvoid";

			ArrayList<String[]> bind5 = new ArrayList<String[]>();
			ArrayList<String[]> bind6 = new ArrayList<String[]>();
			if (depth == 0) {
				while (value(args2, depth + 1, bind5)) {
					//io.printArgsDebug("Exit","value",args2, depth);
					args3[2] = args2[2];
					while (value(args3, depth + 1, bind6)) {
//						io.printArgsDebug("Exit","value",args3, depth+1);
						if (parser.parsePredArguments(old_args, 3).equals("ccc")) {
							return true;
						}
						else {
							String[] args4 = old_args.clone();
							args4[0] = args3[0];
							args4[1] = args3[1];
							args4[2] = args2[0];
							if(binding==null){
								binding = new ArrayList<String[]>();
							}
							if(!inBindings(args4, binding)){
								io.printArgsDebug("Exit","value",args4, depth);
								addToBindings(args4, binding);
								printBindings(old_args, args4);
							}
							
							//reset args3 for next iteration
							args3 = old_args.clone();
							args3[1] = "shouldAvoid";
							args3[2] = args2[2];
						}
						io.printArgsDebug("Redo","value",args3, depth);
					}
					//reset args2 for next iteration
					args2[0] = old_args[2];
					args2[1] = "contains";
					args2[2] = "Stuff";
					io.printArgsDebug("Redo","value",args2, depth);
					//reset bind6 for next iteration
					bind6.clear();
				}
			} else { // if(depth > 0)
				while (value(args2, depth + 1, bind5)) {
					args3[2] = args2[2];
					while (value(args3, depth + 1, bind6)) {
						io.printArgsDebug("Exit","value",args3, depth);
						args[0] = args3[0];
						args[1] = args3[1];
						args[2] = args2[0];
						if (!inBindings(args, binding)) {
							addToBindings(args, binding);
							return true;
						}
						// reset args3 for next iteration
						args3[1] = "shouldAvoid";
						args3[2] = args2[2];
						
						io.printArgsDebug("Redo","value",args3, depth);
					}
					io.printArgsDebug("Redo","value",args2, depth);
				}
			}
		}
		
		
		io.printArgsDebug("Fail","value",old_args, depth);
		return false;
	}
	
	public boolean callValue(String[] args, int depth){
		boolean retval = false;
		
		return retval;
	}
	
	public boolean inBindings(String[] args, ArrayList<String[]> binding){
		for(String[] entry:binding){
			if(args[0].equals(entry[0]) && args[1].equals(entry[1]) && args[2].equals(entry[2])){
				return true;
			}
		}
		return false;
	}
	
	public boolean addToBindings(String[] args, ArrayList<String[]> binding){
		String[] entry = new String[3];
		entry[0] = args[0];
		entry[1] = args[1];
		entry[2] = args[2];
		binding.add(entry);
		return true;
	}
	
	public boolean clearBindings(){
		for(String[] array: bindings){
			for(int i = 0; i < array.length; i++){
				array[i] = null;
			}
		}
		return true;
	}
	
	String[][] edges = {
				{"david", "isa", "diabetics"},
				{"diabetics", "shouldAvoid", "sugar"},
				{"candy", "contains", "sugar"},
				{"snickers", "ako", "candy"}
			};
}
