package com.code.problem.enrique;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;

/**
 *   Thread to output each JSON object in a CSV file.
 *  @author Enrique Valdes
 */
public class WriterThread extends Thread {

	/**
	 * Each line to output in the CSV file
	 */
	private String line; 
	
	
	/**
	 * Counter of lines output to the CSV file
	 */
	private int i;
	
	public void run(){
		
		try {
			
			i=0;
			PrintWriter out;
			
			// create output file and set CSV header
			out = new PrintWriter("output.csv");
			String header = "First Name,Last Name,Content,Date"+"\n";
			out.print(header);
			
				// iteration of the buffer of parsed lines in CSV
				while(BufferQueue.nThreads>0 || (!BufferQueue.dataBuffer.isEmpty())){
	
					// read a CSV line from the buffer
					line = BufferQueue.readLine();
					
						if (line==null){
							break;
						}
										
					// write CSV line in the output file
					out.print(line);
					i++;	
				}
			
			System.out.println("Parsing completed");
			System.out.println("Number of JSON objects parsed: " + i);
			out.close();
			
			
		} catch (FileNotFoundException e) {

        	Parser.logger.log(Level.SEVERE, "Error creating output file", e);
		} catch (InterruptedException e) {

        	Parser.logger.log(Level.SEVERE, "Writer thread is blocked in sleep", e);
			Thread.currentThread().interrupt();
		}
	}
	
}
