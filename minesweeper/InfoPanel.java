import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel {
  private final Time time = Game.getTime();
  private final JTextField timeElapsedTextField = new JTextField();
  private final JTextField flagsLeftTextField = new JTextField();
  private final Timer timer = new Timer(100, e -> setTimeElapsedTextField());

  InfoPanel(int flagsLeft) {
    setLayout(new GridLayout(1, 2));

    time.reset();
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

  private void setTimeElapsedTextField() {
    timeElapsedTextField.setText("Time: " + time.timeElapsed());
  }

  void stopTimeUpdates() {
    timer.stop();
  }

  void setFlagsLeftTextField(int flagsLeft) {
    flagsLeftTextField.setText("Flags left: " + flagsLeft);
  }
}