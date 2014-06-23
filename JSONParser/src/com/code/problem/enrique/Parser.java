package com.code.problem.enrique;

import java.io.File;
import java.util.logging.Logger;

/**
 * JSON-CSV Parser application class
 * @author Enrique Valdes
 */
public class Parser {

    
    /**
     * Logger used for exceptions
     */
    public static final Logger logger =
            Logger.getLogger(Parser.class.getName());
    

	/**
	 * Runs the JSON-CSV parsing application:
	 * Initializes variables and starts all threads.
	 * @param folder the name of the folder 
	 */
	public void parseFiles(String folder){
		
		try{

//		File dir = new File("C:/jsonfolder/" + folder);
		File dir = new File("jsonfolder/" + folder);
		
		// initialize number of threads: one for each input file
		int threads = dir.listFiles().length; 
		
		BufferQueue.nThreads=threads;
			
			// iteration of the folder to obtain each file
			for (File file : dir.listFiles()) {
				
				// start one reader thread to parse each file
				(new FileThread(file)).start();
			}
		
		// start writer thread to write the CSV output
		WriterThread escritor = new WriterThread();
		escritor.start();
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
