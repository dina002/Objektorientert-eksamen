
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import server.Server;
import gui.SjakkGUI;


public class Main implements Serializable{

	public static void main(String[] args) throws IOException{
		SjakkGUI s1 = new SjakkGUI("User one");
		SjakkGUI s2 = new SjakkGUI("User two");
		
		
		try {
			new Server(s2);
		} catch (Exception e) { System.out.println(e + "hei");
		}
		
		
		
	}

}
