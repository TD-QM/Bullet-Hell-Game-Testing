import java.awt.*;

public class PlayerBullet extends GameObject {

  private int dx, dy;
  private boolean active;

  public PlayerBullet(int x, int y, int width, int height, int dx, int dy, boolean active) {
    super(x, y, width, height);
    this.dx = dx;
    this.dy = dy;
    this.active = active;
  }

  public void tick() {
    this.x += dx;
    this.y += dy;
  }

  public void draw(Graphics g) {
    g.fillRect(x, y, width, height);
  }

  public void create(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void activate(){
    active = true;
  }

  public void deactivate(){
    active = false;
  }

  public boolean status(){
    return active;
  }

  public void setDy(int dy) {
    this.dy = dy;
  }

  public void setY(int y){
    this.y = y;
  }

}
