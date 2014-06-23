package com.code.problem.enrique;

/**
 *  Includes main method of the JSON-CSV Parser
 * @author Enrique Valdes
 */
public class ParserMain {

	/**
	 *  Main method
	 * @param args name of the folder with JSON files
	 */
	public static void main(String []args){
		
		Parser solution = new Parser();
		
		solution.parseFiles(args[0]);
		
	}
	
}
