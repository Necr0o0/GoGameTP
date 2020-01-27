package Model;

import Controller.User;
import Model.Enums.PlayerColor;
import Model.GameMechanics.Chain;
import Model.GameMechanics.Stone;

import java.util.ArrayList;

public class DefaultGameLogic implements IGameLogic {
	public User current_player;
	public Stone[][] board;
	int board_size;
	// Fields related to the ko rule:
	public Stone ko = null;
	int last_destroyed_stone_count = 0;
	// Fields related to finishing the game:
	public boolean prev_was_passed = false;
	public boolean in_progress = true;

	public DefaultGameLogic( int board_size ) {
		board = new Stone[board_size][board_size];
		for( int i=0; i<board_size; i++ ) {
			for( int j=0; j<board_size; j++ ) {
				board[i][j] = new Stone( i, j, null );
			}
		}
		this.board_size = board_size;
	}

	public boolean ValidateMove( int xPos, int yPos ) {
		return ( SpotIsUnoccupied( xPos, yPos )
				&& KoRule( xPos, yPos )
				&& NotSuicidal( xPos, yPos )
		);
	}

	public boolean ValidatePass( User user ) {
		boolean valid = IsCurrentPlayer( user )
			                && HasOpponent( user );
		if( valid ) {
			// Forget about the ko field because a turn has passed
			ko = null;
		}
		return valid;
	}

	public boolean IsCurrentPlayer( User user ) {
		// Makes the game turn-based; no player can make two moves in a row.
		return (user == current_player);
	}

	public boolean HasOpponent( User user ) {
		// Prevent from taking any actions before the other player joins.
		return (user.opponent != null);
	}

	public boolean SpotIsUnoccupied( int xPos, int yPos ) {
		// Return true if attempting to place a stone on an empty field.
		return (board[xPos][yPos].color == null);
	}

	public boolean NotSuicidal( int xPos, int yPos ) {
		// Return true if at least one of the adjacent fields is or will become empty after this move,
		// or if there's a friendly chain with enough liberties nearby.
		ArrayList<Stone> adjacent = GetAdjacent( xPos, yPos );
		for( int i=0; i< adjacent.size(); i++ ) {
			Stone current = adjacent.get(i);
			if( current.color == null
				    || (current.color == current_player.opponent.color && current.GetChain().liberties.size() == 1)
						|| (current.color == current_player.color && current.GetChain().liberties.size() > 1)
			) return true;
		}
		return false;
	}

	public boolean KoRule( int xPos, int yPos ) {
		// Return false if attempting to break the ko rule, and true otherwise.
		if( ko != null ) {
			if( ko.xPos == xPos && ko.yPos == yPos ) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public void PlaceStone( int xPos, int yPos ) {
		// The move was valid, so forget about the ko field:
		ko = null;

		// Get adjacent stones:
		ArrayList<Stone> adjacent = GetAdjacent( xPos, yPos );

		// Create a new, temporary chain:
		Chain new_chain = new Chain();
		Stone new_stone = new Stone( xPos, yPos, current_player.color );

		ArrayList<Chain> friendly_adjacent_chains = new ArrayList<>();
		ArrayList<Stone> new_liberties = new ArrayList<>();

		for( int i=0; i<adjacent.size(); i++ ) {
			Stone current_stone = adjacent.get(i);

			if( current_stone.color == null ) {
				// The spot is empty, add it to the new_chain's liberties list:
					new_liberties.add( current_stone );

			} else if( current_stone.color == current_player.color ) {
				// The spot is occupied by a part of a friendly chain, so it will be merged later.
				if( !friendly_adjacent_chains.contains( current_stone.GetChain() ) )
					friendly_adjacent_chains.add( current_stone.GetChain() );
				// Also take away its liberty:
				current_stone.GetChain().RemoveLiberty( new_stone );

			} else {
				// The spot is occupied by an enemy, so reduce its chain's liberties:
				Chain opponent_chain = current_stone.GetChain();
				opponent_chain.RemoveLiberty( new_stone );

				if( opponent_chain.liberties.size() == 0 ) {
					// Get the number of captured stones for the ko rule:
					last_destroyed_stone_count = opponent_chain.stones.size();
					// Update the capture count for the current player and notify the clients:
					current_player.captured += last_destroyed_stone_count;
					current_player.out.println( "CAP " + current_player.captured );
					current_player.opponent.out.println( "OPPONENT_CAP " + current_player.captured );


					// The chain will be captured as the result of this move,
					// so all chains next to it must have their liberties updated:
					for( int j=0; j<opponent_chain.stones.size(); j++ ) {
						// Iterate over every stone in the soon-to-be-captured chain
						Stone tmp = opponent_chain.stones.get(j);

						ArrayList<Stone> tmp_adj = GetAdjacent( tmp.xPos, tmp.yPos );
						for( int k=0; k<tmp_adj.size(); k++ ) {
							// Look around, and if you see an enemy chain, notify it that
							// your current position will become its new liberty
							Stone tmp_from_another_chain = tmp_adj.get(k);

							if( tmp_from_another_chain.color == current_player.color ) {
								if( !tmp_from_another_chain.GetChain().liberties.contains(tmp) )
									tmp_from_another_chain.GetChain().liberties.add(tmp);
							}
						}

					}
					// The newly placed stone counts its liberties after the loop,
					// because it's not in any Chain yet:
					new_liberties.add( current_stone );

					opponent_chain.ClearStones( current_player );

					// Remove the chain from the player's list:
					current_player.opponent.chain_list.remove( opponent_chain );
				}

			}

		}

		// Apply the ko rule if needed:
		if( friendly_adjacent_chains.size() == 0 && new_liberties.size() == 1 && last_destroyed_stone_count == 1)
			ko = new_liberties.get(0);

		// Add stone to new_chain and pass the information about gained liberties:
		new_chain.AddStone( new_stone, new_liberties );

		// Connect all friendly chains together and assign them to player as new_chain:
		for( int k=0; k<friendly_adjacent_chains.size(); k++ ) {
			Chain ch = friendly_adjacent_chains.get(k);

			// Stones are all distinct, so they can be merged in bulk,
			// but the Stone objects need to update their parent_chain reference:
			for( int p=0; p<ch.stones.size(); p++ ) {
				Stone s = ch.stones.get(p);
				s.parent_chain = new_chain;
			}
			new_chain.stones.addAll( ch.stones );

			// However, two chains may share liberties,
			// so get only distinct ones when merging:
			for( int p=0; p<ch.liberties.size(); p++ ) {
				Stone lp = ch.liberties.get(p);
				if( !new_chain.liberties.contains( lp ) )
					new_chain.liberties.add( lp );
			}
		}

		current_player.chain_list.removeAll( friendly_adjacent_chains );
		current_player.chain_list.add( new_chain );

		// Update the board as well:
		board[xPos][yPos] = new_stone;
	}

	public ArrayList<Stone> GetAdjacent(int xPos, int yPos ) {
		ArrayList<Stone> adjacent = new ArrayList<>();

		// Get the loop boundaries so the program doesn't try to access values outside the `board` array:
		int x_lower = (xPos==0?1:-1);
		int x_upper = (xPos==(board_size-1)?-1:1);
		int y_lower = (yPos==0?1:-1);
		int y_upper = (yPos==(board_size-1)?-1:1);

		for( int i=x_lower; i<=x_upper; i += 2) {
			adjacent.add( board[xPos+i][yPos] );
		}
		for( int j=y_lower; j<=y_upper; j += 2) {
			adjacent.add( board[xPos][yPos+j] );
		}

		return adjacent;
	}

	public void ConcludeGame() {
		in_progress = false;
		CalculateScore();
		// Current player is now black, because of how territory was calculated
		// End the game and print the players' scores:
		current_player.out.println("SHOW_SCORE " + current_player.final_score + " " + current_player.opponent.final_score);
		current_player.opponent.out.println("SHOW_SCORE " + current_player.opponent.final_score + " " + current_player.final_score);
	}

	private void CalculateScore() {
		// First "paint" the board in current player's colour, then their opponent's colour:
		for( int i=0; i<current_player.chain_list.size(); i++ ) {
			Chain ch = current_player.chain_list.get(i);
			for( int j=0; j<ch.stones.size(); j++ ) {
				Stone st = ch.stones.get(j);
				SpreadTerritory( st );
			}
		}
		for( int i=0; i<current_player.opponent.chain_list.size(); i++ ) {
			Chain ch = current_player.opponent.chain_list.get(i);
			for( int j=0; j<ch.stones.size(); j++ ) {
				Stone st = ch.stones.get(j);
				SpreadTerritory( st );
			}
		}

		// Assess which player has which colour, so that Black gets [0] and White gets [1]:
		if( current_player.color == PlayerColor.White ) {
			current_player = current_player.opponent;
		}

		// After that, iterate over every field on the board and assign it to proper player's territory:
		for( int i=0; i<board_size; i++ ) {
			for( int j=0; j<board_size; j++ ) {
				Stone st = board[i][j];
				if( (st.territory_array[0] & st.territory_array[1]) == 0 ) {
					// If this field wasn't surrounded by exclusively one colour...
					current_player.territory += st.territory_array[0];  // (almost) always adds 1 to one and 0 to the other one
					current_player.opponent.territory += st.territory_array[1];

				}
			}
		}

		SetFinalScore();
	}

	private void SpreadTerritory( Stone stone ) {
		ArrayList<Stone> adjacent = GetAdjacent( stone.xPos, stone.yPos );

		for( int i=0; i<adjacent.size(); i++ ) {
			RecursiveSpreadTerritory( adjacent.get(i), stone.color );
		}
	}

	private void RecursiveSpreadTerritory( Stone stone, PlayerColor color ) {
		int arr_index = ( color==PlayerColor.Black?0:1 ); // Black is 0, White is 1

		// If the spot is unoccupied, then "paint" it in your team's colour
		if( stone.territory_array[arr_index] == 0 && stone.color == null ) {
			stone.territory_array[arr_index] = 1;

			ArrayList<Stone> adjacent = GetAdjacent( stone.xPos, stone.yPos );
			for( int i=0; i<adjacent.size(); i++ ) {
				// Paint the adjacent fields with the same colour
				RecursiveSpreadTerritory( adjacent.get(i), color );
			}
		}
	}

	public void SetFinalScore() {
		// The player's score is the size of their territory minus stones captured by the enemy:
		current_player.final_score = current_player.territory - current_player.opponent.captured;
		current_player.opponent.final_score = current_player.opponent.territory - current_player.captured;
	}

}
