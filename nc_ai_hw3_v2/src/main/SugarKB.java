package main;

import io.IO;
import kb.Sugar3;

public class SugarKB {
	io.IO io;
	String[] q; //query split into 4 parts
	int arity = 3;
	Sugar3 sugar3;
	
	public SugarKB(){
		io = new IO();
	}
	
	public void query(){
		io.printPrompt();
		q = io.readQuery(arity);
		if(q==null) return; //in case of "trace."
		processQuery();
	}
	
	public void processQuery(){
		boolean result = false;
		sugar3 = new Sugar3();
		
		String[] args = new String[3];
		args[0] = q[2];
		args[1] = q[3];
		args[2] = q[4];
		
		if(q[0].equals("edge")){
			result = sugar3.callEdge(args,0, null);
			
		}else if(q[0].equals("value")){
			result = sugar3.value(args, 0, null);
		}else{
			io.printUndefProc(q[0], Integer.parseInt(q[1]));
		}
		
		if(result){
			io.printY();
		}else{
			io.printN();
		}
	}
}
