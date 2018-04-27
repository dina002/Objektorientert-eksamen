
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//we use the built-in Java library ServerSocket to create our server.
//specify the IP using the local IPv4 address which is assigned by your router. Normally something like 192.168.XX.XXX.﻿
public class Server implements Runnable {
	
	private String ip = "localhost";
	private int port = 80;
	private Scanner scanner = new Scanner(System.in);
	private Thread thread;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean unableToCommunicateWithOpponent = false;
	private ServerSocket serverSocket;
	private boolean accepted = false;
	private int errors = 0;

	
	public Server() {
		System.out.println("Please input the IP: ");
		ip = scanner.nextLine();
		System.out.println("Please input the port: ");
		port = scanner.nextInt();
		while (port < 1 || port > 65535) {
			System.out.println("The port you entered was invalid, please input another port: ");
			port = scanner.nextInt();
		}
		
		if (!connect()) initializeServer();

		thread = new Thread(this, "Sjakk");
		thread.start();
	}

	private void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}
	
	private void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			if (!accepted) {
				listenForServerRequest();
			}
		}
	}
}

	
	


