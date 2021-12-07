import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
 *	FILENAMES: 		GameFrame.java
 *
 *	DESCRIPTION: 	This file creates the frame of the game and its menus
 *
 **********************************************************************************************/

public class GameFrame extends JFrame
{
	
	/*************************************************
	 * 
	 * 	Method:			GameFrame
	 * 
	 * 	Description: 	Constructor. Creates the frame
	 * 					and the menus
	 * 
	 * 	param: 			int rows, int cols, int mines	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public GameFrame(int rows, int cols, int mines)
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setResizable(false);
		add(new GridPanel(rows, cols, mines));
		setSize(rows * 30, cols * 30);
		setLocation(screenWidth / 3, screenHeight / 4);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(new AbstractAction("New Game")
			{
				public void actionPerformed(ActionEvent event)
				{
					Game.closeGame();
					new Game(Game.difficulty);
				}
			});
		gameMenu.add(new AbstractAction("Highscores")
				{
					public void actionPerformed(ActionEvent event)
					{
						Highscores.highscoresWindow();
					}
				});
		JMenu submenu = new JMenu("Change Difficulty");
		gameMenu.add(submenu);
		submenu.add(new AbstractAction("Easy")
			{
				public void actionPerformed(ActionEvent e)
				{
					Game.closeGame();
					new Game("easy");
				}	
			});
		submenu.add(new AbstractAction("Medium")
			{
				public void actionPerformed(ActionEvent event)
				{
					Game.closeGame();
					new Game("medium");
				}
			});
		submenu.add(new AbstractAction("Hard")
			{
				public void actionPerformed(ActionEvent event)
				{
					Game.closeGame();
					new Game("hard");
				}
			});
		gameMenu.add(new AbstractAction("Exit")
			{
				public void actionPerformed(ActionEvent event)
				{
					Game.closeGame();
				}
			});
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new AbstractAction("Instructions")
			{
				public void actionPerformed(ActionEvent e)
				{
					String message = "The object of the game is to reveal all the squares in the grid without clicking on a mine.\r\n\r\n" +
					"Left click on a square to reveal its contents.\r\n" +
					"Right click on a square to flag it.\r\n\r\n" +
					"There are three pre-defined maps to play: easy, medium and hard.\r\n";
					JOptionPane.showMessageDialog(getParent(), message, "About Minesweeper", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		
	
	}
}
