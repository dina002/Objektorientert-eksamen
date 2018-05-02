package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import gui.SjakkGUI;

public class Server {
    public static final int port = 4444;
    private ServerSocket ss = null;

    public Server(SjakkGUI gui) throws IOException, ClassNotFoundException{

    	ss = new ServerSocket(port);
        System.out.println("Systemet er klar til � godta tilkoblinger");
        Socket socket = ss.accept();
        System.out.println("Systemet er klar til � godta tilkoblinger1");
        InputStream       inps = null;
				ObjectInputStream   in = null;
				System.out.println("test1");
        inps = socket.getInputStream();
        System.out.println("test2");
        in = new ObjectInputStream(inps);
        System.out.println("test3");
        String inn = in.readObject().toString();
        System.out.println("inn" + inn);
        socket.close();
        gui.updater.updateGUI(inn);
        gui.updater.updateGUI("d \n");
        System.out.println("Systemet er klar til � godta tilkoblinger5");

    }




}
