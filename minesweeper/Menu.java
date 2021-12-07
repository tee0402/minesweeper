import javax.swing.*;
import java.awt.*;

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
 *	FILENAMES: 		Menu.java
 *
 *	DESCRIPTION: 	This file contains all the methods that are required to create and show the
 *					game field. This includes the top tool bar and its methods.
 *
 **********************************************************************************************/
public class Menu {

  public static double screenWidth, screenHeight;

	/*************************************************
	 * 
	 * 	Method:			Menu
	 * 
	 * 	Description: 	Constructor. Creates the game field and the 
	 * 					game tray and help tray.
	 * 
	 * 	param: 			none	
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	public Menu() {
    int width = 500;
    int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.getWidth();
		screenHeight = screen.getHeight();
		JFrame frame = new JFrame("Minesweeper");
		frame.setSize(width, height);
		frame.setLocation((int)screenWidth * 2 / 5, (int)screenHeight / 4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.lightGray);
		frame.add(panel);
		
		JLabel label = new JLabel("Minesweeper");
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");

    easy.addActionListener(e -> {
       frame.dispose();
       new Game("easy");
     });
		medium.addActionListener(e -> {
      frame.dispose();
      new Game("medium");
    });
		hard.addActionListener(e -> {
      frame.dispose();
      new Game("hard");
    });
		
		panel.add(label);
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		
		label.setFont(new Font("Verdana", Font.BOLD, 50));
		easy.setMaximumSize(new Dimension(width / 2, height / 5));
		medium.setMaximumSize(new Dimension(width / 2, height / 5));
		hard.setMaximumSize(new Dimension(width / 2, height / 5));
		
		label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		easy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		medium.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		hard.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		frame.setVisible(true);
	}
}
