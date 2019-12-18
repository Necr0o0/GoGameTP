package Controller;

import Model.DefaultGameLogic;
import Model.Enums.PlayerColor;
import Model.GameMechanics.Chain;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Runnable {

	// Connection-related fields
	public Socket socket;
	public Scanner in;
	public PrintWriter out;

	// Gameplay-related fields
	public PlayerColor color;
	public ArrayList<Chain> chain_list = new ArrayList<>();
	public User opponent;
	public DefaultGameLogic game;
	public int captured = 0;  // the number of stones the player has captured so far
	public int territory = 0;
	public int final_score = 0;

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
		while (in.hasNextLine() && game.in_progress ) {
			String[] command = in.nextLine().split(" ");

			if( command[0].equals("PUT_STONE") ) {
				int y = Integer.parseInt(command[1]);
				int x = Integer.parseInt(command[2]);
				if (game.ValidateMove(this, x, y)) {
					game.prev_was_passed = false;
					game.PlaceStone(x, y);
					out.println("LOG wykonano_ruch");
					opponent.out.println("LOG twój_ruch");
					out.println("VALID_MOVE " + x + " " + y);
					opponent.out.println("OPPONENT_MOVED " + x + " " + y);
					game.current_player = this.opponent;  // End your turn
				} else {
					out.println("LOG nie_wolno");
				}
			} else if( command[0].equals("PASS") ) {
				if( game.ValidatePass( this ) && game.prev_was_passed ) {
					// End the game if both players have passed consecutively
					game.ConcludeGame();
				} else if( game.ValidatePass( this ) ) {
					// Otherwise just pass the turn if you can
					game.prev_was_passed = true;
					out.println("LOG spasowano_turę");
					opponent.out.println("LOG przeciwnik_spasował");
					game.current_player = this.opponent;  // End your turn
				} else {
					// Or don't, if you can't
					out.println("LOG nie_wolno_spasować");
				}
			}
		}
	}

}
