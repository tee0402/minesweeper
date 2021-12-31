class Game {
	static String difficulty;
  static HighScores highScores;
	private static GameFrame gameFrame;
  static Time time;
	static InfoWindow infoWindow;
	
	Game(String newDifficulty) {
		difficulty = newDifficulty;
		switch (difficulty) {
			case "easy":
				startGame(10, 10, 10);
				break;
			case "medium":
				startGame(15, 15, 40);
				break;
			case "hard":
				startGame(22, 22, 99);
				break;
		}
	}
	
	private void startGame(int rows, int columns, int mines) {
    highScores = new HighScores();
		gameFrame = new GameFrame(rows, columns, mines);
    time = new Time();
		infoWindow = new InfoWindow(mines, time);
		gameFrame.setVisible(true);
	}
	
	static void closeGame() {
		gameFrame.dispose();
		infoWindow.dispose();
	}
}
