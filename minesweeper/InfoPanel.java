import java.awt.*;
import javax.swing.*;

class InfoPanel extends JPanel {
  private final JTextField flagsLeftTextField, timeElapsedTextField;
  private final Timer timer;

  InfoPanel(int flagsLeft) {
    setLayout(new GridLayout(1, 2));

    timeElapsedTextField = new JTextField();
    Game.time.reset();
    setTimeElapsedTextField();
    timeElapsedTextField.setEditable(false);
    timeElapsedTextField.setFont(new Font("Verdana", Font.BOLD, 13));
    add(timeElapsedTextField);

    flagsLeftTextField = new JTextField();
    setFlagsLeftTextField(flagsLeft);
    flagsLeftTextField.setEditable(false);
    flagsLeftTextField.setFont(new Font("Verdana", Font.BOLD, 13));
    add(flagsLeftTextField);

		timer = new Timer(100, e -> setTimeElapsedTextField());
		timer.start();
	}

  void setFlagsLeftTextField(int flagsLeft) {
    flagsLeftTextField.setText("Flags left: " + flagsLeft);
  }

  private void setTimeElapsedTextField() {
    timeElapsedTextField.setText("Time: " + Game.time.timeElapsed());
  }

  void stopTimeUpdates() {
    timer.stop();
  }
}
