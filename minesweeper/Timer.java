
public class Timer {
	private long startTime;
	private long endTime;
	
	public Timer() {
		startTime = System.currentTimeMillis();
	}
	
	public int elapsedTime() {
		endTime = System.currentTimeMillis();
		return (int)((endTime - startTime) / 1000);
	}
}
