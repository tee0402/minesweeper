import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
	
	private int width;
	private int height;
	public static double screenWidth;
	public static double screenHeight;
	public static Grid grid;
	
	public Menu() {
		width = 500;
		height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.getWidth();
		screenHeight = screen.getHeight();
		JFrame frame = new JFrame("Minesweeper");
		frame.setSize(width, height);
		frame.setLocation(2 * (int)screenWidth / 5, (int)screenHeight / 4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.lightGray);
		frame.add(panel);
		
		JLabel label = new JLabel("Minesweeper");		
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid = new Grid(10, 10, 9);
				frame.dispose();
				new Game();
			}
		});
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid = new Grid(15, 15, 50);
				frame.dispose();
				new Game();
			}
		});
		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid = new Grid(20, 20, 100);
				frame.dispose();
				new Game();
			}
		});
		
		panel.add(label);
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		
		label.setFont(new Font("Verdana", Font.PLAIN, 50));
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
