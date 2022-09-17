import java.awt.*;

public class Player extends Rectangle {

  private int dx, dy;

  public Player(int x, int y, int width, int height, int dx, int dy) {
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

}
