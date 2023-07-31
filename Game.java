import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * TO DO:
 * 
 * Convert Enemy array into a Linked List
 *    With the current system, all enemies dead or alive are processed every tick
 *    With a Linked List, dead enemies can be removed from the list and not processed
 *    Also allows for an amorphous number of enemies instead of a static number
 * 
 * Convert PlayerBullet array into a Linked List
 *    Same as the bullet point before this one, except the main thing to notice is that there can be more than just 10 bullets on screen at once
 * 
 * Set a delay between each bullet
 *    Currently, there's an unintended bug where the player can hold the spacebar down and continuously shoot at a rapid pace
 *    This stops if they move
 * 
 * Add in other enemy types
 *    Triangles are cool
 *      Have these shoot bullets at the player, but they can't travel along the y axis
 * 
 * Add in an HP counter on-screen
 *    Preferrably in the background where the player and enemies are drawn over it rather than it hiding them
 * 
 * Add a death state
 *    If hp=0
 *    Maybe pause all of the enemies on-screen and add a little animation of the square dying
 * 
 * Add a win state
 *    If EnemyLinkedList is empty
 *    Idk how to reward the player
 *    Maybe add a score counter on-screen that decreases as the player takes longer
 *        Add points for when the player kills an enemy or wins
 *        Put their score+intials into a txt file that keeps track of the top 10 or so scores (Research this!)
 */

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
