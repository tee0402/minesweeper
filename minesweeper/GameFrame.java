import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class GameFrame extends JFrame {
  InfoPanel infoPanel;

	GameFrame(int rows, int columns, int mines, String difficulty) {
    super("Minesweeper");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(new AbstractAction("New Game") {
      @Override
      public void actionPerformed(ActionEvent e) {
        Game.startGame(difficulty);
      }
    });
		gameMenu.add(new AbstractAction("High Scores") {
      @Override
      public void actionPerformed(ActionEvent e) {
        Game.highScores.highScoresWindow(difficulty, -1);
      }
    });
		JMenu changeDifficulty = new JMenu("Change Difficulty");
    if (!difficulty.equals("easy")) {
      changeDifficulty.add(new AbstractAction("Easy") {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.startGame("easy");
        }
      });
    }
    if (!difficulty.equals("medium")) {
      changeDifficulty.add(new AbstractAction("Medium") {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.startGame("medium");
        }
      });
    }
    if (!difficulty.equals("hard")) {
      changeDifficulty.add(new AbstractAction("Hard") {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.startGame("hard");
        }
      });
    }
		gameMenu.add(changeDifficulty);
		gameMenu.add(new AbstractAction("Exit") {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new AbstractAction("Instructions") {
      @Override
      public void actionPerformed(ActionEvent e) {
        String message = "The objective of the game is to reveal all the squares in the grid without clicking on a mine.\r\n\r\n";
        message += "Left click on a square to reveal its contents.\r\n";
        message += "Right click on a square to flag it.\r\n\r\n";
        message += "There are three pre-defined maps to play: easy, medium, and hard.\r\n";
        JOptionPane.showMessageDialog(getParent(), message, "About Minesweeper", JOptionPane.INFORMATION_MESSAGE);
      }
    });
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
    setJMenuBar(menuBar);

    add(new GridPanel(rows, columns, mines, difficulty), BorderLayout.PAGE_END);
    add(infoPanel = new InfoPanel(mines), BorderLayout.PAGE_START);

    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

    setVisible(true);
	}
}