package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private String ip = "localhost";
	private int port = 80;
	private Scanner scanner = new Scanner(System.in);
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private Thread thread;
	private Socket socket;
	private boolean accepted = false;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ServerSocket serverSocket;
	private String waitingString = "Venter på ny spiller";
	private int errors = 0;
	private boolean yourTurn = false;
	
	public Server() {
		System.out.println("Skriv inn IP: ");
		ip = scanner.nextLine();
		System.out.println("Skriv inn port: ");
		port = scanner.nextInt();
		while (port < 1 || port > 65535) {
			System.out.println("Porten kan ikke nås, velg en annen: ");
			port = scanner.nextInt();
		}
		
		if (!connect()) initializeServer();

		thread = new Thread("Sjakk");
		thread.start();
	}
	
	public void run() {
		while (true) {
			
			if (!accepted) {
				listenForServerRequest();
			}

		}
	}
	
	public void listenForServerRequest() {
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
	
	boolean connect() {
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
	
	public void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

