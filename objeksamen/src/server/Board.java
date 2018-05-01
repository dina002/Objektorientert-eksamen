import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {

	private JFrame frame;
	private BufferedImage board;
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private Painter painter;
	private boolean yourTurn = false;
	private boolean accepted = false;
	private String waitingString = "Waiting for another player";
	private String unableToCommunicateWithOpponentString = "Unable to communicate with opponent.";
	private boolean unableToCommunicateWithOpponent = false;
	private int errors = 0;
	private Font smallerFont = new Font("Verdana", Font.BOLD, 20);

	public Board() {
		
		painter = new Painter();
		painter.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame();
		frame.setTitle("Sjakk");
		frame.setContentPane(painter);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);


	}

	public void run() {
		while (true) {
			painter.repaint();
		}
	}

	private void render(Graphics g) {
		g.drawImage(board, 0, 0, null);
	}



	private class Painter extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;

		public Painter() {
			setFocusable(true);
			requestFocus();
			setBackground(Color.WHITE);
			addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			render(g);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			repaint();
			Toolkit.getDefaultToolkit().sync();
			System.out.println("DATA WAS SENT");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}


}
