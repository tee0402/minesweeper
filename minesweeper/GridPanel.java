import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

class GridPanel extends JPanel {
  private final int rows, columns, mines;
  private int cellsLeft, flagsLeft;
  private final Grid grid;
	private final JPanel[][] gridPanel;
  private ImageIcon flagImage, mineImage;

	GridPanel(int rows, int columns, int mines, String difficulty) {
		this.rows = rows;
    this.columns = columns;
    cellsLeft = rows * columns;
		this.mines = flagsLeft = mines;
    grid = new Grid(rows, columns, mines);
    gridPanel = new JPanel[rows][columns];

    try {
      BufferedImage flag = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/flag.gif")));
      flagImage = new ImageIcon(flag.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
      BufferedImage mine = ImageIO.read(Objects.requireNonNull(GridPanel.class.getResource("resources/mine.gif")));
      mineImage = new ImageIcon(mine.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
    } catch (IOException e) {
      System.out.println("Error scaling images");
    }

		setLayout(new GridLayout(rows, columns));
    InfoPanel infoPanel = GameFrame.getInfoPanel();
    HighScores highScores = Game.getHighScores();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
        gridPanel[i][j] = new JPanel();
        gridPanel[i][j].setLayout(new BorderLayout());
				add(gridPanel[i][j]);

				JButton cellButton = new JButton();
        cellButton.setPreferredSize(new Dimension(30, 25));
        cellButton.setBackground(Color.white);
        // Add left click listener
        final int row = i;
        final int column = j;
        cellButton.addActionListener(e -> {
          // If left-clicked mine, end game, otherwise reveal cell and surrounding cells recursively
          if (grid.containsMine(row, column)) {
            infoPanel.stopTimeUpdates();
            showAllMines();
            // Show game over prompt
            int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
            if (selection == JOptionPane.YES_OPTION) {
              Game.startGame(difficulty);
            } else if (selection == JOptionPane.NO_OPTION) {
              System.exit(0);
            }
          } else {
            grid.revealCellAndSurrounding(row, column);
          }

          // When all cells have been revealed
          if (cellsLeft <= this.mines) {
            int score = Game.getTime().timeElapsed();
            infoPanel.stopTimeUpdates();
            showAllMines();
            int newHighScoreIndex = highScores.addHighScore(difficulty, score);
            // Show game over prompt if no new high score or high scores window if new high score
            if (newHighScoreIndex == -1) {
              int selection = JOptionPane.showConfirmDialog(null, "You won with a score of " + score + "! Play again?");
              if (selection == JOptionPane.YES_OPTION) {
                Game.startGame(difficulty);
              } else if (selection == JOptionPane.NO_OPTION) {
                System.exit(0);
              }
            } else {
              highScores.highScoresWindow(difficulty, newHighScoreIndex);
            }
          }
        });
        // Add right click listener
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
              infoPanel.setFlagsLeftTextField(flagsLeft);
						}
					}
				});
        gridPanel[i][j].add(cellButton);
			}
		}
	}

  private void revealInGUI(int row, int column) {
    gridPanel[row][column].removeAll();
    int cellData = grid.getData(row, column);
    if (cellData > 0) {
      gridPanel[row][column].add(new JLabel(String.valueOf(cellData), SwingConstants.CENTER));
    }
    gridPanel[row][column].revalidate();
    gridPanel[row][column].repaint();
    cellsLeft--;
  }

  private void showAllMines() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid.containsMine(i, j)) {
          ((JButton) gridPanel[i][j].getComponent(0)).setIcon(mineImage);
        }
      }
    }
  }

  private class Grid {
    private final Cell[][] grid;
    private final int rows, columns;

    private Grid(int rows, int columns, int mines) {
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

    private void incrementCell(Cell cell) {
      if (cell != null && cell.getData() != -1) {
        cell.setData(cell.getData() + 1);
      }
    }

    private Cell getCell(int row, int column) {
      if (row >= 0 && row < rows && column >= 0 && column < columns) {
        return grid[row][column];
      }
      return null;
    }

    private int getData(int row, int column) {
      Cell cell = getCell(row, column);
      return cell == null ? -2 : cell.getData();
    }

    private boolean containsMine(int row, int column) {
      return getData(row, column) == -1;
    }

    private void revealCellAndSurrounding(int row, int column) {
      Cell cell = getCell(row, column);
      if (cell != null && !cell.getRevealed()) {
        cell.setRevealed();
        revealInGUI(row, column);
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

    private class Cell {
      private int data = 0;
      private boolean revealed = false;

      private int getData() {
        return data;
      }
      private void setData(int data) {
        this.data = data;
      }

      private boolean getRevealed() {
        return revealed;
      }
      private void setRevealed() {
        revealed = true;
      }
    }
  }
}