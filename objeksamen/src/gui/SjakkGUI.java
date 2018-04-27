package gui;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javafx.scene.paint.Color;

@SuppressWarnings("serial")
public class SjakkGUI extends JFrame {
	
	//public static final int squareCount = 64;
	JButton[][] sjakkKnapper = new JButton[8][8];
	
	public SjakkGUI(String user) {		
		
		String title = "Sjakk  " + user;
		java.awt.Color blackColor = java.awt.Color.BLACK;
		java.awt.Color whiteColor = java.awt.Color.WHITE;
		
		JButton chessButton = null;
		int i = 1;// for å sette fargene på knappene
		for(int kolonne = 0; kolonne <= 7; kolonne++) {
			for(int rad = 0; rad <= 7; rad++ ) {
				chessButton = new JButton();
				
				if(i % 2 == 0) {
					chessButton.setBackground(blackColor);
					sjakkKnapper[kolonne][rad]= chessButton;
					add(chessButton);
				}
				else {		
					chessButton.setBackground(whiteColor);
					sjakkKnapper[kolonne][rad]= chessButton;
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
		
	}
	
	public void setbrikke(int rad,int kolonne , String typeBrikke) {
		Image img = null;
		
		try {
			switch(typeBrikke) {
			case "n":
				img = ImageIO.read(getClass().getResource("/img/knighthvit.png"));
				break;
			case "k":
				img = ImageIO.read(getClass().getResource("/img/kinghvit.png"));
				break;
			case "q":
				img = ImageIO.read(getClass().getResource("/img/queenhvit.png"));
				break;
			case "r":
				
				img = ImageIO.read(getClass().getResource("/img/rookhvit.png"));
				break;
			case "b":
				img = ImageIO.read(getClass().getResource("/img/bishophvit.png"));
				break;
			case "p":
				img = ImageIO.read(getClass().getResource("/img/pawnhvit.png"));
				break;
			case "N":
				img = ImageIO.read(getClass().getResource("/img/knightsvart.png"));
				break;
			case "K":
				img = ImageIO.read(getClass().getResource("/img/kingsvart.jpg"));
				break;
			case "Q":
				img = ImageIO.read(getClass().getResource("/img/queensvart.png"));
				break;
			case "R":
				img = ImageIO.read(getClass().getResource("/img/rooksvart.png"));
				break;
			case "B":
				img = ImageIO.read(getClass().getResource("/img/bishopsvart.png"));
				break;
			case "P":
				img = ImageIO.read(getClass().getResource("/img/pawnsvart.png"));
				break;
			}
		} catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		 
		
		
		
		JButton knapp = sjakkKnapper[rad][kolonne];
		knapp.setIcon(new ImageIcon(img));
		
		
		
	}
	
	
	
	
}
