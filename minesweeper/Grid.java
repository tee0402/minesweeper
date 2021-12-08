import java.util.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/******************************************************************************************
 * 
 * 	NAME:			Iao Seng Sio and Sollie Garcia
 * 
 * 	HOMEWORK: 		MineSweeper Project
 * 
 * 	CLASS:			ICS 211
 * 
 * 	INSTRUCTOR:		Scott Robertson
 * 
 *	DATE: 			May 4, 2016
 * 
 *	FILENAMES: 		Grid.java
 *
 *	DESCRIPTION: 	This file contains all the methods that are required create the data for the 
 *					grid of the game. This includes the random placement of the bombs and the 
 *					revealing of the spaces when they are clicked.
 *
 **********************************************************************************************/

public class Grid {
	private final Cell[][] grid;
	private final int rows, columns, mines;
	
	/*************************************************
	 * 
	 * 	Method:			Grid
	 * 
	 * 	Description: 	Constructor. Creates the correct amount of cells for 
	 * 					the grid and also randomly places the mines in the
	 * 					cells. A bomb = -1
	 * 
	 * 	param: 			int rows, int cols, int mines	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public Grid(int numRows, int numColumns, int numMines) {
		rows = numRows;
		columns = numColumns;
		mines = numMines;
		grid = new Cell[rows][columns];

    // Set up grid of cells and directional relationships
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

    // Place mines randomly and increment adjacent cells
		Random random = new Random();
		int minesPlaced = 0;
		while (minesPlaced < mines) {
			int mineX = random.nextInt(rows);
			int mineY = random.nextInt(columns);
			if (getData(mineX, mineY) > -1) {
				setData(mineX, mineY, -1);
				minesPlaced++;
				if (getCell(mineX, mineY).getNorth() != null && getCell(mineX, mineY).getNorth().getData() > -1) {
					getCell(mineX, mineY).getNorth().setData(getCell(mineX, mineY).getNorth().getData() + 1);
				}
				if (getCell(mineX, mineY).getNortheast() != null && getCell(mineX, mineY).getNortheast().getData() > -1) {
					getCell(mineX, mineY).getNortheast().setData(getCell(mineX, mineY).getNortheast().getData() + 1);
				}
				if (getCell(mineX, mineY).getEast() != null && getCell(mineX, mineY).getEast().getData() > -1) {
					getCell(mineX, mineY).getEast().setData(getCell(mineX, mineY).getEast().getData() + 1);
				}
				if (getCell(mineX, mineY).getSoutheast() != null && getCell(mineX, mineY).getSoutheast().getData() > -1) {
					getCell(mineX, mineY).getSoutheast().setData(getCell(mineX, mineY).getSoutheast().getData() + 1);
				}
				if (getCell(mineX, mineY).getSouth() != null && getCell(mineX, mineY).getSouth().getData() > -1) {
					getCell(mineX, mineY).getSouth().setData(getCell(mineX, mineY).getSouth().getData() + 1);
				}
				if (getCell(mineX, mineY).getSouthwest() != null && getCell(mineX, mineY).getSouthwest().getData() > -1) {
					getCell(mineX, mineY).getSouthwest().setData(getCell(mineX, mineY).getSouthwest().getData() + 1);
				}
				if (getCell(mineX, mineY).getWest() != null && getCell(mineX, mineY).getWest().getData() > -1) {
					getCell(mineX, mineY).getWest().setData(getCell(mineX, mineY).getWest().getData() + 1);
				}
				if (getCell(mineX, mineY).getNorthwest() != null && getCell(mineX, mineY).getNorthwest().getData() > -1) {
					getCell(mineX, mineY).getNorthwest().setData(getCell(mineX, mineY).getNorthwest().getData() + 1);
				}
			}
		}
	}
	
	/*************************************************
	 * 
	 * 	Method:			getRows
	 * 
	 * 	Description: 	returns the amount of rows
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		rows
	 * 
	 *************************************************/
	
	public int getRows() {
		return rows;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getColumns
	 * 
	 * 	Description: 	returns the amount of columns
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		columns
	 * 
	 *************************************************/
	
	public int getColumns() {
		return columns;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getMines
	 * 
	 * 	Description: 	returns the amount of mines
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		mines
	 * 
	 *************************************************/
	
	public int getMines() {
		return mines;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getData
	 * 
	 * 	Description: 	returns the data the is in the requested cell. 
	 * 					If can not be reached, returns -2
	 * 
	 * 	param: 			int row, int column	
	 * 
	 * 	return: 		grid[row][column].getData(), -2
	 * 
	 *************************************************/
	
	public int getData(int row, int column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return grid[row][column].getData();
		}
		return -2;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setData
	 * 
	 * 	Description: 	sets the data of the requested row and col
	 * 					coordinate to a number specified as data
	 * 
	 * 	param: 			int row, int column, int data
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setData(int row, int column, int data) {
		grid[row][column].setData(data);
	}
	
	/*************************************************
	 * 
	 * 	Method:			getCell
	 * 
	 * 	Description: 	Returns the cell that is at the requested
	 * 					row and column coordinate. if not, return null
	 * 
	 * 	param: 			int row, int column
	 * 
	 * 	return: 		grid[row][column], null
	 * 
	 *************************************************/
	
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return grid[row][column];
		}
		return null;
	}

	/*************************************************
	 *
	 * 	Method:			setHidden
	 *
	 * 	Description: 	sets the requested row and col
	 * 					coordinate either true or false
	 *
	 * 	param: 			int row, int column, boolean bool
	 *
	 * 	return: 		none
	 *
	 *************************************************/

	public void setHidden(int row, int column, boolean bool) {
		grid[row][column].setHidden(bool);
	}

  /*************************************************
	 * 
	 * 	Method:			reveal
	 * 
	 * 	Description: 	reveals the surrounding cells of the requested cell.
	 * 
	 * 	param: 			Cell cell, int row, int column
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void reveal(Cell cell, int row, int column) {
		if (cell.getNorth() != null && cell.getNorth().getHidden()) {
			cell.getNorth().setHidden(false);
			if (cell.getNorth().getData() == 0) {
				reveal(cell.getNorth(), row - 1, column);
			}
			revealInGUI(row - 1, column);
		}
		if (cell.getNortheast() != null && cell.getNortheast().getHidden()) {
			cell.getNortheast().setHidden(false);
			if (cell.getNortheast().getData() == 0) {
				reveal(cell.getNortheast(), row - 1, column + 1);
			}
			revealInGUI(row - 1, column + 1);
		}
		if (cell.getEast() != null && cell.getEast().getHidden()) {
			cell.getEast().setHidden(false);
			if (cell.getEast().getData() == 0) {
				reveal(cell.getEast(), row, column + 1);
			}
			revealInGUI(row, column + 1);
		}
		if (cell.getSoutheast() != null && cell.getSoutheast().getHidden()) {
			cell.getSoutheast().setHidden(false);
			if (cell.getSoutheast().getData() == 0) {
				reveal(cell.getSoutheast(), row + 1, column + 1);
			}
			revealInGUI(row + 1, column + 1);
		}
		if (cell.getSouth() != null && cell.getSouth().getHidden()) {
			cell.getSouth().setHidden(false);
			if (cell.getSouth().getData() == 0) {
				reveal(cell.getSouth(), row + 1, column);
			}
			revealInGUI(row + 1, column);
		}
		if (cell.getSouthwest() != null && cell.getSouthwest().getHidden()) {
			cell.getSouthwest().setHidden(false);
			if (cell.getSouthwest().getData() == 0) {
				reveal(cell.getSouthwest(), row + 1, column - 1);
			}
			revealInGUI(row + 1, column - 1);
		}
		if (cell.getWest() != null && cell.getWest().getHidden()) {
			cell.getWest().setHidden(false);
			if (cell.getWest().getData() == 0) {
				reveal(cell.getWest(), row, column - 1);
			}
			revealInGUI(row, column - 1);
		}
		if (cell.getNorthwest() != null && cell.getNorthwest().getHidden()) {
			cell.getNorthwest().setHidden(false);
			if (cell.getNorthwest().getData() == 0) {
				reveal(cell.getNorthwest(), row - 1, column - 1);
			}
			revealInGUI(row - 1, column - 1);
		}
	}
	
	/*************************************************
	 * 
	 * 	Method:			revealInGUI
	 * 
	 * 	Description: 	reveals a cell in the GUI
	 * 
	 * 	param: 			int row, int column
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	private void revealInGUI(int row, int col) {
    	GridPanel.guiGrid[row][col].removeAll();
    	if (getData(row, col) > 0) {
    		GridPanel.guiGrid[row][col].add(new JLabel(String.valueOf(getData(row, col)), SwingConstants.CENTER));
    	}
    	GridPanel.guiGrid[row][col].revalidate();
    	GridPanel.guiGrid[row][col].repaint();
    	GridPanel.cellsLeft--;
	}
}
