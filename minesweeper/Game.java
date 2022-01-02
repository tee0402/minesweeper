class Game {
	static String difficulty;
  static HighScores highScores = new HighScores();
  static Time time = new Time();
  static GameFrame gameFrame;

  static void startGame(String newDifficulty) {
    difficulty = newDifficulty;
    switch (difficulty) {
      case "easy":
        gameFrame = new GameFrame(10, 10, 10);
        break;
      case "medium":
        gameFrame = new GameFrame(15, 15, 40);
        break;
      case "hard":
        gameFrame = new GameFrame(22, 22, 99);
        break;
    }
  }
	
	static void closeGame() {
		gameFrame.dispose();
	}
}