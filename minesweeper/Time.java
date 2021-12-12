class Time {
	private final long startTime;
	
	Time() {
		startTime = System.currentTimeMillis();
	}
	
	int timeElapsed() {
		long endTime = System.currentTimeMillis();
		return (int) ((endTime - startTime) / 1000);
	}
}
