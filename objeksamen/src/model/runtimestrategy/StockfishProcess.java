package model.runtimestrategy;

import java.io.BufferedReader;
import java.util.Observable;

import model.IConfiguration;
import model.Messagetype;

public class StockfishProcess extends Observable implements IConfiguration  {
	
	private static String processFilename = PROCESS_FILE_LOCATION_ON_THE_DESKTOP;
	private ClientThread client;
		
	public StockfishProcess(BufferedReader reader) {	
		client = new ClientThread(processFilename, this, reader);
		client.start();		
	}
	
	public void update(Messagetype message) {
		setChanged();
		notifyObservers(message);
		clearChanged();		
	}

}
