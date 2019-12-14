package Model;

import Controller.User;
import Model.GameMechanics.Stone;

public class DefaultGameLogic implements IGameLogic {
	public User current_player;
	public Stone[][] board;

	public DefaultGameLogic() {
		board = new Stone[19][19];

	}

	public boolean ValidateMove( User user ) {
		return IsCurrentPlayer(user);
	}

	public boolean IsCurrentPlayer( User user ) {
		// Makes the game turn-based; no player can make two moves in a row.
		return (user == current_player);
	}

}
