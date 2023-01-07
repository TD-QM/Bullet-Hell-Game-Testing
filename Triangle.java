
// Triangle.java
// Class Definition
// Uses overloaded constructors
// User-defined class with graphics
import java.awt.*;

public class Triangle {
  // instance variables
  private Point pointA, pointB, pointC;

  // constructor taking 3 Point object arguments
  public Triangle(Point a, Point b, Point c) {
    pointA = a;
    pointB = b;
    pointC = c;

  }

  // constructor taking 6 integer arguments
  public Triangle(int ax, int ay, int bx, int by, int cx, int cy) {
    pointA = new Point(ax, ay);
    pointB = new Point(bx, by);
    pointC = new Point(cx, cy);
  }

  // constructor taking 4 integer arguments
  public Triangle(int x, int y, int w, int h) {
    pointA = new Point(x, y);
    pointB = new Point(x + w, y);
    pointC = new Point(x + w/2, y + h);
  }

  // set the Points
  public void setPointA(Point a) {
    pointA = a;
  }

  public void setPointA(int x, int y) {
    pointA.x = x;
    pointA.y = y;
  }

  public void setPointB(Point b) {
    pointB = b;
  }

  public void setPointB(int bx, int by) {
    pointB.x = bx;
    pointB.y = by;
  }

  public void setPointB(int x, int y, int w, int h) {
    pointB.x = x + w;
    pointB.y = y - h / 2;
  }

  public void setPointC(Point c) {
    pointC = c;
  }

  public void setPointC(int cx, int cy) {
    pointC.x = cx;
    pointC.y = cy;
  }

  public void setPointC(int x, int y, int w, int h) {
    pointC.x = x + w;
    pointC.y = y + h / 2;
  }

  // get coordinates
  public int getX() {
    return pointA.x;
  }

  public int getY() {
    return pointA.y;
  }

  public int getW() {
    return pointC.x - pointA.x;
  }

  public int getH() {
    return (pointC.y - pointB.y);
  }

  public String toString() {
    return "The triangle is defined by ( x1, y1 ) = ( " + this.pointA.x + ", " + this.pointA.y + " ), ( x2, y2 ) = ( "
        + this.pointB.x + ", " + this.pointB.y + " ) and ( x3, y3 ) = ( " + this.pointC.x + ", " + this.pointC.y + " )";
  }

  public void sketch(Graphics g) {
    g.setColor(Color.orange);
    g.drawLine(pointA.x, pointA.y, pointB.x, pointB.y);
    g.drawLine(pointA.x, pointA.y, pointC.x, pointC.y);
    g.drawLine(pointB.x, pointB.y, pointC.x, pointC.y);
  }

  public void fill(Graphics g, Color shade) {
    Polygon p;
    p = new Polygon();
    p.addPoint(pointA.x, pointA.y);
    p.addPoint(pointB.x, pointB.y);
    p.addPoint(pointC.x, pointC.y);
    p.addPoint(pointA.x, pointA.y);
    g.setColor(shade);
    g.fillPolygon(p);
  }
}
