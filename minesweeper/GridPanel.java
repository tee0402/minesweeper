import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.imageio.*;

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
 *	FILENAMES: 		GridPanel.java
 *
 *	DESCRIPTION: 	This file contains all the methods that are required to play the actual game.
 *					It contains the action listeners for the right and left click.
 *
 **********************************************************************************************/

public class GridPanel extends JPanel
{
  public static int flags;
	public static int cellsLeft;
	public static JPanel[][] guiGrid;
	int row, col, mine;
	BufferedImage flag;
	ImageIcon flagImage;
	ImageIcon tileImage = new ImageIcon(Objects.requireNonNull(GridPanel.class.getResource("resources/tile.jpg")));
	
	/*************************************************
	 * 
	 * 	Method:			GridPanel
	 * 
	 * 	Description: 	Constructor. Contains the logic to play the game
	 * 					and right and left click action listeners.
	 * 
	 * 	param: 			int rows, int cols, int mines	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public GridPanel(int rows, int cols, int mines)
	{	
		row = rows;
		col = cols;
		mine = mines;
		flags = mines;
    Grid grid = new Grid(rows, cols, mines);
		guiGrid = new JPanel[rows][cols];
    cellsLeft = grid.getRows() * grid.getColumns();
    try {
      flag = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/flag.gif")));
      Image newimg = flag.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      flagImage = new ImageIcon(newimg);
    } catch (IOException e) {
      System.out.println("Error reading flag.gif.");
    }
		setLayout(new GridLayout(rows, cols));
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				guiGrid[i][j] = new JPanel();
				guiGrid[i][j].setLayout(new BorderLayout());
				add(guiGrid[i][j]);
				JButton button = new JButton();
				button.setIcon(tileImage);
				guiGrid[i][j].add(button);
        // Add left click handler
				button.addActionListener(new ButtonHandler(i, j, grid));
        // Add right click handler
				button.addMouseListener(new MouseAdapter() {
					/*************************************************
					 * 
					 * 	Method:			mouseClicked
					 * 
					 * 	Description: 	if right click is pressed on a button, add a flag 
					 * 					and if a flag already exists, delete the flag
					 * 
					 * 	param: 			MouseEvent e	
					 * 
					 * 	return: 		none
					 * 
					 *************************************************/
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							if (button.getIcon().equals(tileImage) && flags > 0) {
								button.setIcon(flagImage);
								flags--;
							}
							else if (button.getIcon().equals(flagImage)) {
								button.setIcon(tileImage);
								flags++;
							}
							InfoWindow.flagsLeftTextField.setText("Flags left: " + GridPanel.flags);
						}
					}
				});
			}
		}
	}
	
	static class ButtonHandler implements ActionListener {
    private final int row, col;
    private final Grid grid;

    /*************************************************
   *
   * 	Method:			ButtonHandler
   *
   * 	Description: 	Constructor. Initializes variables.
   *
   * 	param: 			int x, int y, Grid g
   *
   * 	return: 		none
   *
   *************************************************/

    public ButtonHandler(int x, int y, Grid g) {
      row = x;
      col = y;
      grid = g;
    }

    /*************************************************
   *
   * 	Method:			actionPerformed
   *
   * 	Description: 	if button was clicked, end game
   * 					if it was a mine and reveal square(s)
   * 					if it was a number
   *
   * 	param: 			ActionEvent event
   *
   * 	return: 		none
   *
   *************************************************/

    public void actionPerformed(ActionEvent event) {
      // Clicked mine
      if (grid.getData(row, col) == -1) {
        // Show all mines
        for (int i = 0; i < grid.getRows(); i++) {
          for (int j = 0; j < grid.getColumns(); j++) {
            if (grid.getData(i, j) == -1) {
              JButton button = (JButton) guiGrid[i][j].getComponent(0);
              button.setIcon(new ImageIcon(Objects.requireNonNull(GridPanel.class.getResource("resources/mine.gif"))));
            }
          }
        }
        // Show game over prompt
        int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          new Game(Game.difficulty);
        }
        else if (selection == JOptionPane.NO_OPTION) {
          System.exit(0);
        }
      }
      // Clicked empty cell
      else if (grid.getData(row, col) == 0) {
        grid.setHidden(row, col, false);
        guiGrid[row][col].removeAll();
        guiGrid[row][col].revalidate();
        guiGrid[row][col].repaint();
        cellsLeft--;
        grid.reveal(grid.getCell(row, col), row, col);
      }
      // Clicked number
      else {
        grid.setHidden(row, col, false);
        guiGrid[row][col].removeAll();
        guiGrid[row][col].add(new JLabel(String.valueOf(grid.getData(row, col)), SwingConstants.CENTER));
        guiGrid[row][col].revalidate();
        guiGrid[row][col].repaint();
        cellsLeft--;
      }

      if (cellsLeft <= grid.getMines()) {
        int newHighscoreIndex = Highscores.checkHighscore(Game.difficulty, Time.timeElapsed());
        JFrame highscoreFrame = Highscores.highscoresWindow(Game.difficulty, newHighscoreIndex);
        int selection = JOptionPane.showConfirmDialog(null, "You win! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          highscoreFrame.dispose();
          new Game(Game.difficulty);
        }
        else if (selection == JOptionPane.NO_OPTION) {
          Game.closeGame();
        }
      }
    }
	}
}
