package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

import gui.CreateMenu;


public class SjakkGUI extends JFrame {


	private static final long serialVersionUID = 1L;
	protected int port;
	protected String ip;
    ServerSocket ss = null;
	JButton[][] sjakkKnapper = new JButton[8][8];
	SjakkGUI gui= this;
	public GUIUpdater updater = new GUIUpdater(gui);
	public Socket socket = null;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;

	public SjakkGUI(String user,String ip,int port) {
		this.port = port;
		this.ip = ip;
		new CreateMenu(updater,gui);

		ActionListener actionListener = new ActionListener()
		 {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  updater.talkToStockfish("position startpos moves e2e4 \n");
		    	  updater.talkToStockfish("d \n");
		    	  send("position startpos moves e2e4 \n");
		    	  motta();

		      }
		    };



		String title = "Sjakk  " + user;
		java.awt.Color blackColor = java.awt.Color.BLACK;
		java.awt.Color whiteColor = java.awt.Color.WHITE;

		JButton chessButton = null;
		int i = 1;// for ï¿½ sette fargene pï¿½ knappene
		for(int kolonne = 0; kolonne <= 7; kolonne++) {
			for(int rad = 0; rad <= 7; rad++ ) {
				chessButton = new JButton();

				if(i % 2 == 0) {
					chessButton.setBackground(blackColor);
					sjakkKnapper[kolonne][rad]= chessButton;
					sjakkKnapper[kolonne][rad].addActionListener(actionListener);
					add(chessButton);
				}
				else {
					chessButton.setBackground(whiteColor);
					sjakkKnapper[kolonne][rad]= chessButton;
					sjakkKnapper[kolonne][rad].addActionListener(actionListener);
					add(chessButton);
				}

				if(i % 8 == 0) {
					java.awt.Color temp = blackColor;
					blackColor = whiteColor;
					whiteColor = temp;

				}
				i ++;
			}

		}

		this.setTitle(title);
		this.setLayout(new GridLayout(8, 18));
		this.setSize(650, 650);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			ss = new ServerSocket(port);
		} catch(Exception e) { System.out.println(e +"prøver å lage server");}
		
		try {
			System.out.println("welcome client");
	        socket = new Socket(ip, port);
	        System.out.println("Client connected");
		} catch(Exception e) { System.out.println(e +"prøver å lage client");}
		
				
	}

	private void send(String send) {
		OutputStream       outps = null;
		ObjectOutputStream   out = null;
		try {
			outps = socket.getOutputStream();
			out = new ObjectOutputStream(outps);
			out.writeObject(send);
			System.out.println("dette ble sendt " + send);
		} catch (Exception e) {System.out.println("synj");}
	}
	
	protected void motta() {
		try {
			
	        InputStream       inps = null;
			//ObjectInputStream   in = null;
			String inn = "";
			System.out.println("test1");
	        inps = socket.getInputStream();
	        System.out.println("test2");
	        BufferedInputStream bis = new BufferedInputStream(inps);
	        System.out.println("test3");
	        
    		
	        while( bis.available() < 0 ) {
	        		ObjectInputStream ois = new ObjectInputStream(bis);
	        		inn = ois.readObject().toString();
	        	}
			
	        System.out.println("inn" + inn);
	        
	        updater.updateGUI(inn);
	        updater.updateGUI("d \n");
		} catch (Exception e) { System.out.println(e +"prøver å koble til server");}
	}
}
