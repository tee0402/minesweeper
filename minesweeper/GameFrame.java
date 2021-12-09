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
		add(new GridPanel(rows, cols, mines));

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screenSize.width / 3, screenSize.height / 4, rows * 30, cols * 30);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
		setTitle("Minesweeper");
		
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
						Highscores.highscoresWindow(Game.difficulty, -1);
					}
				});
		JMenu changeDifficulty = new JMenu("Change Difficulty");
    changeDifficulty.add(new AbstractAction("Easy")
    {
      public void actionPerformed(ActionEvent e)
      {
        Game.closeGame();
        new Game("easy");
      }
    });
    changeDifficulty.add(new AbstractAction("Medium")
    {
      public void actionPerformed(ActionEvent event)
      {
        Game.closeGame();
        new Game("medium");
      }
    });
    changeDifficulty.add(new AbstractAction("Hard")
    {
      public void actionPerformed(ActionEvent event)
      {
        Game.closeGame();
        new Game("hard");
      }
    });
		gameMenu.add(changeDifficulty);
		gameMenu.add(new AbstractAction("Exit")
			{
				public void actionPerformed(ActionEvent event)
				{
					System.exit(0);
				}
			});
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new AbstractAction("Instructions")
			{
				public void actionPerformed(ActionEvent e)
				{
					String message = "The objective of the game is to reveal all the squares in the grid without clicking on a mine.\r\n\r\n";
          message += "Left click on a square to reveal its contents.\r\n";
					message += "Right click on a square to flag it.\r\n\r\n";
					message += "There are three pre-defined maps to play: easy, medium, and hard.\r\n";
					JOptionPane.showMessageDialog(getParent(), message, "About Minesweeper", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

    setVisible(true);
	}
}
