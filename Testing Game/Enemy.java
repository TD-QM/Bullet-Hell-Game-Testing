import java.awt.*;

public class Enemy extends Triangle {

  private int x, y, width, height, dx, dy;
  private Color color;
  private boolean alive;

  public Enemy(int x, int y, int width, int height, int dx, int dy, Color color) {
    super(x, y, x + width, y, x + width / 2, y + height);
    this.color = color;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.dx = dx;
    this.dy = dy;
  }

  public void tick() {
    if ((x <= 0) || ((x + width * 2) >= 700)) {
      dx = -dx;
    }
    if ((y <= 0) || ((y + height * 2) >= 700)) {
      dy = -dy;
    }
    x += dx;
    y += dy;
    super.setPointA(x, y);
    super.setPointB(x + width, y);
    super.setPointC(x + width / 2, y + height);
  }

  public void draw(Graphics g) {
    super.fill(g, color);
    // super.sketch(g);
  }

  public void setDx(int dx) {
    this.dx = dx;
  }

  public void setDy(int dy) {
    this.dy = dy;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void spawn() {
    alive = true;
  }

  public void die() {
    alive = false;
  }

  public boolean aliveCheck() {
    return alive;
  }

}
