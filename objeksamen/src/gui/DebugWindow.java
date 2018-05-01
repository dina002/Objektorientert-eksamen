package gui;


//import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import gui.SjakkGUI;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.StyleConstants;

import model.Messagetype;

@SuppressWarnings("serial")
public class DebugWindow extends JFrame implements Observer {
	
	private static final int W = 500;
	private JTextPane p = new JTextPane();
	private int messagecount;
	int count = 0;
	private StyleToggler styleToggler = new StyleToggler();
	SjakkGUI gui1 = new SjakkGUI("Sjakkbruker 1");
	SjakkGUI gui2 = new SjakkGUI("Sjakkbruker 2");
	
	
	
	public DebugWindow(String title) {
		setTitle(title);
		add(new JScrollPane(p));
		setLocation(200 + (++count * W), 40);
		setSize(W,400);
		setVisible(true);
		p.setContentType("HTML/plain");
	}

	public void write(String s) {
		Document doc = p.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), "\n" + s, styleToggler.next());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Messagetype m = (Messagetype) arg;
		updateGUI(m.getMsg());
		System.out.println("start| \n" + m.getMsg() +"\n |slutt ");
		write(" #" + ++messagecount + " " + m.getType());
		write(m.getMsg() + "\n --------------");		
	}
	
	public void updateGUI(String arg) {
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
			// System.out.println(spill); // for debugging
			
			String[] rader = spill.split("/");
			for(int rad = 0;rad <= 7; rad++) {
				String[] brikke = rader[rad].split("");
				if(brikke.length <= 1) {
					
				}
				else {
					for(int kolonne = 0; (kolonne + 1) <= brikke.length ; kolonne++) {
						
						System.out.println(brikke[kolonne]);
						
						gui1.setbrikke(rad,kolonne,brikke[kolonne]);
						gui2.setbrikke(rad,kolonne,brikke[kolonne]);
						
					}
				}
			}
		}
		
	}
	
	
}

