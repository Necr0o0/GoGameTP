package Model.GameMechanics;

import Model.Enums.PlayerColor;

public class Stone {
	public int xPos, yPos;
	public PlayerColor color;
	public Chain parent_chain;

	public Stone( int xPos, int yPos, PlayerColor color ) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}

	public Stone( Stone position_of ) {
		this.xPos = position_of.xPos;
		this.yPos = position_of.yPos;
	}

	public Chain GetChain() {
		return parent_chain;
	}

	public void Remove() {
		this.color = null;
		parent_chain.stones.remove(this);
		this.parent_chain = null;
	}
}
