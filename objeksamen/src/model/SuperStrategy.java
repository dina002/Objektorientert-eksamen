package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;



public abstract class SuperStrategy extends Thread {

	protected BufferedReader commandReader;
	protected BufferedReader processReader;
	protected OutputStreamWriter processWriter;
	
	int count = 0;
	
	public void run() {
		String command;
		try {
			while ((command = commandReader.readLine()) != null) {
				if (!command.isEmpty()) {
					talkToStockfish(command);
				}
				else System.out.println("command was empty, was not sent to stockfish");
				System.out.println("Command #" + ++count + " [" + command + "] length " + command.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}

	abstract protected void talkToStockfish(String command); 
	
}
