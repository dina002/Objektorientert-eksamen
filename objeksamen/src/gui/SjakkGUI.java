package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import server.Message;
import CreateMenu;


public class SjakkGUI extends JFrame {


	//private static final long serialVersionUID = 1L;
	public static final int port = 4444;
    //private ServerSocket ss = null;
	JButton[][] sjakkKnapper = new JButton[8][8];
	SjakkGUI gui= this;
	public GUIUpdater updater = new GUIUpdater(gui);
	Socket socket = null;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;

	public SjakkGUI(String user) {

		new CreateMenu.createMenuBar();

		ActionListener actionListener = new ActionListener()
		 {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  updater.talkToStockfish("position startpos moves e2e4 \n");
		    	  updater.talkToStockfish("d \n");
		    	  send("position startpos moves e2e4 \n");

		      }
		    };



		String title = "Sjakk  " + user;
		java.awt.Color blackColor = java.awt.Color.BLACK;
		java.awt.Color whiteColor = java.awt.Color.WHITE;

		JButton chessButton = null;
		int i = 1;// for � sette fargene p� knappene
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
	}

	private void send(String send) {
		OutputStream       outps = null;
		ObjectOutputStream   out = null;
		try {
			System.out.println("welcome client");
	        socket = new Socket("localhost", 4444);
	        System.out.println("Client connected");
			outps = socket.getOutputStream();
			out = new ObjectOutputStream(outps);
			out.writeObject(send);
			socket.close();
		} catch (Exception e) {System.out.println("synj");}
	}
}
