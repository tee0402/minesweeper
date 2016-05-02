import javax.swing.*;

import java.awt.*;

public class Game {
	
	private boolean inPlay;
	private Highscores highscores;
	private Timer timer;
	private Grid grid;
	
	public Game() {
		highscores = new Highscores();
		grid = Menu.grid;
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(2 * (int)Menu.screenWidth / 5, (int)Menu.screenHeight / 4);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenu menu = new JMenu();
		menuBar.add(menu);
		
		timer = new Timer();
		JLabel timeLabel = new JLabel();
		panel.add(timeLabel);
		
		inPlay = true;
		while (inPlay) {
			int time = timer.elapsedTime();
			timeLabel.setText(String.valueOf(time));
			
			boolean leftClicked = false;
			if (leftClicked) {
				//Set row and column to the button's row and column please
				int row = 0;
				int column = 0;
				Cell cell = grid.getCell(row, column);
				grid.reveal(cell);
				leftClicked = false;
			}
		}
		
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JOptionPane.showConfirmDialog(dialog, "Would you like to play again?");
	}
}
