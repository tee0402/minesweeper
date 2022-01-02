import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.imageio.*;

class GridPanel extends JPanel {
  private final int rows, columns, mines;
  private int flagsLeft;
	private static int cellsLeft;
  private static Grid grid;
	private static JPanel[][] gridPanel;
  private ImageIcon flagImage;
  private ImageIcon mineImage;

	GridPanel(int rows, int columns, int mines) {
		this.rows = rows;
    this.columns = columns;
		this.mines = flagsLeft = mines;
    grid = new Grid(rows, columns, mines);
    gridPanel = new JPanel[rows][columns];
    cellsLeft = rows * columns;

    try {
      BufferedImage flag = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/flag.gif")));
      flagImage = new ImageIcon(flag.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
      BufferedImage mine = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/mine.gif")));
      mineImage = new ImageIcon(mine.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
    } catch (IOException e) {
      System.out.println("Error scaling images");
    }

		setLayout(new GridLayout(rows, columns));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
        gridPanel[i][j] = new JPanel();
        gridPanel[i][j].setLayout(new BorderLayout());
				add(gridPanel[i][j]);
				JButton button = new JButton();
        button.setPreferredSize(new Dimension(30, 25));
				button.setBackground(Color.white);
        gridPanel[i][j].add(button);
        // Add left click handler
				button.addActionListener(new ButtonHandler(i, j));
        // Add right click handler
				button.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							if (button.getIcon() == null && flagsLeft > 0) {
								button.setIcon(flagImage);
                flagsLeft--;
							} else if (button.getIcon() != null) {
                button.setIcon(null);
                flagsLeft++;
							}
							Game.gameFrame.infoPanel.setFlagsLeftTextField(flagsLeft);
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

  private void showAllMines() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid.getData(i, j) == -1) {
          JButton button = (JButton) gridPanel[i][j].getComponent(0);
          button.setIcon(mineImage);
        }
      }
    }
  }
	
	private class ButtonHandler implements ActionListener {
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
        Game.gameFrame.infoPanel.stopTimeUpdates();
        int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          Game.startGame(Game.difficulty);
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
        int newHighScoreIndex = Game.highScores.checkHighScore(Game.difficulty, Game.time.timeElapsed());
        Game.gameFrame.infoPanel.stopTimeUpdates();
        Game.highScores.highScoresWindow(Game.difficulty, newHighScoreIndex);
        int selection = JOptionPane.showConfirmDialog(null, "You win! Play again?");
        if (selection == JOptionPane.YES_OPTION) {
          Game.closeGame();
          Game.highScores.closeHighScoresWindow();
          Game.startGame(Game.difficulty);
        } else if (selection == JOptionPane.NO_OPTION) {
          Game.closeGame();
        }
      }
    }
	}
}