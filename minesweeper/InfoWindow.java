import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

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
 *	FILENAMES: 		InfoWindow
 *
 *	DESCRIPTION: 	creates the game window that shows the amount of flags left and shows the timer.
 *
 **********************************************************************************************/

public class InfoWindow extends JFrame{
  public static JTextField flagsLeftTextField;
	private final JTextField timeElapsedTextField;
	
	/*************************************************
	 * 
	 * 	Method:			InfoWindow
	 * 
	 * 	Description: 	Constructor. Creates the window 
	 * 					that contains the amount of flags left and shows the timer
	 * 
	 * 	param: 			none
	 * 
	 * 	return: 		none
	 * 
	 *************************************************/
	
	public InfoWindow()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width * 4 / 5, screenSize.height / 4, 166, 189);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);

    flagsLeftTextField = new JTextField();
    flagsLeftTextField.setText("Flags left: " + GridPanel.flags);
    flagsLeftTextField.setEditable(false);
    flagsLeftTextField.setBounds(12, 21, 116, 22);
		getContentPane().add(flagsLeftTextField);
    flagsLeftTextField.setColumns(10);

    timeElapsedTextField = new JTextField();
    timeElapsedTextField.setEditable(false);
    timeElapsedTextField.setBounds(12, 100, 116, 22);
		getContentPane().add(timeElapsedTextField);
    timeElapsedTextField.setColumns(10);

    Time.instantiate();
    timeElapsedTextField.setText("Time: " + Time.timeElapsed());
		Timer timer = new Timer(1000, e -> timeElapsedTextField.setText("Time: " + Time.timeElapsed()));
		timer.start();

    setVisible(true);
	}
}
