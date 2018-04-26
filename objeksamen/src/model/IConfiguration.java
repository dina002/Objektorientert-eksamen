package model;

public interface IConfiguration {
	
	// a couple of alternatives for user names. Edit according to your own configuration
	String USERNAME_ON_YOGA = "win";
	String USERNAME_ON_DESKTOP_E_V_000064 = "vh";

	// create or edit according to your location of the process file
	String PROCESS_FILE_LOCATION_ON_THE_YOGA = "C:/Users/" + USERNAME_ON_YOGA + "/Documents/stockfish-9-win/Windows/stockfish_9_x64.exe";
	String PROCESS_FILE_LOCATION_ON_THE_DESKTOP = "C:/Users/Simon/Desktop/stockfish_9_x32.exe";
		
	// better see the proper documentation for the uci protocol
	String FEN_EXAMPLE = "8/6pk/8/1R5p/3K3P/8/6r1/8 b - - 0 42"; 
	
}
