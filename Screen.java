/*
 * Importing a bunch of crap for this thing to work, mainly the Graphics for shapes and the ActionEvent/Listener for player movement
 */
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

/*
 * These are to update the screen that the player sees
 */
import javax.swing.*;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

  // This timer is to call in the actionPerformed function below
  Timer timer = new Timer(10, this);

  // Creation of the Player class
  Player p = new Player(480, 600, 12, 12, 0, 0, 10);
  PlayerBullet[] pbArr;
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

    /*
     * These other keybinds are here because I wanted to try having a "focus" mechanic
     *    similar to Touhou in which the player reduces their movement speed to have more 
     *    accurate dodges. Didn't really work though. A ton of bugs cropped up.
     */
    // addKeyBind(contentPane, "shift W", "FocusUp", focusMoveUp, focusStopUp);
    // addKeyBind(contentPane, "shift S", "FocusDown", focusMoveDown, focusStopDown);
    // addKeyBind(contentPane, "shift A", "FocusLeft", focusMoveLeft, focusStopLeft);
    // addKeyBind(contentPane, "shift D", "FocusRight", focusMoveRight, focusStopRight);
    addKeyBind(contentPane, "SPACE", "Shoot", shootBullet, stopBullet);

    // Making an array for the player's bullets
    pbArr = new PlayerBullet[7];
    for (int i = 0; i < pbArr.length; i++) {
      pbArr[i] = new PlayerBullet(-1, -1, 2, 10, 0, 0, false);
    }

    // Making an array for the enemies
    en = new Enemy[50];
    
    // (int) ( (Math.random()*3) + 7 )
    // (int) ( (Math.random()*20) )
    // (int) ( (Math.random()*943) + 15 )


    for(int i = 0; i < 50; i++){
      if(i > 9){
        en[i] = new Enemy( (int) ( (Math.random()*943) + 15), (int) ( (Math.random()*200) ), 15, 15, -(int) ( (Math.random()*3) + 1), (int) ( (Math.random()*3) + 1), 4, Color.green);
      } else if(i > 19){
        en[i] = new Enemy( (int) ( (Math.random()*943) + 15), (int) ( (Math.random()*200) ), 15, 15, (int) ( (Math.random()*3) + 1), -(int) ( (Math.random()*3) + 1), 4, Color.green);
      } else if(i > 29){
        en[i] = new Enemy( (int) ( (Math.random()*943) + 15), (int) ( (Math.random()*200) ), 15, 15, -(int) ( (Math.random()*3) + 1), -(int) ( (Math.random()*3) + 1), 4, Color.green);
      } else {
        en[i] = new Enemy( (int) ( (Math.random()*943) + 15), (int) ( (Math.random()*200) ), 15, 15, (int) ( (Math.random()*3) + 1), (int) ( (Math.random()*3) + 1), 4, Color.green);
      }
      
    }


    // EnemyBullet[][] enb = new EnemyBullet[20][10];

    // Starting the timer so that the game can detect input
    timer.start();

  }

  // Functions for player movement

  // Boolean values are to check to see whether or not the player is moving in the opposite direction
  // This is so that if they're changing direction, releasing the opposite key won't cause them to come to a stop
  boolean up, down, left, right = false;

  /*
   * More deleted ideas. Sigh.
   */
  // boolean focusUp, focusDown, focusLeft, focusRight = false;

  // Adding a new AbstractAction that moves the player up (or just sets their dy to -3)
  Action moveUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

      // Code for if the player is at the very top of the screen
      if (p.getY() <= 0) {
        p.setDy(0); // Sets dy to zero, so they can't move
      } else {        
        p.setDy(-3); // If they're not at the top of the screen, then they move up
      }

      // Setting up to true for the stopDown action
      up = true;
    }
  };
  // Action focusMoveUp = new AbstractAction() {
  //   public void actionPerformed(ActionEvent e) {
  //     p.setDy(-1);
  //     focusUp = true;
  //   }
  // };

  // Adding a new AbstractAction that stops the player from moving up
  Action stopUp = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

      // Checks to see if the player is currently moving down
      // 
      if (!down) {
        p.setDy(0); // If they're not moving down, then set the dy to zero
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
      if (p.getY() >= 633) {
        p.setDy(0);
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
        p.setDx(0);
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
      if (p.getX() >= 961) {
        p.setDx(0);
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
      for (int i = 0; i < pbArr.length; i++) {
        if (!pbArr[i].activeCheck()) {
          pbArr[i].create((int) p.getX() + 4, (int) p.getY());
          pbArr[i].setDy(-10);
          pbArr[i].activate();
          break;
        }
      }
    }
  };
  Action stopBullet = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      // Literally does nothing lmfao
      // It's here to have a stop action for the addKeyBind function
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

    // Ticks the player by one, incrementing their movement depending on what key they're currently holding
    p.tick();

    /*  
     * This is just some code for the Player's Bullets. It makes them go zoom up the screen.
     * Once they're out of sight, they stop moving and become inactive.
     */
    for (int i = 0; i < pbArr.length; i++) {
      if (pbArr[i].activeCheck()){
        pbArr[i].tick();
        if (pbArr[i].getY() <= -10) {
          pbArr[i].deactivate();
        }
      }
    }

    /*
     * Oh boy, I love complicated things
     * So this entire mess of code checks every single bullet to every single alive enemy. Every. Single. Frame.
     * I'm not entirely sure if there's a more optimal way to make this code. If there is, I don't know it.
     * 
     * i is for the enemy array, j is for the bullet array
     */
    for (int i = 0; i < en.length; i++) {
      for (int j = 0; j < pbArr.length; j++) {
        if ((en[i].aliveCheck() == true) // Check to see if the enemy in the enemyArray is alive first of all. Shaves off some iterations
            &&
            pbArr[j].activeCheck() // Check to see if the bullet is active or not. Also helps to shave off some iterations
            &&
            pbArr[j].getX() >= en[i].getX() // All of this other garbage is basically just checking if the bullet and the enemy overlap in the 2d space
            &&
            pbArr[j].getX() + 3 <= en[i].getX() + en[i].getWidth()
            &&
            pbArr[j].getY() >= en[i].getY()
            &&
            pbArr[j].getY() + 10 <= en[i].getY() + en[i].getHeight()) {
          
          // If all of these horrid checks pass, then hit the enemy
          en[i].hit(); 

          // Deactivate the bullet
          pbArr[j].deactivate();

          // This one is just for testing purposes to see if the enemy actually got hit or not
          System.out.println("Hit");

          // Break out of the check for bullets
          break;
        }
      }

    /*
     * It's time for needlessly complicated and inefficient things part 2!
     */
    for (int j = 0; j < en.length; j++) {
      if ((en[j].aliveCheck() == true) // Check to see if the enemy in the enemyArray is alive first of all. Shaves off some iterations
          &&
          p.getX() + 3 >= en[j].getX() + 2 // All of this other garbage is basically just checking if the bullet and the enemy overlap in the 2d space
          &&
          p.getX() + 9 <= en[j].getX() + 10
          &&
          p.getY() + 3 >= en[j].getY() + 2
          &&
          p.getY() + 9 <= en[j].getY() + 10) {
        
        
        // Hit the player
        p.hit();
        
        // This one is necessary to see if the player actually got hit or not. Also displays their hp on the terminal
        System.out.println("Player Hit");
        System.out.println("Player HP: " + p.getHP());

        // Break out of the check
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

    g.setColor(Color.blue);
    for (int i = 0; i < pbArr.length; i++) {
      if (pbArr[i].activeCheck()){
        pbArr[i].draw(g);
      }
    }

    for (int i = 0; i < en.length; i++) {
      if (en[i].aliveCheck()) {
        en[i].draw(g);
      }
    }

  }

}
