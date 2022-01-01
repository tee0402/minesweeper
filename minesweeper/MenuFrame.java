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
    add(panel);

    JLabel label = new JLabel("Minesweeper");
    label.setFont(new Font("Verdana", Font.BOLD, 50));
    label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(label);

    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    easy.addActionListener(e -> {
      dispose();
      Game.startGame("easy");
    });
    medium.addActionListener(e -> {
      dispose();
      Game.startGame("medium");
    });
    hard.addActionListener(e -> {
      dispose();
      Game.startGame("hard");
    });
    easy.setMaximumSize(new Dimension(width / 2, height / 5));
    medium.setMaximumSize(new Dimension(width / 2, height / 5));
    hard.setMaximumSize(new Dimension(width / 2, height / 5));
    easy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    medium.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    hard.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    panel.add(easy);
    panel.add(medium);
    panel.add(hard);

    setVisible(true);
  }
}
