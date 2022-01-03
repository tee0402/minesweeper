import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

class HighScores {
	private final ArrayList<Integer> easyScores = new ArrayList<>();
	private final ArrayList<Integer> mediumScores = new ArrayList<>();
	private final ArrayList<Integer> hardScores = new ArrayList<>();
  private JFrame frame;

  HighScores() {
    readHighScores("easy_scores.txt", easyScores);
    readHighScores("medium_scores.txt", mediumScores);
    readHighScores("hard_scores.txt", hardScores);
  }

  private void readHighScores(String fileName, ArrayList<Integer> scores) {
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

  int checkHighScore(String difficulty, int score) {
    int newHighScoreIndex = -1;
    int numEasyScores = easyScores.size();
    int numMediumScores = mediumScores.size();
    int numHardScores = hardScores.size();
    if (difficulty.equals("easy") && (numEasyScores < 10 || score < easyScores.get(numEasyScores - 1))) {
      newHighScoreIndex = addHighScore(score, easyScores);
      writeHighScores(easyScores, "easy_scores.txt");
    } else if (difficulty.equals("medium") && (numMediumScores < 10 || score < mediumScores.get(numMediumScores - 1))) {
      newHighScoreIndex = addHighScore(score, mediumScores);
      writeHighScores(mediumScores, "medium_scores.txt");
    } else if (difficulty.equals("hard") && (numHardScores < 10 || score < hardScores.get(numHardScores - 1))) {
      newHighScoreIndex = addHighScore(score, hardScores);
      writeHighScores(hardScores, "hard_scores.txt");
    }
    return newHighScoreIndex;
  }

  private int addHighScore(int score, ArrayList<Integer> highScores) {
    int numHighScores = highScores.size();
    if (numHighScores == 0) {
      highScores.add(score);
      return 0;
    } else if (numHighScores < 10 && score >= highScores.get(numHighScores - 1)) {
      highScores.add(score);
      return highScores.size() - 1;
    } else {
      for (int i = 0; i < numHighScores; i++) {
        if (score < highScores.get(i)) {
          highScores.add(i, score);
          if (highScores.size() == 11) {
            highScores.remove(10);
          }
          return i;
        }
      }
    }
    return -1;
  }

  private void writeHighScores(ArrayList<Integer> highScores, String fileName) {
    try {
      FileWriter fileWriter = new FileWriter(fileName);
      for (int highScore : highScores) {
        fileWriter.write(highScore + " ");
      }
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Error writing high scores file");
    }
  }
	
	void highScoresWindow(String difficulty, int newHighScoreIndex) {
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
    for (int i = 0; i < 10; i++) {
			scoresPanel.add(createScoreLabel(easyScores, i, newHighScoreIndex, difficulty.equals("easy")));
      scoresPanel.add(createScoreLabel(mediumScores, i, newHighScoreIndex, difficulty.equals("medium")));
      scoresPanel.add(createScoreLabel(hardScores, i, newHighScoreIndex, difficulty.equals("hard")));
		}
    frame.add(scoresPanel, BorderLayout.EAST);

    frame.pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2);

    frame.setVisible(true);
	}

  private JLabel createScoreLabel(ArrayList<Integer> scores, int i, int newHighScoreIndex, boolean currentDifficulty) {
    if (i < scores.size()) {
      JLabel scoreLabel = new JLabel(String.valueOf(scores.get(i)), SwingConstants.CENTER);
      if (currentDifficulty && i == newHighScoreIndex) {
        scoreLabel.setForeground(Color.red);
      }
      scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
      return scoreLabel;
    } else {
      return new JLabel();
    }
  }

  void closeHighScoresWindow() {
    frame.dispose();
  }
}