package org.tpproject.maven;

import Controller.User;
import Model.DefaultGameLogic;
import Model.Enums.PlayerColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultGameLogicTest {
	private DefaultGameLogic game = new DefaultGameLogic( 19 );

	@Test
	public void PlaceStoneTest() {
		game.current_player = new User( null, PlayerColor.Black, game );

		game.PlaceStone( 2, 1 );

		PlayerColor placed_color = game.board[2][1].color;
		assertEquals( game.current_player.color, placed_color );
	}

	@Test
	public void MergeChainsTest() {
		game.current_player = new User( null, PlayerColor.Black, game );

		game.PlaceStone( 2, 1 );
		game.PlaceStone( 2, 3 );

		int chain_count = game.current_player.chain_list.size();
		assertEquals( 2, chain_count );

		game.PlaceStone( 2, 2 );

		chain_count = game.current_player.chain_list.size();
		assertEquals( 1, chain_count );
	}

	@Test
	public void ShareLibertiesTest() {
		game.current_player = new User( null, PlayerColor.Black, game );

		game.PlaceStone( 3, 7 );  // Make a small "L" shape
		game.PlaceStone( 3, 8 );
		game.PlaceStone( 4, 8 );

		int[] liberties = new int[3];
		liberties[0] = game.board[3][7].parent_chain.liberties.size();
		liberties[1] = game.board[3][8].parent_chain.liberties.size();
		liberties[2] = game.board[4][8].parent_chain.liberties.size();
		int[] expected = {7, 7, 7};

		assertArrayEquals( expected, liberties );
	}

}
