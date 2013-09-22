package chess;

import rules.GridCoords;
import rules.GridPiece;
import rules.Grid;

public class ChessPieces {
	Grid grid;
	
	public class Rook extends GridPiece{

		public Rook(GridCoords coords, int player) {
			super(coords, player);
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			if(coords.x != coords2.x && coords.y != coords2.y) return false;
			if(coords.x == coords2.x) {
				int dir = coords2.y > coords.y ? 1 : -1;
				for(int i = coords.y + dir; coords.y * dir < coords2.y * dir; coords.y+=dir) {
					if(grid.pieces[coords.x][i] != null) return false;
				}
			}
			else {
				int dir = coords2.x > coords.x ? 1 : -1;
				for(int i = coords.x + dir; coords.x * dir < coords2.x * dir; coords.x+=dir) {
					if(grid.pieces[i][coords.y] != null) return false;
				}
			}
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
		
	}
	
	
	public class Knight extends GridPiece{
		
		public Knight(GridCoords coords, int player) {
			super(coords, player);
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			
			int dx = coords.x - coords2.x;
			int dy = coords.y - coords2.y;
			if((dx*dx != 1 && dy*dy != 4) || (dx*dx!= 4 && dy*dy != 1)) return false;
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
		
	}
	
	public class Bishop extends GridPiece{

		public Bishop(GridCoords coords, int player) {
			super(coords, player);
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			if((coords.x + coords.y != coords2.x + coords2.y) || (coords.x - coords.y != coords2.x - coords2.y))
				return false;
			int dirx = coords2.x > coords.x ? 1 : -1;
			int diry = coords2.y > coords.y ? 1 : -1;
			int y = coords.y;
			for(int x = coords.x; x * dirx < coords2.x * dirx; x += dirx) {
				y += diry;
				if(grid.pieces[x][y] != null) return false;
			}
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
	}
	
	public class Queen extends GridPiece{

		public Queen(GridCoords coords, int player) {
			super(coords, player);
		}
		
		public boolean canMoveDiag(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			if((coords.x + coords.y != coords2.x + coords2.y) || (coords.x - coords.y != coords2.x - coords2.y))
				return false;
			int dirx = coords2.x > coords.x ? 1 : -1;
			int diry = coords2.y > coords.y ? 1 : -1;
			int y = coords.y;
			for(int x = coords.x; x * dirx < coords2.x * dirx; x += dirx) {
				y += diry;
				if(grid.pieces[x][y] != null) return false;
			}
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
		
		public boolean canMoveStraight(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			if(coords.x != coords2.x && coords.y != coords2.y) return false;
			if(coords.x == coords2.x) {
				int dir = coords2.y > coords.y ? 1 : -1;
				for(int i = coords.y + dir; coords.y * dir < coords2.y * dir; coords.y+=dir) {
					if(grid.pieces[coords.x][i] != null) return false;
				}
			}
			else {
				int dir = coords2.x > coords.x ? 1 : -1;
				for(int i = coords.x + dir; coords.x * dir < coords2.x * dir; coords.x+=dir) {
					if(grid.pieces[i][coords.y] != null) return false;
				}
			}
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			return canMoveStraight(coords2) || canMoveDiag(coords2);
		}
	}
	
	public class King extends GridPiece {

		public King(GridCoords coords, int player) {
			super(coords, player);
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			int dx = coords.x - coords2.x;
			int dy = coords.y - coords2.y;
			if(dx*dx > 1 || dy*dy > 1) return false;
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			return true;
		}
		
	}
	
	public class Pawn extends GridPiece {

		public Pawn(GridCoords coords, int player) {
			super(coords, player);
		}
		
		@Override
		public boolean canMove(GridCoords coords2) {
			if(coords2.x < 0 || coords2.x > 7 || coords2.y < 0 || coords2.y > 7) return false;
			if(coords.x == coords2.x && coords.y == coords2.y) return false;
			if(grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player == this.player)
				return false;
			if(player == 0 && (coords2.y - coords.y <= 0 || coords2.y - coords.y >= 3)) return false; 
			if(player == 2 && (coords.y - coords2.y <= 0 || coords.y - coords2.y >= 3)) return false; 
			int dx = coords2.x - coords.x;
			int dy = coords2.y - coords.y;
			if(dx*dx == 0 && dy*dy == 1) {
				return grid.pieces[coords2.x][coords2.y] == null;
			}
			if(dx*dx == 0 && dy*dy == 4) {
				if(player == 0 && coords.y != 1) return false;
				if(player == 1 && coords.y != 6) return false;
				return (grid.pieces[coords2.x][coords2.y] == null) && (grid.pieces[coords2.x][(coords2.y + coords.y)/2] == null);
			}
			if(dx*dx == 1 && dy*dy == 1) {
				return (grid.pieces[coords2.x][coords2.y] != null && grid.pieces[coords2.x][coords2.y].player != player);
			}
			return false;
		}
	}
}
