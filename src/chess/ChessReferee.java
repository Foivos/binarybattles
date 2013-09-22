package chess;

import java.util.List;

import referee.GridReferee;
import rules.GridCoords;
import rules.GridPiece;
import rules.Player;

public class ChessReferee extends GridReferee{

	public ChessReferee(List<Player> players) {
		super(8, 8, players);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int winningConditions() {
		int kx=0, ky=0;
		outer: for(int x=0; x<8; x++) {
			for(int y=0; y<8; y++) {
				try {
					if(Class.forName("chess.ChessPieces.King").isInstance(grid.pieces[x][y]) && grid.pieces[x][y].player != activePlayer) {
						kx = x;
						ky = y;
						break outer;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		boolean[][] threatened = new boolean[3][3];
		
		int c = 0;
		for(int i=-1; i<2; i++) {
			for(int j =-1; j<2; j++) {
				if(i==0 && j==0) continue;
				if(kx+i<0 || kx+i>7 || ky+j <0 || ky+j>7) {
					threatened[i+1][j+1] = true;
					continue;
				}
				chess:
				for(int x=0; x<8; x++) {
					for(int y=0; y<8; y++) {
						if(threatened[i+1][j+1]) break chess;
						GridPiece piece = grid.pieces[x][y];
						if(piece == null) continue;
						if(piece.player == activePlayer && piece.canMove(new GridCoords(kx+i, ky+j))) {
							threatened[i+1][j+1] = true;
						}
						if(piece.player != activePlayer && x == kx+i && y == ky+j) {
							threatened[i+1][j+1] = true;
						}
					}
				}
				if(threatened[i+1][j+1]) c++;
			}
			
		}
		if(c == 8) {
			GridCoords coords = null;
			for(int x=0; x<8; x++) {
				for(int y=0; y<8; y++) {
					GridPiece piece = grid.pieces[x][y];
					if(piece == null) continue;
					if(piece.player == activePlayer && piece.canMove(new GridCoords(kx, ky))) {
						if(coords == null) {
							coords = new GridCoords(x,y);
						}
						else {
							return activePlayer;
						}
					}
				}
			}
			if(coords == null) {
				
			}
			else {
				
			}
		}
		
		return -1;
	}

}
