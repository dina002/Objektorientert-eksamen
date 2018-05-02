package gui;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Messagetype;
import model.processbuilderstrategy.StockfishProcess;

public class GUIUpdater implements Observer{
	SjakkGUI gui;
	private PrintWriter pw;
	GUIUpdater updater = this;
	public GUIUpdater(SjakkGUI gui) {
		this.gui=gui;
		
		try {
    		
			PipedWriter pipedwriter;
			BufferedReader reader = new BufferedReader(new PipedReader(pipedwriter = new PipedWriter()) );
			pw = new PrintWriter(pipedwriter);
			
			StockfishProcess stockfish = new StockfishProcess(reader);
			stockfish.addObserver(updater);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Messagetype m = (Messagetype) arg;
		updateGUI(m.getMsg());
		
	}
	
	public void talkToStockfish(String input) {
		pw.print(input);
	}
	
	public void rensbrett() { // funksjon for å reste brettet		
		for (int index = 0; index <= 7; index++) { 
			for (int index2 = 0; index2 <= 7; index2++) {
				JButton knapp = gui.sjakkKnapper[index][index2]; 
				knapp.setIcon(null);
			}
		}
	}
	
	public void updateGUI(String arg) {
		rensbrett();
		
		System.out.println(arg);
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
			JButton knapp = gui.sjakkKnapper[rad][kolonne];
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
