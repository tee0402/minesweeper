class Time {
	private long startTime;
	
	void reset() {
    startTime = System.currentTimeMillis();
  }
	
	long timeElapsed() {
		return (System.currentTimeMillis() - startTime) / 1000;
	}
}