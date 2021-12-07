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
	
	public static void instantiate() {
		easyScores = new ArrayList<>();
		mediumScores = new ArrayList<>();
		hardScores = new ArrayList<>();
		
		try {
			Scanner easy = new Scanner(new File("easy_scores.txt"));
			while (easy.hasNext()) {
				easyScores.add(easy.nextInt());
			}
			easy.close();
			Scanner medium = new Scanner(new File("medium_scores.txt"));
			while (medium.hasNext()) {
				mediumScores.add(medium.nextInt());
			}
			medium.close();
			Scanner hard = new Scanner(new File("hard_scores.txt"));
			while (hard.hasNext()) {
				hardScores.add(hard.nextInt());
			}
			hard.close();
		} catch (IOException e) {
			System.out.println("Highscores file not found.");
		}
		
		if (easyScores.size() == 0) {
			easyScores.add(9001);
		}
		if (mediumScores.size() == 0) {
			mediumScores.add(9001);
		}
		if (hardScores.size() == 0) {
			hardScores.add(9001);
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
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public static void highscoresWindow() {
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
		
		for (int i = 0; i < 10; i++) {
			if (i < easyScores.size() && easyScores.get(i) != 9001) {
				JLabel easyScoresLabel = new JLabel((i + 1) + ".       " + easyScores.get(i));
				easyScoresLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
				panel.add(easyScoresLabel);
			}
			else {
				JLabel easyScoresLabel = new JLabel((i + 1) + ".       ");
				easyScoresLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
				panel.add(easyScoresLabel);
			}
			if (i < mediumScores.size() && mediumScores.get(i) != 9001) {
				JLabel mediumScoresLabel = new JLabel(String.valueOf(mediumScores.get(i)), SwingConstants.CENTER);
				mediumScoresLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
				panel.add(mediumScoresLabel);
			}
			else {
				JLabel mediumScoresLabel = new JLabel();
				panel.add(mediumScoresLabel);
			}
			if (i < hardScores.size() && hardScores.get(i) != 9001) {
				JLabel hardScoresLabel = new JLabel(String.valueOf(hardScores.get(i)), SwingConstants.CENTER);
				hardScoresLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
				panel.add(hardScoresLabel);
			}
			else {
				JLabel hardScoresLabel = new JLabel();
				panel.add(hardScoresLabel);
			}
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
		if (difficulty.equals("easy") && score < easyScores.get(easyScores.size() - 1)) {
			for (int i = 0; i < easyScores.size(); i++) {
				if (score <= easyScores.get(i) && easyScores.size() < 10) {
					easyScores.add(i, score);
					break;
				}
				else if (score <= easyScores.get(i)) {
					easyScores.add(i, score);
					easyScores.remove(easyScores.size() - 1);
					break;
				}
			}
			try {
				FileWriter fw = new FileWriter("easy_scores.txt");
        for (Integer easyScore : easyScores) {
          fw.write(easyScore + " ");
        }
				fw.close();
			} catch (IOException e) {
				System.out.println("Highscores file not found.");
			}
		}
		else if (difficulty.equals("medium") && score < mediumScores.get(mediumScores.size() - 1)) {
			for (int i = 0; i < mediumScores.size(); i++) {
				if (score <= mediumScores.get(i) && mediumScores.size() < 10) {
					mediumScores.add(i, score);
					break;
				}
				else if (score <= mediumScores.get(i)) {
					mediumScores.add(i, score);
					mediumScores.remove(mediumScores.size() - 1);
					break;
				}
			}
			try {
				FileWriter fw = new FileWriter("medium_scores.txt");
        for (Integer mediumScore : mediumScores) {
          fw.write(mediumScore + " ");
        }
				fw.close();
			} catch (IOException e) {
				System.out.println("Highscores file not found.");
			}
		}
		else if (difficulty.equals("hard") && score < hardScores.get(hardScores.size() - 1)) {
			for (int i = 0; i < hardScores.size(); i++) {
				if (score <= hardScores.get(i) && hardScores.size() < 10) {
					hardScores.add(i, score);
					break;
				}
				else if (score <= hardScores.get(i)) {
					hardScores.add(i, score);
					hardScores.remove(hardScores.size() - 1);
					break;
				}
			}
			try {
				FileWriter fw = new FileWriter("hard_scores.txt");
        for (Integer hardScore : hardScores) {
          fw.write(hardScore + " ");
        }
				fw.close();
			} catch (IOException e) {
				System.out.println("Highscores file not found.");
			}
		}
	}
}
