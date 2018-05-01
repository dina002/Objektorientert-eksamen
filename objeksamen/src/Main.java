
import gui.InputToStockfish;
import gui.SjakkGUI;
import server.Server;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		//Board board = new Board();
		new SjakkGUI("User one");
		new SjakkGUI("User two");
		//new InputToStockfish();
	}

}
