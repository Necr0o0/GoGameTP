package org.tpproject.maven.Controller;

import org.tpproject.maven.Controller.Model.Enums.PlayerColor;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class User implements Runnable {

	public Socket socket;
	public PlayerColor color;

	Scanner in;
	PrintWriter out;

	public User(Socket socket, PlayerColor color ) {
		this.socket = socket;
		this.color = color;
	}

	@Override
	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch( Exception e ) {}
		EchoReceivedString();
	}

	// Receive string, print it into console, then send back to the client:
	public void EchoReceivedString() {
		while( in.hasNextLine() ) {
			String line = in.nextLine();
			System.out.println( "Received: " + line );
			out.println( line );
		}
	}
}
