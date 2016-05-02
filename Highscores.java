import java.util.*;
import java.io.*;

public class Highscores {

	private ArrayList<Integer> easyScores;
	private ArrayList<String> easyNames;
	private ArrayList<Integer> mediumScores;
	private ArrayList<String> mediumNames;
	private ArrayList<Integer> hardScores;
	private ArrayList<String> hardNames;
	
	public Highscores() {
		easyScores = new ArrayList<Integer>();
		easyNames = new ArrayList<String>();
		mediumScores = new ArrayList<Integer>();
		mediumNames = new ArrayList<String>();
		mediumScores = new ArrayList<Integer>();
		mediumNames = new ArrayList<String>();
		
		try {
			Scanner easy = new Scanner(new File("easy_scores.txt"));
			while (easy.hasNext()) {
				easyNames.add(easy.next());
				easyScores.add(easy.nextInt());
			}
			easy.close();
			Scanner medium = new Scanner(new File("medium_scores.txt"));
			while (medium.hasNext()) {
				mediumNames.add(easy.next());
				mediumScores.add(easy.nextInt());
			}
			medium.close();
			Scanner hard = new Scanner(new File("hard_scores.txt"));
			while (hard.hasNext()) {
				hardNames.add(easy.next());
				hardScores.add(easy.nextInt());
			}
			hard.close();
		} catch (IOException e) {
			System.out.println("High score file not found.");
		}
	}
}
