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
	SjakkGUI gui = new SjakkGUI("bruker 1");
	
	
	
	
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
		gui.updateGUI(m.getMsg());
		//System.out.println("start| \n" + m.getMsg() +"\n |slutt ");
		write(" #" + ++messagecount + " " + m.getType());
		write(m.getMsg() + "\n --------------");		
	}
	
	
	
	
}

