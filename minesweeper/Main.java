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
	 *	FILENAMES: 		Main.java
	 *
	 *	DESCRIPTION: 	This file starts the main menu of the game.
	 *
	 **********************************************************************************************/

public class Main {
	
	public static void main(String[] args) {
    JFrame frame = new JFrame("Minesweeper");
    int width = 500;
    int height = 500;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setBounds(screenSize.width * 2 / 5, screenSize.height / 4, width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setBackground(Color.lightGray);
    frame.add(panel);

    JLabel label = new JLabel("Minesweeper");
    label.setFont(new Font("Verdana", Font.BOLD, 50));
    label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(label);

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
    easy.setMaximumSize(new Dimension(width / 2, height / 5));
    medium.setMaximumSize(new Dimension(width / 2, height / 5));
    hard.setMaximumSize(new Dimension(width / 2, height / 5));
    easy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    medium.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    hard.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(easy);
    panel.add(medium);
    panel.add(hard);

    frame.setVisible(true);
	}
}
