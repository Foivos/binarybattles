package referee;

import java.util.Collection;

import rules.Grid;
import rules.GridCoords;

public abstract class GridReferee extends BaseReferee{
	public Grid grid;
	private Collection<Move> moves;
	private int activePlayer = 0;
	private int movesPerRound;
	
	@Override
	public int turn() {
		send();
		receive();
		validateMoves();
		int winner = winningConditions();
		activePlayer = (activePlayer + 1) % players.size();
		return winner;
	}
	
	public void send() {
		Communicator communicator = new Communicator(players.get(activePlayer));
		for(int i=0; i<grid.width; i++) {
			for(int j=0; j<grid.height; j++) {
				if(grid.pieces[i][j] == null) {
					continue;
				}
				players.get(activePlayer).send(grid.pieces[i][j].toString());
			}
		}
	}
	
	public void receive() {
		for(int i=0; i<movesPerRound; i++) {
			String response = players.get(activePlayer).read();
			GridCoords coord1 = new GridCoords(), coord2 = new GridCoords();
			coord1.x = Integer.parseInt(response);
			coord1.y = Integer.parseInt(response);
			coord2.x = Integer.parseInt(response);
			coord2.y = Integer.parseInt(response);
			moves.add(new Move(coord1, coord2));
		}
	}
	
	public boolean validateMoves() {
		for(Move move : moves) {
			if(!grid.getPiece(move.coord1).canMove(move.coord2)) {
				return false;
			}
		}
		return true;
	}
	
	private class Move {
		public GridCoords coord1, coord2;
		
		public Move(GridCoords coord1, GridCoords coord2) {
			this.coord1 = coord1;
			this.coord2 = coord2;
		}
	}
}
