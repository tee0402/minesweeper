import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class InfoWindow extends JFrame {
  private final JTextField flagsLeftTextField, timeElapsedTextField;
  private final Timer timer;
	
	InfoWindow(int flagsLeft, Time time) {
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
    setFlagsLeftTextField(flagsLeft);
    flagsLeftTextField.setEditable(false);
    flagsLeftTextField.setBounds(12, 100, 116, 22);
		getContentPane().add(flagsLeftTextField);
    flagsLeftTextField.setColumns(10);

    timeElapsedTextField.setText("Time: " + time.timeElapsed());
		timer = new Timer(100, e -> timeElapsedTextField.setText("Time: " + time.timeElapsed()));
		timer.start();

    setVisible(true);
	}

  void setFlagsLeftTextField(int flagsLeft) {
    flagsLeftTextField.setText("Flags left: " + flagsLeft);
  }

  void stopTimeUpdates() {
    timer.stop();
  }
}
