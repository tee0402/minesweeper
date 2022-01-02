class Time {
	private long startTime;
	
	void reset() {
    startTime = System.currentTimeMillis();
  }
	
	int timeElapsed() {
		return (int) ((System.currentTimeMillis() - startTime) / 1000);
	}
}