import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Rectangle {

  private int dx, dy, hp;
  private boolean alive, invincible;
  private Color color;

  Timer iFrameTimer = new Timer();
  TimerTask iFrames = new TimerTask(){
    public void run(){
      invincible = false;
      color = Color.black;
    }
  };

  public Player(int x, int y, int width, int height, int dx, int dy, int hp) {
    setBounds(x, y, width, height);
    this.dx = dx;
    this.dy = dy;
    this.hp = hp;
    alive = true;
    invincible = false;
  }

  public void tick() {

    // Updating the position of the player
    
    // Checking to see if the player is within the bounds of the game
    // If they're not, then set them to be
    if(x > 961){
      x = 961;
    } else if(x < 0){
      x = 0;
    } else {
      x += dx;
    }

    if(y > 633){
      y = 633;
    } else if(y < 0){
      y = 0;
    } else {
      y += dy;
    }

    // System.out.println("X: " + x);
    // System.out.println("Y: " + y);

  }

  public void draw(Graphics g) {

    g.setColor(color);
    g.fillRect(x, y, width, height);

    g.setColor(Color.white);
    g.fillRect(x + width/4, y + height/4, width/2, height/2);
  }

  public void setDx(int dx) {
    this.dx = dx;
  }

  public void setDy(int dy) {
    this.dy = dy;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean hit(){
    if(!invincible){
      hp--;
      invincible = true;
      color = Color.lightGray;

      iFrameTimer.schedule(iFrames, 3000);
      iFrames = new TimerTask(){
        public void run(){
          invincible = false;
        }
      };
      
    }

    if(hp <= 0){
      alive = false;
    }
    return alive;
  }

  public boolean hasIFrames(){
    return invincible;
  }

  public int getHP(){
    return hp;
  }

}
