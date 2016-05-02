
public class Cell {

	private Cell north;
	private Cell northeast;
	private Cell east;
	private Cell southeast;
	private Cell south;
	private Cell southwest;
	private Cell west;
	private Cell northwest;
	private int data;
	private boolean revealed;
	
	public Cell getNorth() {
		return north;
	}
	
	public void setNorth(Cell cell) {
		north = cell;
	}
	
	public Cell getNortheast() {
		return northeast;
	}
	
	public void setNortheast(Cell cell) {
		northeast = cell;
	}
	
	public Cell getEast() {
		return east;
	}
	
	public void setEast(Cell cell) {
		east = cell;
	}
	
	public Cell getSoutheast() {
		return southeast;
	}
	
	public void setSoutheast(Cell cell) {
		southeast = cell;
	}
	
	public Cell getSouth() {
		return south;
	}
	
	public void setSouth(Cell cell) {
		south = cell;
	}
	
	public Cell getSouthwest() {
		return southwest;
	}
	
	public void setSouthwest(Cell cell) {
		southwest = cell;
	}
	
	public Cell getWest() {
		return west;
	}
	
	public void setWest(Cell cell) {
		west = cell;
	}
	
	public Cell getNorthwest() {
		return northwest;
	}
	
	public void setNorthwest(Cell cell) {
		northwest = cell;
	}
	
	public int getData() {
		return data;
	}
	
	public void setData(int newData) {
		data = newData;
	}
	
	public boolean getRevealed() {
		return revealed;
	}
	
	public void setRevealed(boolean bool) {
		revealed = bool;
	}
}
