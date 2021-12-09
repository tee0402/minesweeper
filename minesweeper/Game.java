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
 *	FILENAMES: 		Game.java
 *
 *	DESCRIPTION: 	This file sets the difficulties and starts the game
 *
 **********************************************************************************************/

public class Game {
	
	public static String difficulty;
	private static GameFrame gameFrame;
	private static InfoWindow infoWindow;
	
	/*************************************************
	 * 
	 * 	Method:			Game
	 * 
	 * 	Description: 	Constructor. Sets the difficulty and starts the 
	 * 					game
	 * 
	 * 	param: 			String newDifficulty	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public Game(String newDifficulty) {
		difficulty = newDifficulty;
		switch (difficulty) {
			case "easy":
				startGame(10, 10, 10);
				break;
			case "medium":
				startGame(15, 15, 40);
				break;
			case "hard":
				startGame(22, 22, 99);
				break;
		}
	}
	
	/*************************************************
	 * 
	 * 	Method:			startGame
	 * 
	 * 	Description: 	starts the game, starts the timer.
	 * 
	 * 	param: 			int rows, int cols, int mines	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	private void startGame(int rows, int cols, int mines)
	{
		Highscores.instantiate();
		gameFrame = new GameFrame(rows, cols, mines);
		infoWindow = new InfoWindow();
		gameFrame.setVisible(true);
	}
	
	/*************************************************
	 * 
	 * 	Method:			closeGame
	 * 
	 * 	Description: 	closes the game
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public static void closeGame() {
		gameFrame.dispose();
		infoWindow.dispose();
	}
}
