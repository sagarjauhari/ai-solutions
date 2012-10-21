package parser;

public class Parser {
	
	public String parsePredArguments(String[] args, int arity){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arity; i++){
			if(Character.isUpperCase(args[i].charAt(0))){
				sb.append("v");//variable
			}else{
				sb.append("c");//constant
			}
		}
		return sb.toString();
	}

}
