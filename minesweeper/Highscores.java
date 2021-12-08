import java.util.*;
import java.awt.*;
import java.io.*;

import javax.swing.*;

/******************************************************************************************
 * 
 * 	NAME:			Iao Seng Sio and Sollie Garcia
 * 
 * 	HOMEWORK: 		MineSweeper Project
 * 
 * 	CLASS:			ICS 211
 * 
 * 	INSTRUCTOR:		Scott Robertson
 * 
 *	DATE: 			May 4, 2016
 * 
 *	FILENAMES: 		highscores.java
 *
 *	DESCRIPTION: 	contains the methods for writing to the highscores to a file
 *
 **********************************************************************************************/

public class Highscores {

	private static ArrayList<Integer> easyScores;
	private static ArrayList<Integer> mediumScores;
	private static ArrayList<Integer> hardScores;
	
	/*************************************************
	 * 
	 * 	Method:			instantiate
	 * 
	 * 	Description: 	contains all the scanners for the file writing of the different difficulties.
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
  public static void instantiate() {
		easyScores = new ArrayList<>();
		mediumScores = new ArrayList<>();
		hardScores = new ArrayList<>();
		
		try {
      File easyScoresFile = new File("easy_scores.txt");
      easyScoresFile.createNewFile();
      File mediumScoresFile = new File("medium_scores.txt");
      mediumScoresFile.createNewFile();
      File hardScoresFile = new File("hard_scores.txt");
      hardScoresFile.createNewFile();
			Scanner sc = new Scanner(easyScoresFile);
			while (sc.hasNext()) {
				easyScores.add(sc.nextInt());
			}
			sc.close();
			sc = new Scanner(mediumScoresFile);
			while (sc.hasNext()) {
				mediumScores.add(sc.nextInt());
			}
			sc.close();
			sc = new Scanner(hardScoresFile);
			while (sc.hasNext()) {
				hardScores.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			System.out.println("Highscores file not found.");
		}
	}

  /*************************************************
   *
   * 	Method:			checkHighscore
   *
   * 	Description: 	writes the score in the correct position in the list
   *
   * 	param: 			String difficulty, int score
   *
   * 	return: 		none
   *
   *************************************************/

  public static void checkHighscore(String difficulty, int score) {
    if (difficulty.equals("easy") && (easyScores.size() == 0 || easyScores.size() < 10 || score < easyScores.get(easyScores.size() - 1))) {
      addHighscore(score, easyScores);
      writeHighscores(easyScores, "easy_scores.txt");
    }
    else if (difficulty.equals("medium") && (mediumScores.size() == 0 || mediumScores.size() < 10 || score < mediumScores.get(mediumScores.size() - 1))) {
      addHighscore(score, mediumScores);
      writeHighscores(mediumScores, "medium_scores.txt");
    }
    else if (difficulty.equals("hard") && (hardScores.size() == 0 || hardScores.size() < 10 || score < hardScores.get(hardScores.size() - 1))) {
      addHighscore(score, hardScores);
      writeHighscores(hardScores, "hard_scores.txt");
    }
  }

  public static void addHighscore(int score, ArrayList<Integer> highscores) {
    if (highscores.size() == 0 || (highscores.size() < 10 && score >= highscores.get(highscores.size() - 1))) {
      highscores.add(score);
    }
    else {
      for (int i = 0; i < highscores.size(); i++) {
        if (score < highscores.get(i)) {
          highscores.add(i, score);
          if (highscores.size() == 11) {
            highscores.remove(10);
          }
          break;
        }
      }
    }
  }

  public static void writeHighscores(ArrayList<Integer> highscores, String highscoresFile) {
    try {
      FileWriter fw = new FileWriter(highscoresFile);
      for (Integer highscore : highscores) {
        fw.write(highscore + " ");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("Highscores file not found.");
    }
  }
	
	/*************************************************
	 * 
	 * 	Method:			highscoresWindow
	 * 
	 * 	Description: 	creates a new window containing the the local
	 * 					high scores
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		frame of new window
	 * 
	 *************************************************/
	
	public static JFrame highscoresWindow() {
		JFrame frame = new JFrame("Highscores");
		frame.setSize(400, 500);
		frame.setLocation(2 * (int)Menu.screenWidth / 5, (int)Menu.screenHeight / 4);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(11, 4));
		frame.add(panel);

		JLabel easy = new JLabel("Easy", SwingConstants.CENTER);
		JLabel medium = new JLabel("Medium", SwingConstants.CENTER);
		JLabel hard = new JLabel("Hard", SwingConstants.CENTER);
		easy.setFont(new Font("Verdana", Font.BOLD, 20));
		medium.setFont(new Font("Verdana", Font.BOLD, 20));
		hard.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);

    JLabel scoreLabel;
		for (int i = 0; i < 10; i++) {
			if (i < easyScores.size()) {
        scoreLabel = new JLabel((i + 1) + ".       " + easyScores.get(i));
			}
			else {
        scoreLabel = new JLabel((i + 1) + ".       ");
			}
      scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
      panel.add(scoreLabel);
			if (i < mediumScores.size()) {
        scoreLabel = new JLabel(String.valueOf(mediumScores.get(i)), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
			}
			else {
        scoreLabel = new JLabel();
			}
      panel.add(scoreLabel);
			if (i < hardScores.size()) {
        scoreLabel = new JLabel(String.valueOf(hardScores.get(i)), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
			}
			else {
        scoreLabel = new JLabel();
			}
      panel.add(scoreLabel);
		}

    return frame;
	}
}
