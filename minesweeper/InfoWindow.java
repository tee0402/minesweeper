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
  public static JTextField txtFlagsLeft_1;
	private final JTextField txtTime;
	Timer timer;
	
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
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenWidth * 4 / 5, screenHeight / 4, 166, 189);
    JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().setLayout(null);
		txtFlagsLeft_1 = new JTextField();
		txtFlagsLeft_1.setText("Flags left: " + GridPanel.flags);
		txtFlagsLeft_1.setEditable(false);
		txtFlagsLeft_1.setBounds(12, 21, 116, 22);
		getContentPane().add(txtFlagsLeft_1);
		txtFlagsLeft_1.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setBounds(12, 100, 116, 22);
		getContentPane().add(txtTime);
		txtTime.setColumns(10);
		setVisible(true);
		timer = new Timer(1000, e -> txtTime.setText("Time: " + Time.elapsedTime()));
		timer.start();
	}
}
