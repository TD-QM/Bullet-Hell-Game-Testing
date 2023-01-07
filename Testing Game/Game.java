import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame {
  public static void main(String[] args) {
    Game game = new Game();
    game.setVisible(true);

    game.init(contentPane);

  }

  static JPanel contentPane = new JPanel();

  public Game() {
    setTitle("Test");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1000, 700);
    // Actual Bounds of the Game
    // Max X: 973
    // Max Y: 645

    contentPane.setBackground(new Color(255, 255, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);

    setContentPane(contentPane);
  }

  public void init(JPanel contentPane) {
    setLocationRelativeTo(null);

    setLayout(new GridLayout(1, 1, 0, 0));

    Screen s = new Screen(contentPane);

    add(s);

    setVisible(true);
  }

}
