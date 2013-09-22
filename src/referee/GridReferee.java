package referee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rules.Grid;
import rules.GridCoords;
import rules.Player;

public abstract class GridReferee extends BaseReferee{
	public Grid grid;
	protected Collection<Move> moves = new ArrayList<Move>();;
	protected int activePlayer = 0;
	protected int movesPerRound = 1;
	protected boolean[] droppedOut;
	
	public GridReferee(int width, int height, List<Player> players) {
		grid = new Grid(width, height);
		this.players = players;
		droppedOut = new boolean[players.size()];
		for(int i=0; i<droppedOut.length; i++) {
			droppedOut[i] = false;
		}
	}
	
	@Override
	public int turn() {
		if(droppedOut[activePlayer]) return -1;
		send();
		receive();
		droppedOut[activePlayer] = !validateMoves();
		int winner = winningConditions();
		activePlayer = (activePlayer + 1) % players.size();
		return winner;
	}
	
	public void send() {
		try {
			for(int i=0; i<grid.width; i++) {
				for(int j=0; j<grid.height; j++) {
					if(grid.pieces[i][j] == null) {
						continue;
					}
				
					players.get(activePlayer).send(grid.pieces[i][j].toString());
				}
			}
			players.get(activePlayer).send("ready");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receive() {
		for(int i=0; i<movesPerRound; i++) {
			try {
				String response = players.get(activePlayer).read();
				GridCoords coord1 = new GridCoords(), coord2 = new GridCoords();
				String[] coords = response.split(" ");
				coord1.x = Integer.parseInt(coords[0]);
				coord1.y = Integer.parseInt(coords[1]);
				coord2.x = Integer.parseInt(coords[2]);
				coord2.y = Integer.parseInt(coords[3].trim());
				moves.add(new Move(coord1, coord2));
			}
			catch(IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public boolean validateMoves() {
		for(Move move : moves) {
			if(!grid.getPiece(move.coord1).canMove(move.coord2)) {
				moves = new ArrayList<Move>();
				return false;
			}
			grid.pieces[move.coord2.x][move.coord2.y] = grid.getPiece(move.coord1);
			grid.pieces[move.coord1.x][move.coord1.y] = null;
			grid.getPiece(move.coord2).coords = move.coord2;
		}
		moves = new ArrayList<Move>();
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
