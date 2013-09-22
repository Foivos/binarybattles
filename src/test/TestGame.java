package test;

import game.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import referee.GridReferee;
import rules.GridCoords;
import rules.GridPiece;
import rules.Player;
import rules.Grid;

public class TestGame {
	public static Grid GRID;
	
	public static void main(String[] args) throws IOException {
		
		List<Player> players = new ArrayList<Player>();
		Player player = new Player("java -classpath ./bin test.TestPlayer");
		players.add(player);
		players.add(player);
		
		final GridReferee referee = new GridReferee(9, 9, players) {
			
			@Override
			public int winningConditions() {
				int x = 1;
				int y = x;
				for(int i=0; i<grid.width; i++) {
					if(grid.pieces[0][i] != null && grid.pieces[i][0].player == 1) {
						return 1;
					}
					if(grid.pieces[grid.height-1][i] != null && grid.pieces[grid.height-1][i].player == 0) {
						return 0;
					}
					
				}
				for(int i=0; i<players.size(); i++) {
					if(droppedOut[i]) {
						return 1 ^ i;
					}
				}
				return -1;
			}
		};
		referee.grid.pieces[0][4] = new GridPiece(new GridCoords(0,4), 0) {
			public Grid grid = referee.grid;
			
			@Override
			public boolean canMove(GridCoords coords2) {
				if(grid.getPiece(coords2) != null) return false;
				if(coords.x == coords2.x + 1 && coords.y == coords2.y) return true;
				if(coords.x == coords2.x - 1 && coords.y == coords2.y) return true;
				if(coords.x == coords2.x && coords.y == coords2.y + 1) return true;
				if(coords.x == coords2.x && coords.y == coords2.y - 1) return true;
				return false;
			}
		};
		
		referee.grid.pieces[8][4] = new GridPiece(new GridCoords(8,4), 1) {
			public Grid grid = referee.grid;
			
			@Override
			public boolean canMove(GridCoords coords2) {
				if(grid.getPiece(coords2) != null) return false;
				if(coords.x == coords2.x + 1 && coords.y == coords2.y) return true;
				if(coords.x == coords2.x - 1 && coords.y == coords2.y) return true;
				if(coords.x == coords2.x && coords.y == coords2.y + 1) return true;
				if(coords.x == coords2.x && coords.y == coords2.y - 1) return true;
				return false;
			}
		};
		
		
		Game game = new Game(referee, players);
		System.out.println(game.play());
		
	}
}
