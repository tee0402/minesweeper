class Game {
  private static final HighScores highScores = new HighScores();
  private static final Time time = new Time();
  private static GameFrame gameFrame;

  static void startGame(Difficulty difficulty) {
    if (gameFrame != null) {
      gameFrame.dispose();
    }
    switch (difficulty) {
      case EASY -> gameFrame = new GameFrame(10, 10, 10, difficulty);
      case MEDIUM -> gameFrame = new GameFrame(15, 15, 40, difficulty);
      case HARD -> gameFrame = new GameFrame(22, 22, 99, difficulty);
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