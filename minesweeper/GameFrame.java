import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameFrame extends JFrame {
	GameFrame(int rows, int columns, int mines) {
		add(new GridPanel(rows, columns, mines));

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screenSize.width / 3, screenSize.height / 4, rows * 30, columns * 30);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
		setTitle("Minesweeper");
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(new AbstractAction("New Game") {
				public void actionPerformed(ActionEvent event) {
					Game.closeGame();
					new Game(Game.difficulty);
				}
			});
		gameMenu.add(new AbstractAction("Highscores") {
        public void actionPerformed(ActionEvent event)
        {
          Game.highScores.highScoresWindow(Game.difficulty, -1);
        }
      });
		JMenu changeDifficulty = new JMenu("Change Difficulty");
    changeDifficulty.add(new AbstractAction("Easy") {
        public void actionPerformed(ActionEvent e) {
          Game.closeGame();
          new Game("easy");
        }
      });
    changeDifficulty.add(new AbstractAction("Medium") {
        public void actionPerformed(ActionEvent event) {
          Game.closeGame();
          new Game("medium");
        }
      });
    changeDifficulty.add(new AbstractAction("Hard") {
        public void actionPerformed(ActionEvent event) {
          Game.closeGame();
          new Game("hard");
        }
      });
		gameMenu.add(changeDifficulty);
		gameMenu.add(new AbstractAction("Exit") {
				public void actionPerformed(ActionEvent event)
				{
					System.exit(0);
				}
			});
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new AbstractAction("Instructions") {
				public void actionPerformed(ActionEvent e) {
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
