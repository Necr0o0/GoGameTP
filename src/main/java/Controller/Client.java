package Controller;

import Model.Enums.PlayerColor;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	// Connection and input/output variables:
	private Socket socket;
	private Scanner in;
	private PrintWriter out;

	// Player's color:
	public PlayerColor color;

	// Client constructor:
	public Client( String server_address ) throws Exception {
		socket = new Socket( server_address, Server.server_port );
		in = new Scanner( socket.getInputStream() );
		out = new PrintWriter(socket.getOutputStream(), true);
	}

	// Method for testing purposes, might remove later:
	public void SendMessage( String message ) {
		out.println( message );
	}

	public String ReceiveMessage() {
		if( in.hasNextLine() ) {
			return in.nextLine();
		} else {
			return "Something went wrong";
		}
	}

	// args[0] should be the server's address, localhost or 127.0.0.1 for now:
	public static void main(String[] args ) throws Exception {
		if( args.length != 1 ) {
			System.err.println("Pass the server IP as the sole command line argument");
			return;
		}
		Client client = new Client( args[0] );

		System.out.println( client.in.nextLine() ); // Hello world (...)
		System.out.println( client.in.nextLine() ); // My colour (...)
		client.out.println( "From Client to Server!" );
	}

}
