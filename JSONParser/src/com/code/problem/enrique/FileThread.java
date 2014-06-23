package com.code.problem.enrique;

import java.io.File;
import java.io.FileInputStream;

import java.util.Scanner;
import java.util.logging.Level;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;


/**
 *  Thread to parse a JSON file.
 * @author Enrique Valdes
 */
public class FileThread extends Thread {

	/**
	 * File to parse by the thread
	 */
	private File toProcess;
	   
	/**
	 *  Creates thread to parse an input file
	 * @param parameter the name of the file to parse
	 */
	public FileThread(File parameter) {

		toProcess = parameter;
	}

	
public void run() {
	   
	   System.out.println("Processing: " + toProcess.toString());
	   
	   try{
		
		   JSONParser parser = new JSONParser();
	
		   FileInputStream inputStream = new FileInputStream(toProcess);
		   
	       Scanner sc = new Scanner(inputStream, "UTF-8");
	        
	        	// loop structure to scan a file, parsing each JSON object from
	        	// each line into a String, to write it in the BufferQueue
		        while (sc.hasNextLine()) {
		        	
		        	 String line = sc.nextLine();
		                    	 
		             Object obj;

		             obj = parser.parse(line);
	             
		             JSONObject jsonObject = (JSONObject) obj;
		             
		             //gets the person
		             JSONObject persona = (JSONObject) jsonObject.get("person");
		                
		             String firstName = (String) persona.get("first_name");
		             String lastName = (String) persona.get("last_name");
		             
		             //gets the data
		             JSONObject datos = (JSONObject) jsonObject.get("data");
		             
		             String content = (String) datos.get("content");
		             String date = (String) datos.get("date");
		             
		             // String in CSV for each JSON object
		             String linea = firstName + "," + lastName + "," + content + "," + date + "\n";
		             
		             // write the CSV line in the buffer
		             BufferQueue.writeLine(linea);	
		        }
		        
		    
	        // thread finishes: decrement number of threads
	        BufferQueue.nThreads--;
	        
	        sc.close();	        
	             
        }catch(FileNotFoundException e){
        	
        	Parser.logger.log(Level.SEVERE, "Error getting input file", e);
        }catch(ParseException e){
        	
        	Parser.logger.log(Level.SEVERE, "Error parsing JSON object", e);       
        }
	   
   }
		        
}
	   
