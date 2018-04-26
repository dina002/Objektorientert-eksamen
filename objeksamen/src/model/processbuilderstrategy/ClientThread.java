package model.processbuilderstrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import model.Messagetype;
import model.SuperStrategy;

public class ClientThread  extends SuperStrategy  {

	private StockfishProcess stockfishProcess;

	public ClientThread(String processFilename, StockfishProcess stockfishProcess, BufferedReader br) {
		this.stockfishProcess = stockfishProcess;
		commandReader = br;
		ProcessBuilder pb = new ProcessBuilder(processFilename);
		try {
			Process  process = pb.start();
			processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			processWriter = new OutputStreamWriter(process.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected  void talkToStockfish(String command) {
		try {
			// stockfish gets command
			processWriter.write(command + "\n");
			processWriter.flush();
			processWriter.write("isready\n");
			processWriter.flush();
			
			// stockfish answers
			String l = "";
			String result = "";
			while (!(l = processReader.readLine()).equals("readyok")) {
				result += l + "\n";
			}
			stockfishProcess.update(new Messagetype("result", result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
