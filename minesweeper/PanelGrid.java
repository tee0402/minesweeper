import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

class PanelGrid extends JPanel {
  private final int rows, columns, mines;
  private int cellsLeft, flagsLeft;
  private final CellGrid cellGrid;
	private final JPanel[][] panelGrid;
  private ImageIcon flagImage, mineImage;

	PanelGrid(int rows, int columns, int mines, Difficulty difficulty) {
		this.rows = rows;
    this.columns = columns;
    cellsLeft = rows * columns;
		this.mines = flagsLeft = mines;
    cellGrid = new CellGrid(rows, columns, mines);
    panelGrid = new JPanel[rows][columns];

    try {
      BufferedImage flag = ImageIO.read(Objects.requireNonNull(PanelGrid.class.getResource("resources/flag.gif")));
      flagImage = new ImageIcon(flag.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
      BufferedImage mine = ImageIO.read(Objects.requireNonNull(PanelGrid.class.getResource("resources/mine.gif")));
      mineImage = new ImageIcon(mine.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
    } catch (IOException e) {
      System.out.println("Error scaling images");
    }

		setLayout(new GridLayout(rows, columns));
    InfoPanel infoPanel = GameFrame.getInfoPanel();
    HighScores highScores = Game.getHighScores();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

				JButton cellButton = new JButton();
        cellButton.setPreferredSize(new Dimension(30, 25));
        cellButton.setBackground(Color.white);
        // Add left click listener
        boolean mine = cellGrid.containsMine(i, j);
        final int row = i;
        final int column = j;
        cellButton.addActionListener(e -> {
          // If left-clicked mine, end game, otherwise reveal cell and surrounding cells recursively
          if (mine) {
            infoPanel.stopTimeUpdates();
            showAllMines();
            // Show game over prompt
            int selection = JOptionPane.showConfirmDialog(null, "Game over! Play again?");
            switch (selection) {
              case JOptionPane.YES_OPTION -> Game.startGame(difficulty);
              case JOptionPane.NO_OPTION -> System.exit(0);
            }
          } else {
            cellGrid.revealCellAndSurrounding(row, column);
            // When all cells have been revealed
            if (cellsLeft <= this.mines) {
              int score = (int) Game.getTime().timeElapsed();
              infoPanel.stopTimeUpdates();
              showAllMines();
              int newHighScoreIndex = highScores.addHighScore(difficulty, score);
              // Show game over prompt if no new high score or high scores window if new high score
              if (newHighScoreIndex == -1) {
                int selection = JOptionPane.showConfirmDialog(null, "You won with a score of " + score + "! Play again?");
                switch (selection) {
                  case JOptionPane.YES_OPTION -> Game.startGame(difficulty);
                  case JOptionPane.NO_OPTION -> System.exit(0);
                }
              } else {
                highScores.highScoresWindow(difficulty, newHighScoreIndex);
              }
            }
          }
        });
        // Add right click listener
        cellButton.addMouseListener(new MouseAdapter() {
          @Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
              if (cellButton.getIcon() != null) {
                cellButton.setIcon(null);
                flagsLeft++;
              } else if (flagsLeft > 0) {
                cellButton.setIcon(flagImage);
                flagsLeft--;
							}
              infoPanel.setFlagsLeftTextField(flagsLeft);
						}
					}
				});
        panel.add(cellButton);

        panelGrid[i][j] = panel;
			}
		}
	}

  private void revealInGUI(int row, int column, int data) {
    JPanel panel = panelGrid[row][column];
    panel.removeAll();
    if (data > 0) {
      panel.add(new JLabel(String.valueOf(data), SwingConstants.CENTER));
    }
    panel.revalidate();
    panel.repaint();
    cellsLeft--;
  }

  private void showAllMines() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (cellGrid.containsMine(i, j)) {
          ((JButton) panelGrid[i][j].getComponent(0)).setIcon(mineImage);
        }
      }
    }
  }

  private class CellGrid {
    private final Cell[][] cellGrid;
    private final int rows, columns;

    private CellGrid(int rows, int columns, int mines) {
      this.rows = rows;
      this.columns = columns;

      // Set up grid of empty cells
      cellGrid = new Cell[rows][columns];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          cellGrid[i][j] = new Cell();
        }
      }

      // Place mines randomly
      Random random = new Random();
      int minesPlaced = 0;
      while (minesPlaced < mines) {
        int x = random.nextInt(rows);
        int y = random.nextInt(columns);
        if (!containsMine(x, y)) {
          incrementCell(getCell(x - 1, y - 1));
          incrementCell(getCell(x - 1, y));
          incrementCell(getCell(x - 1, y + 1));
          incrementCell(getCell(x, y - 1));
          cellGrid[x][y].setData(-1);
          incrementCell(getCell(x, y + 1));
          incrementCell(getCell(x + 1, y - 1));
          incrementCell(getCell(x + 1, y));
          incrementCell(getCell(x + 1, y + 1));
          minesPlaced++;
        }
      }
    }

    private boolean containsMine(int row, int column) {
      return cellGrid[row][column].getData() == -1;
    }

    private void incrementCell(Cell cell) {
      if (cell != null && cell.getData() != -1) {
        cell.setData(cell.getData() + 1);
      }
    }

    private Cell getCell(int row, int column) {
      return row >= 0 && row < rows && column >= 0 && column < columns ? cellGrid[row][column] : null;
    }

    // DFS
    private void revealCellAndSurrounding(int row, int column) {
      Cell cell = getCell(row, column);
      if (cell != null && !cell.getRevealed()) {
        cell.setRevealed();
        int data = cell.getData();
        revealInGUI(row, column, data);
        if (data == 0) {
          revealCellAndSurrounding(row - 1, column - 1);
          revealCellAndSurrounding(row - 1, column);
          revealCellAndSurrounding(row - 1, column + 1);
          revealCellAndSurrounding(row, column - 1);
          revealCellAndSurrounding(row, column + 1);
          revealCellAndSurrounding(row + 1, column - 1);
          revealCellAndSurrounding(row + 1, column);
          revealCellAndSurrounding(row + 1, column + 1);
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