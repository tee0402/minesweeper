import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel {
  private final JTextField flagsLeftTextField = new JTextField();
  private final JTextField timeElapsedTextField = new JTextField();
  private final Timer timer = new Timer(100, e -> setTimeElapsedTextField());

  InfoPanel(int flagsLeft) {
    setLayout(new GridLayout(1, 2));

    Game.time.reset();
    setTimeElapsedTextField();
    timeElapsedTextField.setEditable(false);
    timeElapsedTextField.setFont(new Font("Verdana", Font.BOLD, 13));
    add(timeElapsedTextField);

    setFlagsLeftTextField(flagsLeft);
    flagsLeftTextField.setEditable(false);
    flagsLeftTextField.setFont(new Font("Verdana", Font.BOLD, 13));
    add(flagsLeftTextField);

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