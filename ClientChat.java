

import java.net.*;
import java.io.*;

public class ClientChat {
	private String hostname;
	private int port;
	private String userName;
	
	public ClientChat(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			
			System.out.println("Connected to the server chat");
			
			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();
			
		} catch(UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch(IOException ex) {
			System.out.println("I/O Error: " + ex.getMessage());
		}
	}
	
	void setUserName(String userName) {
		this.userName = userName;
	}
	
	String getUserName() {
		return this.userName;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2) return;
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		ClientChat client = new ClientChat(hostname, port);
		client.execute();

	}

}
