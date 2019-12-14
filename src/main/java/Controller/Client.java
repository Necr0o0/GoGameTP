package Controller;

import Model.Enums.PlayerColor;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import View.GameplayView;

public class Client extends Thread {

	// Connection and input/output variables:
	private Socket socket;
	private Scanner in;
	private PrintWriter out;

	// Player's color:
	GameplayView game_view;
	public PlayerColor color;
	public PlayerColor enemy_color;

	// Client constructor:
	public Client( String server_address ) throws Exception {
		socket = new Socket( server_address, Server.server_port );
		in = new Scanner( socket.getInputStream() );
		out = new PrintWriter(socket.getOutputStream(), true);
	}

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

	// Process data received from the server:
	public void PlayLoop() {
		while (in.hasNextLine()) {
			String[] command = in.nextLine().split(" ");
			if( command[0].equals("VALID_MOVE") ) {
				System.out.println("Received " + "VALID_MOVE");
				int y = Integer.parseInt( command[1] );
				int x = Integer.parseInt( command[2] );
				game_view.PlaceStone( x, y, color );
			} else if ( command[0].equals("OPPONENT_MOVED") ) {
				System.out.println("Received " + "OPPONENT_MOVED");
				int y = Integer.parseInt( command[1] );
				int x = Integer.parseInt( command[2] );
				game_view.PlaceStone( x, y, enemy_color );
			}
		}

	}

	// args[0] should be the server's address, localhost or 127.0.0.1 for now:
	public static void main(String[] args ) throws Exception {


	}

	@Override
	public void run() {


		//System.out.println(client.socket.getRemoteSocketAddress());
		this.color = PlayerColor.valueOf(this.in.next()); // Get assigned color from server
		System.out.println("CLIENT SIE ODPALIL");
		this.enemy_color = ( this.color == PlayerColor.Black ? PlayerColor.White : PlayerColor.Black );
		this.game_view = new GameplayView( this );        // Launch the window with the game
		this.PlayLoop();

	}
}
