class Game {
  private static final HighScores highScores = new HighScores();
  private static final Time time = new Time();
  private static GameFrame gameFrame;

  static void startGame(String difficulty) {
    if (gameFrame != null) {
      gameFrame.dispose();
    }
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
    time.reset();
  }

  static HighScores getHighScores() {
    return highScores;
  }

  static Time getTime() {
    return time;
  }
}