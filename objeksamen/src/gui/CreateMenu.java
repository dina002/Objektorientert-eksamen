package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class CreateMenu{

	CreateMenu(GUIUpdater updater, SjakkGUI gui) {
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem startMenuItem = new JMenuItem("Start spill");
    JMenuItem exitMenuItem = new JMenuItem("Lukk spill");
    file.add(startMenuItem);
    file.add(exitMenuItem);
    menubar.add(file);
    gui.setJMenuBar(menubar);

    exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	try {
            		gui.socket.close();
            	}catch (Exception e) {}
            	
                 System.exit(0);
            }
        });

     startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              updater.talkToStockfish("position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 \n");
              updater.talkToStockfish("d \n");
             
            }
        });
     

  }

}

