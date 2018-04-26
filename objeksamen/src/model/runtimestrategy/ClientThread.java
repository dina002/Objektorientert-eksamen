package model.runtimestrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import model.Messagetype;
import model.SuperStrategy;

public class ClientThread extends SuperStrategy  {

	private StockfishProcess stockfishProcess;

	public ClientThread(String processFilename, StockfishProcess stockfishProcess, BufferedReader br) {
		this.stockfishProcess = stockfishProcess;
		commandReader = br;
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec(processFilename);
			processWriter = new OutputStreamWriter(proc.getOutputStream());
			processReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void talkToStockfish(String command) {

		try {
			// stockfish gets command
			processWriter.write(command+"\n");
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
