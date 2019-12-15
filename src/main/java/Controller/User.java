package Controller;

import Model.DefaultGameLogic;
import Model.Enums.PlayerColor;
import Model.GameMechanics.Chain;
import Model.IGameLogic;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
	Lista komend:
	CLIENT -> SERVER:
		PUT_STONE x y
	SERVER -> CLIENT:
		VALID_MOVE x y
		OPPONENT_MOVED x y

 */
public class User implements Runnable {

	// Connection-related fields
	public Socket socket;
	Scanner in;
	public PrintWriter out;

	// Gameplay-related fields
	public PlayerColor color;
	public ArrayList<Chain> chain_list = new ArrayList<>();
	public User opponent;
	public DefaultGameLogic game; // Would be nice if this could be an interface, but then the variables don't seem to work...
	public int captured = 0;

	public User(Socket socket, PlayerColor color, DefaultGameLogic game ) {
		this.socket = socket;
		this.color = color;
		this.game = game;   // so that the threads can communicate with each other
	}

	@Override
	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch( Exception e ) {}
		out.println( color );   // Assign color to client
		if( color == PlayerColor.Black ) {
			game.current_player = this;
		} else {
			opponent = game.current_player;
			opponent.opponent = this;
		}
		ListenLoop();
	}

	public void ListenLoop() {
		while (in.hasNextLine()) {
			String[] command = in.nextLine().split(" ");
			for( int i=0; i<3; i++ ) {
				System.out.println( command[i] );
			}

			if( command[0].equals("PUT_STONE") ) {
				//System.out.println("Received " + "PUT_STONE");
				int y = Integer.parseInt( command[1] );
				int x = Integer.parseInt( command[2] );
				if( game.ValidateMove( this, x, y ) ) {
					game.PlaceStone( x, y );
					out.println("LOG wykonano_ruch");
					out.println("VALID_MOVE " + x + " " + y);
					opponent.out.println("OPPONENT_MOVED " + x + " " + y);
					game.current_player = this.opponent;  // End your turn
				} else {
					out.println("LOG nie_wolno");
					//System.out.println( "Metoda game.ValidateMove() zwróciła \'false\'");
				}
			}
		}
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
