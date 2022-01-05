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

	GridPanel(int rows, int columns, int mines, String difficulty) {
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

				JButton cellButton = new JButton();
        cellButton.setPreferredSize(new Dimension(30, 25));
        cellButton.setBackground(Color.white);
        // Add left click handler
        final int row = i;
        final int column = j;
        cellButton.addActionListener(e -> {
          // Clicked mine
          if (grid.getData(row, column) == -1) {
            Game.gameFrame.infoPanel.stopTimeUpdates();
            showAllMines();
            // Show game over prompt
            int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
            if (selection == JOptionPane.YES_OPTION) {
              Game.startGame(difficulty);
            } else if (selection == JOptionPane.NO_OPTION) {
              System.exit(0);
            }
          }
          // Clicked empty or number cell
          else {
            grid.revealCellAndSurrounding(row, column);
          }
          // When all cells have been revealed
          if (cellsLeft <= this.mines) {
            int score = Game.time.timeElapsed();
            Game.gameFrame.infoPanel.stopTimeUpdates();
            showAllMines();
            int newHighScoreIndex = Game.highScores.checkHighScore(difficulty, score);
            // Show game over prompt if no new high score or high scores window if new high score
            if (newHighScoreIndex == -1) {
              int selection = JOptionPane.showConfirmDialog(null, "You won with a score of " + score + "! Play again?");
              if (selection == JOptionPane.YES_OPTION) {
                Game.startGame(difficulty);
              } else if (selection == JOptionPane.NO_OPTION) {
                System.exit(0);
              }
            } else {
              Game.highScores.highScoresWindow(difficulty, newHighScoreIndex);
            }
          }
        });
        // Add right click handler
        cellButton.addMouseListener(new MouseAdapter() {
          @Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							if (cellButton.getIcon() == null && flagsLeft > 0) {
                cellButton.setIcon(flagImage);
                flagsLeft--;
							} else if (cellButton.getIcon() != null) {
                cellButton.setIcon(null);
                flagsLeft++;
							}
							Game.gameFrame.infoPanel.setFlagsLeftTextField(flagsLeft);
						}
					}
				});
        gridPanel[i][j].add(cellButton);
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
}