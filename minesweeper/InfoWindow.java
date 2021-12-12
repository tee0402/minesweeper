import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class InfoWindow extends JFrame{
  static JTextField flagsLeftTextField;
	private final JTextField timeElapsedTextField;
  static Time time;
  static Timer timer;
	
	InfoWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width * 4 / 5, screenSize.height / 4, 166, 189);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);

    timeElapsedTextField = new JTextField();
    timeElapsedTextField.setEditable(false);
    timeElapsedTextField.setBounds(12, 21, 116, 22);
    getContentPane().add(timeElapsedTextField);
    timeElapsedTextField.setColumns(10);

    flagsLeftTextField = new JTextField();
    flagsLeftTextField.setText("Flags left: " + GridPanel.flags);
    flagsLeftTextField.setEditable(false);
    flagsLeftTextField.setBounds(12, 100, 116, 22);
		getContentPane().add(flagsLeftTextField);
    flagsLeftTextField.setColumns(10);

    time = new Time();
    timeElapsedTextField.setText("Time: " + time.timeElapsed());
		timer = new Timer(100, e -> timeElapsedTextField.setText("Time: " + time.timeElapsed()));
		timer.start();

    setVisible(true);
	}
}
