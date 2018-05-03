import gui.SjakkGUI;


public class Main {

	public static void main(String[] args) {
		
		String ip = "localhost";
		int port = 222;
		
		
		
		new SjakkGUI("User one",ip,port);
		new SjakkGUI("User two",ip,port);
		
		
		
	}

}
