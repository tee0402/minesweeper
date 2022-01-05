class Game {
  static HighScores highScores = new HighScores();
  static Time time = new Time();
  static GameFrame gameFrame;

  static void startGame(String difficulty) {
    closeGame();
    switch (difficulty) {
      case "easy":
        gameFrame = new GameFrame(10, 10, 10, difficulty);
        break;
      case "medium":
        gameFrame = new GameFrame(15, 15, 40, difficulty);
        break;
      case "hard":
        gameFrame = new GameFrame(22, 22, 99, difficulty);
        break;
    }
  }
	
	private static void closeGame() {
    if (gameFrame != null) {
      gameFrame.dispose();
    }
	}
}