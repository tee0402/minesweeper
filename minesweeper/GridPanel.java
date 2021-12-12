import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.imageio.*;

class GridPanel extends JPanel {
  static int flags;
	static int cellsLeft;
  private static Grid grid;
	private static JPanel[][] gridPanel;
	int rows, columns, mines;
  ImageIcon flagImage;
	ImageIcon tileImage = new ImageIcon(Objects.requireNonNull(GridPanel.class.getResource("resources/tile.jpg")));

	GridPanel(int numRows, int numColumns, int numMines) {
		rows = numRows;
    columns = numColumns;
		mines = numMines;
		flags = mines;
    grid = new Grid(rows, columns, mines);
    gridPanel = new JPanel[rows][columns];
    cellsLeft = rows * columns;

    try {
      BufferedImage flag = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/flag.gif")));
      flagImage = new ImageIcon(flag.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    } catch (IOException e) {
      System.out.println("Error reading flag.gif.");
    }

		setLayout(new GridLayout(rows, columns));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
        gridPanel[i][j] = new JPanel();
        gridPanel[i][j].setLayout(new BorderLayout());
				add(gridPanel[i][j]);
				JButton button = new JButton();
				button.setIcon(tileImage);
        gridPanel[i][j].add(button);
        // Add left click handler
				button.addActionListener(new ButtonHandler(i, j));
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
							} else if (button.getIcon().equals(flagImage)) {
								button.setIcon(tileImage);
								flags++;
							}
							InfoWindow.flagsLeftTextField.setText("Flags left: " + flags);
						}
					}
				});
			}
		}
	}

  static void revealInGUI(int row, int column) {
    gridPanel[row][column].removeAll();
    if (grid.getData(row, column) > 0) {
      gridPanel[row][column].add(new JLabel(String.valueOf(grid.getData(row, column)), SwingConstants.CENTER));
    }
    gridPanel[row][column].revalidate();
    gridPanel[row][column].repaint();
    cellsLeft--;
  }

  void showAllMines() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid.getData(i, j) == -1) {
          JButton button = (JButton) gridPanel[i][j].getComponent(0);
          button.setIcon(new ImageIcon(Objects.requireNonNull(GridPanel.class.getResource("resources/mine.gif"))));
        }
      }
    }
  }
	
	class ButtonHandler implements ActionListener {
    private final int row, column;

    ButtonHandler(int x, int y) {
      row = x;
      column = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // Clicked mine
      if (grid.getData(row, column) == -1) {
        showAllMines();
        // Show game over prompt
        InfoWindow.timer.stop();
        int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          new Game(Game.difficulty);
        } else if (selection == JOptionPane.NO_OPTION) {
          System.exit(0);
        }
      }
      // Clicked empty or number cell
      else {
        grid.revealCellAndSurrounding(row, column);
      }

      if (cellsLeft <= mines) {
        showAllMines();
        int newHighscoreIndex = Game.highscores.checkHighscore(Game.difficulty, InfoWindow.time.timeElapsed());
        InfoWindow.timer.stop();
        JFrame highscoreFrame = Game.highscores.highscoresWindow(Game.difficulty, newHighscoreIndex);
        int selection = JOptionPane.showConfirmDialog(null, "You win! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          highscoreFrame.dispose();
          new Game(Game.difficulty);
        } else if (selection == JOptionPane.NO_OPTION) {
          Game.closeGame();
        }
      }
    }
	}
}
