import java.awt.*;

public class PlayerBullet extends Rectangle {

  private int dx, dy;

  public PlayerBullet(int x, int y, int width, int height, int dx, int dy) {
    setBounds(x, y, width, height);
    this.dx = dx;
    this.dy = dy;
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

  public void setDy(int dy) {
    this.dy = dy;
  }

}
