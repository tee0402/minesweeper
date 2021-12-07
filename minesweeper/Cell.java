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
 *	FILENAMES: 		Cell.java
 *
 *	DESCRIPTION: 	Contains all the methods for the cell, much like a node
 *
 **********************************************************************************************/
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
	private boolean hidden = true;
	
	/*************************************************
	 * 
	 * 	Method:			getNorth
	 * 
	 * 	Description: 	returns the cell directly north of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell north of the current cell
	 * 
	 *************************************************/
	
	public Cell getNorth() {
		return north;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setNorth
	 * 
	 * 	Description: 	sets the north value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setNorth(Cell cell) {
		north = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getNortheast
	 * 
	 * 	Description: 	returns the cell directly northeast of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell northeast of the current cell
	 * 
	 *************************************************/
	
	public Cell getNortheast() {
		return northeast;
	}
	
	
	/*************************************************
	 * 
	 * 	Method:			setNortheast
	 * 
	 * 	Description: 	sets the northeast value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setNortheast(Cell cell) {
		northeast = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getEast
	 * 
	 * 	Description: 	returns the cell directly east of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell east of the current cell
	 * 
	 *************************************************/
	
	public Cell getEast() {
		return east;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setEast
	 * 
	 * 	Description: 	sets the east value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setEast(Cell cell) {
		east = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getSoutheast
	 * 
	 * 	Description: 	returns the cell directly southeast of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell southeast of the current cell
	 * 
	 *************************************************/
	
	public Cell getSoutheast() {
		return southeast;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setSoutheast
	 * 
	 * 	Description: 	sets the southeast value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setSoutheast(Cell cell) {
		southeast = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getSouth
	 * 
	 * 	Description: 	returns the cell directly south of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell south of the current cell
	 * 
	 *************************************************/
	
	public Cell getSouth() {
		return south;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setSouth
	 * 
	 * 	Description: 	sets the south value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setSouth(Cell cell) {
		south = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getSouthwest
	 * 
	 * 	Description: 	returns the cell directly southwest of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell southwest of the current cell
	 * 
	 *************************************************/
	
	public Cell getSouthwest() {
		return southwest;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setSouthwest
	 * 
	 * 	Description: 	sets the southwest value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setSouthwest(Cell cell) {
		southwest = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getWest
	 * 
	 * 	Description: 	returns the cell directly west of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell west of the current cell
	 * 
	 *************************************************/
	
	public Cell getWest() {
		return west;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setWest
	 * 
	 * 	Description: 	sets the west value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setWest(Cell cell) {
		west = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getNorthwest
	 * 
	 * 	Description: 	returns the cell directly northwest of the given cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the cell northwest of the current cell
	 * 
	 *************************************************/
	
	public Cell getNorthwest() {
		return northwest;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setNorthwest
	 * 
	 * 	Description: 	sets the northwest value of the current cell to a given cell
	 * 
	 * 	param: 			Cell cell	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setNorthwest(Cell cell) {
		northwest = cell;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getData
	 * 
	 * 	Description: 	returns the data in the current cell
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		the data contained in the current cell
	 * 
	 *************************************************/
	
	public int getData() {
		return data;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setData
	 * 
	 * 	Description: 	sets the data of the current cell to the given data
	 * 
	 * 	param: 			int newData	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setData(int newData) {
		data = newData;
	}
	
	/*************************************************
	 * 
	 * 	Method:			getHidden
	 * 
	 * 	Description: 	returns true if data is hidden, false otherwise
	 * 
	 * 	param: 			none
	 * 
	 * 	return: 		true or false
	 * 
	 *************************************************/
	
	public boolean getHidden() {
		return hidden;
	}
	
	/*************************************************
	 * 
	 * 	Method:			setHidden
	 * 
	 * 	Description: 	set current cell hidden to true or false, as specified by the
	 * 					developer
	 * 
	 * 	param: 			boolean bool
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public void setHidden(boolean bool) {
		hidden = bool;
	}
}
