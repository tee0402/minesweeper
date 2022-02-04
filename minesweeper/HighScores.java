import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

class HighScores {
	private final Scores easyScores = new Scores("easy_scores.txt");
	private final Scores mediumScores = new Scores("medium_scores.txt");
	private final Scores hardScores = new Scores("hard_scores.txt");
  private JFrame frame;

  int addHighScore(String difficulty, int score) {
    switch (difficulty) {
      case "easy":
        return easyScores.add(score);
      case "medium":
        return mediumScores.add(score);
      case "hard":
        return hardScores.add(score);
    }
    return -1;
  }
	
	void highScoresWindow(String difficulty, int newHighScoreIndex) {
    closeHighScoresWindow();
		frame = new JFrame("High Scores");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel numbersPanel = new JPanel();
    numbersPanel.setLayout(new GridLayout(11, 1));
    numbersPanel.add(new JLabel());
    for (int i = 1; i <= 10; i++) {
      JLabel numberLabel = new JLabel(i + ".");
      numberLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
      numbersPanel.add(numberLabel);
    }
    frame.add(numbersPanel, BorderLayout.WEST);
		
		JPanel scoresPanel = new JPanel();
    scoresPanel.setLayout(new GridLayout(11, 3));
		JLabel easyLabel = new JLabel("Easy", SwingConstants.CENTER);
		JLabel mediumLabel = new JLabel("Medium", SwingConstants.CENTER);
		JLabel hardLabel = new JLabel("Hard", SwingConstants.CENTER);
    easyLabel.setFont(new Font("Verdana", Font.BOLD, 20));
    mediumLabel.setFont(new Font("Verdana", Font.BOLD, 20));
    hardLabel.setFont(new Font("Verdana", Font.BOLD, 20));
    scoresPanel.add(easyLabel);
    scoresPanel.add(mediumLabel);
    scoresPanel.add(hardLabel);
    boolean easy = difficulty.equals("easy");
    boolean medium = difficulty.equals("medium");
    boolean hard = difficulty.equals("hard");
    for (int i = 0; i < 10; i++) {
      boolean newHighScoreRow = i == newHighScoreIndex;
			scoresPanel.add(easyScores.createScoreLabel(i, easy && newHighScoreRow));
      scoresPanel.add(mediumScores.createScoreLabel(i, medium && newHighScoreRow));
      scoresPanel.add(hardScores.createScoreLabel(i, hard && newHighScoreRow));
		}
    frame.add(scoresPanel, BorderLayout.EAST);

    if (newHighScoreIndex != -1) {
      JPanel playAgainPanel = new JPanel();
      playAgainPanel.setLayout(new GridLayout(1, 2));
      JButton playAgainButton = new JButton("Play again");
      playAgainButton.addActionListener(e -> {
        closeHighScoresWindow();
        Game.startGame(difficulty);
      });
      playAgainPanel.add(playAgainButton);
      JButton exitButton = new JButton("Exit");
      exitButton.addActionListener(e -> System.exit(0));
      playAgainPanel.add(exitButton);
      frame.add(playAgainPanel, BorderLayout.SOUTH);
    }

    frame.pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2);

    frame.setVisible(true);
	}

  private void closeHighScoresWindow() {
    if (frame != null) {
      frame.dispose();
    }
  }

  private static class Scores {
    private final ArrayList<Integer> scores = new ArrayList<>();
    private final String fileName;

    private Scores(String fileName) {
      this.fileName = fileName;
      read();
    }

    private void read() {
      File file = new File(fileName);
      try {
        if (file.exists() || file.createNewFile()) {
          Scanner scanner = new Scanner(file);
          while (scanner.hasNextInt()) {
            scores.add(scanner.nextInt());
          }
          scanner.close();
        }
      } catch (IOException e) {
        System.out.println("Error scanning high scores file");
      }
    }

    private int add(int score) {
      int index = -1;
      int numScores = scores.size();
      if (numScores < 10 || score < scores.get(numScores - 1)) {
        if (numScores == 0) {
          scores.add(score);
          index = 0;
        } else if (numScores < 10 && score >= scores.get(numScores - 1)) {
          scores.add(score);
          index = scores.size() - 1;
        } else {
          for (int i = 0; i < numScores; i++) {
            if (score < scores.get(i)) {
              scores.add(i, score);
              if (scores.size() == 11) {
                scores.remove(10);
              }
              index = i;
              break;
            }
          }
        }
        write();
      }
      return index;
    }

    private void write() {
      try {
        FileWriter fileWriter = new FileWriter(fileName);
        for (int score : scores) {
          fileWriter.write(score + " ");
        }
        fileWriter.close();
      } catch (IOException e) {
        System.out.println("Error writing high scores file");
      }
    }

    private JLabel createScoreLabel(int i, boolean highlight) {
      if (i < scores.size()) {
        JLabel scoreLabel = new JLabel(String.valueOf(scores.get(i)), SwingConstants.CENTER);
        if (highlight) {
          scoreLabel.setForeground(Color.red);
        }
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        return scoreLabel;
      } else {
        return new JLabel();
      }
    }
  }
}