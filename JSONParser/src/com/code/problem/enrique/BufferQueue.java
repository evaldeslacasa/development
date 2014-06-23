package com.code.problem.enrique;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  Synchronized buffer
 * @author Enrique Valdes
 */
public class BufferQueue{
	
    /**
     * Buffer for CSV parsed lines
     */
    public static BlockingQueue<String> dataBuffer = new LinkedBlockingQueue<String>();

    /**
     * Current number of threads parsing files
     */
    public static int nThreads; 

    /**
     *  Adds CSV line to the buffer
     * @param data String representing a CSV line
     */
    public static void writeLine(String data){

    	dataBuffer.offer(data);
    } 

    /**
     *  Removes CSV line from buffer
     * @return linea the String of the CSV line
     * @throws InterruptedException
     */
    public static String readLine() throws InterruptedException{
       
		String linea = dataBuffer.poll(1000, TimeUnit.MILLISECONDS);
		return linea;
    }
}