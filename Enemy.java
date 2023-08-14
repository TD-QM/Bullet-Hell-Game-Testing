import java.awt.*;

public class Enemy extends GameObject{

  protected int x, y, width, height, dx, dy;
  protected int hp;
  protected Color color;
  protected boolean alive;

  public Enemy(int x, int y, int width, int height, int dx, int dy, int hp, Color color) {
    super();
    this.color = color;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.dx = dx;
    this.dy = dy;
    this.hp = hp;
    alive = true;
  }

  public void tick() {
    // This is to just make the enemy bounce off the walls if they reach the edge
    if ((x <= 0) || ((x + width) >= 958)) {
      dx = -dx;
    }
    if ((y <= 0) || ((y + height) >= 645)) {
      dy = -dy;
    }

    x += dx;
    y += dy;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(x, y, width, height);
  }

  public void setDx(int dx) {
    this.dx = dx;
  }

  public void setDy(int dy) {
    this.dy = dy;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public void spawn() {
    alive = true;
  }

  public void hit(){
    hp--;

    if (hp <= 0){
      die();

    } else if(hp <= 1){ // Every time the enemy is hit, change its color and make it faster

      if(dx > 0){
        dx += 1;
      } else {
        dx -= 1;
      }
      if(dy > 0){
        dy += 1;
      } else {
        dy -= 1;
      }

      color = Color.red;

    } else if(hp <= 2){

      if(dx > 0){
        dx += 1;
      } else {
        dx -= 1;
      }
      if(dy > 0){
        dy += 1;
      } else {
        dy -= 1;
      }

      color = Color.orange;

    } else if(hp <= 3){

      if(dx > 0){
        dx += 1;
      } else {
        dx -= 1;
      }
      if(dy > 0){
        dy += 1;
      } else {
        dy -= 1;
      }

      color = Color.yellow;

    }
  }

  public void die() {
    alive = false;
  }

  public boolean status() {
    return alive;
  }

}
