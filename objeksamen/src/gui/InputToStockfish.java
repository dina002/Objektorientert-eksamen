package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/*
 * This window takes input to the engine
 */

public class InputToStockfish extends JFrame {
	
	private Inputpane inputpane;

	public InputToStockfish() {
		setTitle("input to engine");
		add(new JScrollPane(inputpane = new Inputpane()));
		add(new JButton(new AbstractAction("stop") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
			
		}), BorderLayout.SOUTH);
		
		setBounds(100,100,500,300);
		setVisible(true);
	}

}
