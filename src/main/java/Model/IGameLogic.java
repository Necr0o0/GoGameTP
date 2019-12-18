package Model;

import Controller.User;

public interface IGameLogic {
	boolean ValidateMove( User user, int xPos, int yPos );
	boolean ValidatePass( User user );
	void PlaceStone( int xPos, int yPos );

}
