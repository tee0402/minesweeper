import java.util.Random;

class Grid {
	private final Cell[][] grid;
	private final int rows;
  private final int columns;

  Grid(int rows, int columns, int mines) {
		this.rows = rows;
		this.columns = columns;

    // Set up grid of empty cells
    grid = new Cell[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}

    // Place mines randomly
		Random random = new Random();
		int minesPlaced = 0;
		while (minesPlaced < mines) {
			int x = random.nextInt(rows);
			int y = random.nextInt(columns);
      // If the cell at the new random coordinates is not already a mine, place a mine there and increment adjacent cells
			if (grid[x][y].getData() != -1) {
        grid[x][y].setData(-1);
				minesPlaced++;
        incrementCell(getCell(x - 1, y));
        incrementCell(getCell(x - 1, y + 1));
        incrementCell(getCell(x, y + 1));
        incrementCell(getCell(x + 1, y + 1));
        incrementCell(getCell(x + 1, y));
        incrementCell(getCell(x + 1, y - 1));
        incrementCell(getCell(x, y - 1));
        incrementCell(getCell(x - 1, y - 1));
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
  }
}