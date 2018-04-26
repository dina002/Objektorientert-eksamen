package gui;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class StyleToggler extends SimpleAttributeSet {
	
	private SimpleAttributeSet[] sets = new SimpleAttributeSet[2];
	private int toggler = 0;
	
	public StyleToggler() {
		sets[0] = new SimpleAttributeSet() { {
			StyleConstants.setForeground(this, Color.RED);	
			StyleConstants.setFontFamily(this, "serif");
			StyleConstants.setFontSize(this, 16);
		}};		
		sets[1] = new SimpleAttributeSet() {{
		StyleConstants.setForeground(this, Color.BLUE);
		StyleConstants.setFontFamily(this, "monospaced");
		}};		
	}
	
	public AttributeSet next() {
		return sets[toggler++ % 2];
	}

}
