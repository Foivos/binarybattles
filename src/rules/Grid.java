package rules;

public class Grid {
	public int width;
	public int height;
	public GridPiece[][] pieces;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.pieces = new GridPiece[width][height];
	}
	
	public GridPiece getPiece(GridCoords coords) {
		return pieces[coords.x][coords.y];
	}
}
