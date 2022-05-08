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
  PlayerBullet pb = new PlayerBullet(-1, -1, 2, 10, 0, 0);

  // Constructor for the Screen Class
  public Screen(JPanel contentPane) {

    setFocusable(true);

    // Adding the keybinds for movement
    addKeyBind(contentPane, "W", "Up", moveUp, stopUp);
    addKeyBind(contentPane, "S", "Down", moveDown, stopDown);
    addKeyBind(contentPane, "A", "Left", moveLeft, stopLeft);
    addKeyBind(contentPane, "D", "Right", moveRight, stopRight);
    // addKeyBind(contentPane, "shift W", "FocusUp", focusMoveUp, focusStopUp);
    // addKeyBind(contentPane, "shift S", "FocusDown", focusMoveDown, focusStopDown);
    // addKeyBind(contentPane, "shift A", "FocusLeft", focusMoveLeft, focusStopLeft);
    // addKeyBind(contentPane, "shift D", "FocusRight", focusMoveRight, focusStopRight);
    addKeyBind(contentPane, "SPACE", "Shoot", shootBullet, stopBullet);

    // Starting the timer so that the game can detect input
    t.start();

  }

  // Functions for player movement

  // Boolean values are to check to see whether or not the player is moving in the opposite direction
  // This is so that if they're changing direction, releasing the opposite key won't cause them to come to a stop
  boolean up, down, left, right = false;
  boolean focusUp, focusDown, focusLeft, focusRight = false;
  Action moveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(-3);
      up = true;
    }
  };
  Action focusMoveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(-1);
      focusUp = true;
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
  Action focusStopUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!focusDown) {
        p.setDy(0);
      }
      focusUp = false;
    }
  };
  Action moveDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(3);
      down = true;
    }
  };
  Action focusMoveDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDy(1);
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
  Action focusStopDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!focusUp) {
        p.setDy(0);
      }
      focusDown = false;
    }
  };
  Action moveLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(-3);
      left = true;
    }
  };
  Action focusMoveLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(-1);
      up = true;
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
  Action focusStopLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!focusRight) {
        p.setDx(0);
      }
      focusLeft = false;
    }
  };
  Action moveRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(3);
      right = true;
    }
  };
  Action focusMoveRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      p.setDx(1);
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
  Action focusStopRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!focusLeft) {
        p.setDx(0);
      }
      focusRight = false;
    }
  };

  //
  boolean active = false;
  Action shootBullet = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Shoot");
      if (!active) {
        pb.create((int) p.getX() + 3, (int) p.getY());
        pb.setDy(-10);
        active = true;
      }
    }
  };
  Action stopBullet = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      // Literally does nothing lmfao
    }
  };

  // Function for adding keybinds to whenever a key is held or released
  private void addKeyBind(JComponent contentPane, String key, String actionName, Action pressedAction,
      Action releasedAction) {
    InputMap iMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap aMap = contentPane.getActionMap();

    String pressedName = actionName + " pressed";
    String releasedName = actionName + " released";

    if (actionName.length() >= 6 && actionName.substring(0, 5).equals("Focus")) {
      iMap.put(KeyStroke.getKeyStroke(key), pressedName);
      iMap.put(KeyStroke.getKeyStroke("released " + key), releasedName);
      iMap.put(KeyStroke.getKeyStroke("released shift"), releasedName);
      aMap.put(pressedName, pressedAction);
      aMap.put(releasedName, releasedAction);
    } else {
      iMap.put(KeyStroke.getKeyStroke(key), pressedName);
      iMap.put(KeyStroke.getKeyStroke("released " + key), releasedName);
      aMap.put(pressedName, pressedAction);
      aMap.put(releasedName, releasedAction);
    }
  }

  // Function that updates the screen as the game goes on
  public void actionPerformed(ActionEvent e) {
    p.tick();
    pb.tick();
    if (pb.getY() < -10) {
      pb.setDy(0);
      active = false;
    }

    repaint();
  }

  // Removes and redraws the player and other objects to simulate movement
  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());

    p.draw(g);
    pb.draw(g);
  }

}
