import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

  // This timer is to call in the actionPerformed function below
  Timer t = new Timer(0, this);

  // Creation of the Player class
  Player p = new Player(350, 600, 10, 10, 0, 0);

  // Constructor for the Screen Class
  public Screen(JPanel contentPane) {

    setFocusable(true);

    // Adding the keybinds for movement
    addKeyBind(contentPane, "W", "Up", moveUp, stopUp);
    addKeyBind(contentPane, "S", "Down", moveDown, stopDown);
    addKeyBind(contentPane, "A", "Left", moveLeft, stopLeft);
    addKeyBind(contentPane, "D", "Right", moveRight, stopRight);

    // Starting the timer so that the game can detect input
    t.start();

  }

  // Functions for player movement ad also stopping the player when they stop holding a key
  /* 
  TODO: Figure out what's causing the player to "lag" a bit sometimes when they change 
    directions after only holding down the opposite key for a short time. This doesn't happen
    when the player has held the opposite direction for a long time (about a full second)
  */
  Action moveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(-1);
    }
  };
  Action stopUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(0);
    }
  };
  Action moveDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(1);
    }
  };
  Action stopDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(0);
    }
  };
  Action moveLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(-1);
    }
  };
  Action stopLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(0);
    }
  };
  Action moveRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(1);
    }
  };
  Action stopRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(0);
    }
  };

  private void addKeyBind(JComponent contentPane, String key, String actionName, Action pressedAction,
      Action releasedAction) {
    InputMap iMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap aMap = contentPane.getActionMap();

    String pressedName = actionName + " pressed";
    String releasedName = actionName + " released";

    iMap.put(KeyStroke.getKeyStroke(key), pressedName);
    iMap.put(KeyStroke.getKeyStroke("released " + key), releasedName);
    aMap.put(pressedName, pressedAction);
    aMap.put(releasedName, releasedAction);
  }

  public void actionPerformed(ActionEvent e) {

    p.tick();
    repaint();

  }

  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());

    p.draw(g);
  }

}
