/******************************************************************************************
 * 
 * 	NAME:			Iao Seng Sio and Sollie Garcia
 * 
 * 	HOMEWORK: 		MineSweeper Project
 * 
 * 	CLASS:			ICS 211
 * 
 * 	INSTRUCTOR:		Scott Robertson
 * 
 *	DATE: 			May 4, 2016
 * 
 *	FILENAMES: 		Time.java
 *
 *	DESCRIPTION: 	contains the conversion for the elapsed time for the timer. 
 *
 **********************************************************************************************/
public class Time {
	private static long startTime;
	
	/*************************************************
	 * 
	 * 	Method:			instantiate
	 * 
	 * 	Description: 	starts the timer
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public static void instantiate() {
		startTime = System.currentTimeMillis();
	}
	
	/*************************************************
	 * 
	 * 	Method:			timeElapsed
	 * 
	 * 	Description: 	stops the timer and returns the time elapsed in seconds
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		time elapsed in seconds
	 * 
	 *************************************************/
	
	public static int timeElapsed() {
		long endTime = System.currentTimeMillis();
		return (int) ((endTime - startTime) / 1000);
	}
}
