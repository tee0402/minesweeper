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
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(11, 4));
		frame.add(panel);

		JLabel easyLabel = new JLabel("Easy", SwingConstants.CENTER);
		JLabel mediumLabel = new JLabel("Medium", SwingConstants.CENTER);
		JLabel hardLabel = new JLabel("Hard", SwingConstants.CENTER);
    easyLabel.setFont(new Font("Verdana", Font.BOLD, 20));
    mediumLabel.setFont(new Font("Verdana", Font.BOLD, 20));
    hardLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(easyLabel);
		panel.add(mediumLabel);
		panel.add(hardLabel);

    for (int i = 0; i < 10; i++) {
			addScoreLabel(panel, true, easyScores, i, newHighScoreIndex, difficulty.equals("easy"));
      addScoreLabel(panel, false, mediumScores, i, newHighScoreIndex, difficulty.equals("medium"));
      addScoreLabel(panel, false, hardScores, i, newHighScoreIndex, difficulty.equals("hard"));
		}

    frame.pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2);

    frame.setVisible(true);
	}

  private void addScoreLabel(JPanel panel, boolean writeNumber, ArrayList<Integer> scores, int i, int newHighScoreIndex, boolean highlight) {
    JLabel scoreLabel;
    if (i < scores.size()) {
      scoreLabel = writeNumber ? new JLabel((i + 1) + (i == 9 ? ".     " : ".       ") + scores.get(i)) : new JLabel(String.valueOf(scores.get(i)), SwingConstants.CENTER);
      if (highlight && i == newHighScoreIndex) {
        scoreLabel.setForeground(Color.red);
      }
    } else {
      scoreLabel = writeNumber ? new JLabel((i + 1) + (i == 9 ? ".     " : ".       ")) : new JLabel();
    }
    scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
    panel.add(scoreLabel);
  }

  void closeHighScoresWindow() {
    frame.dispose();
  }
}