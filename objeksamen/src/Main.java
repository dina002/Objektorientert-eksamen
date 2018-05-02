
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gui.InputToStockfish;
import gui.SjakkGUI;
import server.Server;

public class Main implements Serializable{

	public static void main(String[] args) throws IOException{
		try {
			Server server = new Server();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Board board = new Board();
		//SjakkGUI s = new SjakkGUI("User one");
		//new SjakkGUI("User two");
		new InputToStockfish();
		
		//ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Test.dat"));
        //output.writeObject(s);
        //output.close();
		
	}

}
