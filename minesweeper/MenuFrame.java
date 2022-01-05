import javax.swing.*;
import java.awt.*;

class MenuFrame extends JFrame {
  MenuFrame() {
    super("Minesweeper");
    int width = 500;
    int height = 500;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setBackground(Color.lightGray);
    JLabel label = new JLabel("Minesweeper");
    label.setFont(new Font("Verdana", Font.BOLD, 50));
    label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(label);
    JButton easyButton = new JButton("Easy");
    JButton mediumButton = new JButton("Medium");
    JButton hardButton = new JButton("Hard");
    easyButton.addActionListener(e -> {
      dispose();
      Game.startGame("easy");
    });
    mediumButton.addActionListener(e -> {
      dispose();
      Game.startGame("medium");
    });
    hardButton.addActionListener(e -> {
      dispose();
      Game.startGame("hard");
    });
    easyButton.setMaximumSize(new Dimension(width / 2, height / 5));
    mediumButton.setMaximumSize(new Dimension(width / 2, height / 5));
    hardButton.setMaximumSize(new Dimension(width / 2, height / 5));
    easyButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    mediumButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    hardButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(easyButton);
    panel.add(mediumButton);
    panel.add(hardButton);
    add(panel);

    setVisible(true);
  }
}