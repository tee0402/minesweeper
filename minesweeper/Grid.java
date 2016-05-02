import java.util.*;

public class Grid {
	private Cell[][] grid;
	private int rows;
	private int columns;
	private int mines;
	
	public Grid(int numRows, int numColumns, int numMines) {
		rows = numRows;
		columns = numColumns;
		mines = numMines;
		grid = new Cell[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
				grid[i][j].setData(0);
			}
		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 && rows > 2) {
					grid[i][j].setSouth(grid[i + 1][j]);
				}
				else if (i == rows - 1) {
					grid[i][j].setNorth(grid[i - 1][j]);
				}
				else {
					grid[i][j].setSouth(grid[i + 1][j]);
					grid[i][j].setNorth(grid[i - 1][j]);
				}
				if (j == 0 && columns > 2) {
					grid[i][j].setEast(grid[i][j + 1]);
				}
				else if (j == columns - 1) {
					grid[i][j].setWest(grid[i][j - 1]);
				}
				else {
					grid[i][j].setEast(grid[i][j + 1]);
					grid[i][j].setWest(grid[i][j - 1]);
				}
				if (i != 0 && j != columns - 1) {
					grid[i][j].setNortheast(grid[i - 1][j + 1]);
				}
				if (i != rows - 1 && j != columns - 1) {
					grid[i][j].setSoutheast(grid[i + 1][j + 1]);
				}
				if (i != rows - 1 && j != 0) {
					grid[i][j].setSouthwest(grid[i + 1][j - 1]);
				}
				if (i != 0 && j != 0) {
					grid[i][j].setNorthwest(grid[i - 1][j - 1]);
				}
			}
		}
		
		Random random = new Random();
		int minesPlaced = 0;
		while (minesPlaced < mines) {
			int mineX = random.nextInt(rows);
			int mineY = random.nextInt(columns);
			if (getData(mineX, mineY) > -1) {
				setData(mineX, mineY, -1);
				minesPlaced++;
				if (getCell(mineX, mineY).getNorth() != null && getCell(mineX, mineY).getNorth().getData() > -1) {
					getCell(mineX, mineY).getNorth().setData(getCell(mineX, mineY).getNorth().getData() + 1);;
				}
				if (getCell(mineX, mineY).getNortheast() != null && getCell(mineX, mineY).getNortheast().getData() > -1) {
					getCell(mineX, mineY).getNortheast().setData(getCell(mineX, mineY).getNortheast().getData() + 1);;
				}
				if (getCell(mineX, mineY).getEast() != null && getCell(mineX, mineY).getEast().getData() > -1) {
					getCell(mineX, mineY).getEast().setData(getCell(mineX, mineY).getEast().getData() + 1);;
				}
				if (getCell(mineX, mineY).getSoutheast() != null && getCell(mineX, mineY).getSoutheast().getData() > -1) {
					getCell(mineX, mineY).getSoutheast().setData(getCell(mineX, mineY).getSoutheast().getData() + 1);;
				}
				if (getCell(mineX, mineY).getSouth() != null && getCell(mineX, mineY).getSouth().getData() > -1) {
					getCell(mineX, mineY).getSouth().setData(getCell(mineX, mineY).getSouth().getData() + 1);;
				}
				if (getCell(mineX, mineY).getSouthwest() != null && getCell(mineX, mineY).getSouthwest().getData() > -1) {
					getCell(mineX, mineY).getSouthwest().setData(getCell(mineX, mineY).getSouthwest().getData() + 1);;
				}
				if (getCell(mineX, mineY).getWest() != null && getCell(mineX, mineY).getWest().getData() > -1) {
					getCell(mineX, mineY).getWest().setData(getCell(mineX, mineY).getWest().getData() + 1);;
				}
				if (getCell(mineX, mineY).getNorthwest() != null && getCell(mineX, mineY).getNorthwest().getData() > -1) {
					getCell(mineX, mineY).getNorthwest().setData(getCell(mineX, mineY).getNorthwest().getData() + 1);;
				}
			}
		}
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getData(int row, int column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return grid[row][column].getData();
		}
		return -2;
	}
	
	public void setData(int row, int column, int data) {
		grid[row][column].setData(data);
	}
	
	public Cell getCell(int row, int column) {
		return grid[row][column];
	}
	
	public void setRevealed(int row, int column, boolean bool) {
		grid[row][column].setRevealed(bool);
	}
	
	public boolean getRevealed(int row, int column) {
		return grid[row][column].getRevealed();
	}
	
	public void reveal(Cell cell) {
		if (cell.getNorth() != null) {
			if (cell.getNorth().getData() == 0) {
				cell.getNorth().setRevealed(true);
				reveal(cell.getNorth());
			}
			else if (cell.getNorth().getData() > 0) {
				cell.getNorth().setRevealed(true);
			}
		}
		if (cell.getNortheast() != null) {
			if (cell.getNortheast().getData() == 0) {
				cell.getNortheast().setRevealed(true);
				reveal(cell.getNortheast());
			}
			else if (cell.getNortheast().getData() > 0) {
				cell.getNortheast().setRevealed(true);
			}
		}
		if (cell.getEast() != null) {
			if (cell.getEast().getData() == 0) {
				cell.getEast().setRevealed(true);
				reveal(cell.getEast());
			}
			else if (cell.getEast().getData() > 0) {
				cell.getEast().setRevealed(true);
			}
		}
		if (cell.getSoutheast() != null) {
			if (cell.getSoutheast().getData() == 0) {
				cell.getSoutheast().setRevealed(true);
				reveal(cell.getSoutheast());
			}
			else if (cell.getSoutheast().getData() > 0) {
				cell.getSoutheast().setRevealed(true);
			}
		}
		if (cell.getSouth() != null) {
			if (cell.getSouth().getData() == 0) {
				cell.getSouth().setRevealed(true);
				reveal(cell.getSouth());
			}
			else if (cell.getSouth().getData() > 0) {
				cell.getSouth().setRevealed(true);
			}
		}
		if (cell.getSoutheast() != null) {
			if (cell.getSoutheast().getData() == 0) {
				cell.getSoutheast().setRevealed(true);
				reveal(cell.getSoutheast());
			}
			else if (cell.getSoutheast().getData() > 0) {
				cell.getSoutheast().setRevealed(true);
			}
		}
		if (cell.getWest() != null) {
			if (cell.getWest().getData() == 0) {
				cell.getWest().setRevealed(true);
				reveal(cell.getWest());
			}
			else if (cell.getWest().getData() > 0) {
				cell.getWest().setRevealed(true);
			}
		}
		if (cell.getNorthwest() != null) {
			if (cell.getNorthwest().getData() == 0) {
				cell.getNorthwest().setRevealed(true);
				reveal(cell.getNorthwest());
			}
			else if (cell.getNorthwest().getData() > 0) {
				cell.getNorthwest().setRevealed(true);
			}
		}
	}
}
