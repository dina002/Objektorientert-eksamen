package gui;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javafx.scene.paint.Color;


@SuppressWarnings("serial")
public class SjakkGUI extends JFrame {
	
	
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
		/*JButton startButton = new JButton();
		startButton.setVisible(true);
		startButton.setText("Start");
		add(startButton);
		JButton sluttButton = new JButton();
		sluttButton.setVisible(true);
		sluttButton.setText("Start");
		add(sluttButton);
		*/
		
	}
	
	
	public void rensbrett() { // funksjon for å reste brettet
		System.out.println("nå kommer kostebilen");
		Image img = null; 
		
		for (int index = 0; index >= 7; index++) { 
			for (int index2 = 0; index2 >= 7; index2++) {
				System.out.println(index + index2 + "nå ble jeg glad <3");
				JButton knapp = sjakkKnapper[index][index2]; 
				knapp.setIcon(new ImageIcon(img));
			}
		}
		
	}
	
	
	
	
	public void updateGUI(String arg) {
		rensbrett();
		
		String[] linje = arg.split("\n");
		String spill = null;
		if(linje.length>=22) {
			for(int i =0;i<=21;i++) {
				String[] del = linje[i].split(" ");
				//System.out.println(del[0]); //for debugging 
				if(del[0].equals("Fen:")) {
					
					spill= del[1];
					
				}
			}
			//System.out.println(spill); // for debugging
			
			String[] rader = spill.split("/");
			for(int rad = 0;rad <= 7; rad++) {
				
				//System.out.println(rader[rad]); // for debugging
				
				if(rader[rad].length() == 2) { // hvis det er kun en brikke på raden i starten eller slutten
					String[] brikke = rader[rad].split("");
					if(isNumeric(brikke[0])) {
						setbrikke(rad,7,brikke[1]);
					} else {
						setbrikke(rad,0,brikke[0]);
					}
					
				} 
				else if(rader[rad].length() == 3) { // hvis det er kun en brikke midt på brettet
					String[] brikke = rader[rad].split("");
					int kolonne = Integer.parseInt(brikke[0]); 
					setbrikke(rad,kolonne,brikke[1]);
				} 
				else { // hvis det er en hel rad men
					String[] brikke = rader[rad].split("");
					if(brikke.length <= 1) {
						// da er det en tom rad
					}
					else {
						for(int kolonne = 0; (kolonne + 1) <= brikke.length ; kolonne++) {
												
							setbrikke(rad,kolonne,brikke[kolonne]);
							
						}
					}
				}
			}
		}
		
	}
	
	
	
	
	public void setbrikke(int rad,int kolonne , String typeBrikke) { // funksjon som setter bildet til brikke, på riktig plass
		Image img = null;
		if(!isNumeric(typeBrikke)) {
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
					img = ImageIO.read(getClass().getResource("/img/kingsvart.png"));
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
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
	
	
}
