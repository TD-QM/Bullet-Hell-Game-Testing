import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

  // This timer is to call in the actionPerformed function below
  Timer t = new Timer(10, this);

  // Creation of the Player class
  Player p = new Player(350, 600, 10, 10, 0, 0);
  PlayerBullet[] pbArr;
  boolean[] activeBullet;
  Enemy[] en;
  // Triangle test = new Triangle(300, 300, 310, 300, 305, 310);
  // EnemyBullet[][] enb = new EnemyBullet[2][3];

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

    pbArr = new PlayerBullet[20];
    activeBullet = new boolean[20];
    for (int i = 0; i < 20; i++) {
      pbArr[i] = new PlayerBullet(-1, -1, 2, 10, 0, 0);
    }
    for (int i = 0; i < 20; i++) {
      activeBullet[i] = false;
    }

    en = new Enemy[4];
    for (int i = 0; i < 4; i++) {
      if (i < 1) {
        en[i] = new Enemy(300, 200, 15, 15, 5, 0, Color.red);
      } else if (i < 2) {
        en[i] = new Enemy(300, 300, 15, 15, 4, 0, Color.orange);
      } else if (i < 3) {
        en[i] = new Enemy(300, 400, 15, 15, 3, 0, Color.yellow);
      } else if (i < 4) {
        en[i] = new Enemy(300, 500, 15, 15, 2, 0, Color.green);
      }
      en[i].spawn();
    }

    // EnemyBullet[][] enb = new EnemyBullet[20][10];

    // Starting the timer so that the game can detect input
    t.start();

  }

  // Functions for player movement

  // Boolean values are to check to see whether or not the player is moving in the opposite direction
  // This is so that if they're changing direction, releasing the opposite key won't cause them to come to a stop
  boolean up, down, left, right = false;
  // boolean focusUp, focusDown, focusLeft, focusRight = false;
  Action moveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (p.getX() <= 0) {
        p.setY(0);
      } else {
        p.setDy(-3);
      }
      up = true;
    }
  };
  // Action focusMoveUp = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     p.setDy(-1);
  //     focusUp = true;
  //   }
  // };
  Action stopUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!down) {
        p.setDy(0);
      }
      up = false;
    }
  };
  // Action focusStopUp = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     if (!focusDown) {
  //       p.setDy(0);
  //     }
  //     focusUp = false;
  //   }
  // };
  Action moveDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (p.getY() >= 690) {
        p.setY(690);
      } else {
        p.setDy(3);
      }
      down = true;
    }
  };
  // Action focusMoveDown = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     p.setDy(1);
  //     down = true;
  //   }
  // };
  Action stopDown = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!up) {
        p.setDy(0);
      }
      down = false;
    }
  };
  // Action focusStopDown = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     if (!focusUp) {
  //       p.setDy(0);
  //     }
  //     focusDown = false;
  //   }
  // };
  Action moveLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (p.getX() <= 0) {
        p.setX(0);
      } else {
        p.setDx(-3);
      }
      left = true;
    }
  };
  // Action focusMoveLeft = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     p.setDx(-1);
  //     up = true;
  //   }
  // };
  Action stopLeft = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!right) {
        p.setDx(0);
      }
      left = false;
    }
  };
  // Action focusStopLeft = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     if (!focusRight) {
  //       p.setDx(0);
  //     }
  //     focusLeft = false;
  //   }
  // };
  Action moveRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (p.getX() >= 690) {
        p.setX(690);
      } else {
        p.setDx(3);
      }
      right = true;
    }
  };
  // Action focusMoveRight = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     p.setDx(1);
  //     right = true;
  //   }
  // };
  Action stopRight = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      if (!left) {
        p.setDx(0);
      }
      right = false;
    }
  };
  // Action focusStopRight = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     if (!focusLeft) {
  //       p.setDx(0);
  //     }
  //     focusRight = false;
  //   }
  // };

  Action shootBullet = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Shoot");
      for (int i = 0; i < 20; i++) {
        if (!activeBullet[i]) {
          pbArr[i].create((int) p.getX() + 4, (int) p.getY());
          pbArr[i].setDy(-10);
          activeBullet[i] = true;
          break;
        }
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
    iMap.put(KeyStroke.getKeyStroke(key), pressedName);
    iMap.put(KeyStroke.getKeyStroke("released " + key), releasedName);
    aMap.put(pressedName, pressedAction);
    aMap.put(releasedName, releasedAction);

  }

  // Function that updates the screen as the game goes on
  public void actionPerformed(ActionEvent e) {

    p.tick();

    for (int i = 0; i < 20; i++) {
      pbArr[i].tick();
      if (pbArr[i].getY() <= -10) {
        pbArr[i].setDy(0);
        activeBullet[i] = false;
      }
    }

    for (int i = 0; i < en.length; i++) {
      for (int j = 0; j < en.length; j++) {
        if ((en[i].aliveCheck() == true)
            &&
            (pbArr[j].getX() >= en[i].getX()
                || pbArr[j].getX() + 3 >= en[i].getX())
            &&
            (pbArr[j].getX() <= en[i].getX() + en[i].getWidth()
                || pbArr[j].getX() + 3 <= en[i].getX() + en[i].getWidth())
            &&
            (pbArr[j].getY() >= en[i].getY()
                || pbArr[j].getY() + 10 >= en[i].getY())
            &&
            (pbArr[j].getY() <= en[i].getY() + en[i].getHeight()
                || pbArr[j].getY() + 10 <= en[i].getY() + en[i].getHeight())) {
          en[i].die();
          pbArr[j].setY(-10);
          activeBullet[j] = false;
          System.out.println("Hit");
          break;
        }
      }
      if (en[i].aliveCheck()) {
        en[i].tick();
      }
    }

    repaint();
  }

  // Removes and redraws the player and other objects to simulate movement
  public void paint(Graphics g) {

    g.clearRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.black);
    p.draw(g);

    // test.fill(g, Color.red);

    g.setColor(Color.blue);
    for (int i = 0; i < 20; i++) {
      pbArr[i].draw(g);
    }

    g.setColor(Color.red);
    for (int i = 0; i < en.length; i++) {
      if (en[i].aliveCheck()) {
        en[i].draw(g);
      }
    }

  }

}
