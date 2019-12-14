package Model.GameMechanics;

import Controller.User;

import java.util.ArrayList;

public class Chain {
	public ArrayList<Stone> stones = new ArrayList<>();
	public ArrayList<Stone> liberties = new ArrayList<>();

	public Chain() {

	}

	// A function to form chains of stones in such a way so that
	// every stone holds a reference to its parent chain:
	public void AddStone( Stone stone, ArrayList<Stone> liberties ) {
		stone.parent_chain = this;
		this.stones.add( stone );
		this.liberties.addAll( liberties );
	}

	public void RemoveLiberty( Stone where ) {
		Stone target = null;
		for( int i=0; i<liberties.size() && target == null; i++ ) {
			Stone tmp = liberties.get(i);
			if( tmp.xPos == where.xPos && tmp.yPos == where.yPos )
				target = tmp;
		}
		liberties.remove( target );
	}

	public void ClearStones( User user ) {
		// Can't just .clear() it, because the board needs to be updated too
		while( stones.size() != 0 ) {
			Stone s = stones.get(0);
			user.out.println( "CLEAR " + s.xPos + " " + s.yPos );
			user.opponent.out.println("CLEAR " + s.xPos + " " + s.yPos );

			s.Remove(); // First do some stuff in the Stone object, then actually remove it from the list
		}
	}

}
