import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * The main class for rectangle.
 *
 * @author Amir Gheriani
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**Constructor.
     *
     * @param upperLeft the location of the top left corner.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }
    /**Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line.
     *
     * @return a (possibly empty) List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point upperRight, downLeft, downRight;
        List<Point> list = new ArrayList<Point>();
        List<Line> edges = new ArrayList<Line>();
        // Calculate the vertexes of the rectangle.
        upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        downRight = new Point(upperRight.getX(), downLeft.getY());
        // Add the edges to the list.
        edges.add(new Line(this.upperLeft, upperRight));
        edges.add(new Line(downLeft, downRight));
        edges.add(new Line(this.upperLeft, downLeft));
        edges.add(new Line(upperRight, downRight));
        // Add the intersection points.
        for (Line l : edges) {
            if (line.isIntersecting(l)) {
                list.add(line.intersectionWith(l));
            }
        }
        return list;
    }
    /** Return the width.
     *
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }
    /** Return the height.
    *
    * @return the height.
    */
    public double getHeight() {
        return this.height;
    }
    /** Return the upper-left point of the rectangle.
     *
     * @return the upper-left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /** Set the upper left point.
     *
     * @param point the new point.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = new Point(point.getX(), point.getY());
    }
    /**Draw the rectangle on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     * @param color the color to draw the rectangle with.
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
    }
    /**Draw the line of the rectangle on the given DrawSurface.
    *
    * @param surface the DrawSurface to draw on.
    * @param color the color to draw the rectangle with.
    */
    public void drawLineOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
    }
    /**Return if the point is in the rectangle.
     *
     * @param point the point.
     *
     * @return if the point is in the rectangle.
     */
    public boolean includePoint(Point point) {
        return (point.getX() >= this.upperLeft.getX() && point.getX() <= this.upperLeft.getX() + this.width
                && point.getY() >= this.upperLeft.getY() && point.getY() <= this.upperLeft.getY() + this.height);
    }
}
