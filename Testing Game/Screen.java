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

  // Functions for player movement

  // Boolean values are to check to see whether or not the player is moving in the opposite direction
  // This is so that if they're changing direction, releasing the opposite key won't cause them to come to a stop
  boolean up, down, left, right = false;
  Action moveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(-4);
      up = true;
    }
  };
  Action stopUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!down) {
        p.setDy(0);
      }
      up = false;
    }
  };
  Action moveDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(4);
      down = true;
    }
  };
  Action stopDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!up) {
        p.setDy(0);
      }
      down = false;
    }
  };
  Action moveLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(-4);
      left = true;
    }
  };
  Action stopLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!right) {
        p.setDx(0);
      }
      left = false;
    }
  };
  Action moveRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(4);
      right = true;
    }
  };
  Action stopRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!left) {
        p.setDx(0);
      }
      right = false;
    }
  };

  // Function for adding keybinds to whenever a key is held or released
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

  // Function that updates the screen as the game goes on
  public void actionPerformed(ActionEvent e) {
    p.tick();
    repaint();
  }

  // Removes and redraws the player and other objects to simulate movement
  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());

    p.draw(g);
  }

}
