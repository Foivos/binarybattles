package rules;

public class GridPiece {
	public GridCoords coords;
	public int player;
	
	public GridPiece(GridCoords coords, int player) {
		this.coords = coords;
		this.player = player;
	}
	
	public boolean canMove(GridCoords coords2) {
		return true;
	}
	
	@Override
	public String toString() {
		return ("" + coords.x + " " + coords.y + " " + player);
	}
}
