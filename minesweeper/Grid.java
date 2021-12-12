import java.util.*;

class Grid {
	private final Cell[][] grid;
	private final int rows;
  private final int columns;

  Grid(int numRows, int numColumns, int numMines) {
		rows = numRows;
		columns = numColumns;
    grid = new Cell[rows][columns];

    // Set up grid of cells
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
				grid[i][j].setData(0);
			}
		}

    // Place mines randomly
		Random random = new Random();
		int minesPlaced = 0;
		while (minesPlaced < numMines) {
			int mineX = random.nextInt(rows);
			int mineY = random.nextInt(columns);
      // If the cell at the new random coordinates is not already a mine, place a mine there and increment adjacent cells
			if (grid[mineX][mineY].getData() != -1) {
        grid[mineX][mineY].setData(-1);
				minesPlaced++;
        incrementCell(getCell(mineX - 1, mineY));
        incrementCell(getCell(mineX - 1, mineY + 1));
        incrementCell(getCell(mineX, mineY + 1));
        incrementCell(getCell(mineX + 1, mineY + 1));
        incrementCell(getCell(mineX + 1, mineY));
        incrementCell(getCell(mineX + 1, mineY - 1));
        incrementCell(getCell(mineX, mineY - 1));
        incrementCell(getCell(mineX - 1, mineY - 1));
			}
		}
	}
	
	private Cell getCell(int row, int column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return grid[row][column];
		}
		return null;
	}

  private void incrementCell(Cell cell) {
    if (cell != null && cell.getData() != -1) {
      cell.setData(cell.getData() + 1);
    }
  }

  int getData(int row, int column) {
    if (row >= 0 && row < rows && column >= 0 && column < columns) {
      return grid[row][column].getData();
    }
    return -2;
  }

  void revealCellAndSurrounding(int row, int column) {
    Cell cell = getCell(row, column);
    if (cell != null && !cell.getRevealed()) {
      cell.setRevealed();
      GridPanel.revealInGUI(row, column);
      if (cell.getData() == 0) {
        revealSurrounding(row, column);
      }
    }
  }

  private void revealSurrounding(int row, int column) {
    revealCellAndSurrounding(row - 1, column);
    revealCellAndSurrounding(row - 1, column + 1);
    revealCellAndSurrounding(row, column + 1);
    revealCellAndSurrounding(row + 1, column + 1);
    revealCellAndSurrounding(row + 1, column);
    revealCellAndSurrounding(row + 1, column - 1);
    revealCellAndSurrounding(row, column - 1);
    revealCellAndSurrounding(row - 1, column - 1);
  }
}
