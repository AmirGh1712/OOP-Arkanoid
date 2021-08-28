import java.awt.Color;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;

/**
 * The main class for line.
 *
 * @author Amir Gheriani 212938724
 */
public class Line {
    private Point start;
    private Point end;
    private double gradient;
    private double yIntercept;
    private boolean verticalLine;
    /**Constructor.
     *
     * @param start the start point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {
        // Put the first point in start.
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        this.verticalLine = false;
        this.calculateEquation();
    }
    /**Constructor.
     *
     * @param x1 the value x of one of the line's points.
     * @param y1 the value y of one of the line's points.
     * @param x2 the value x of one of the line's points.
     * @param y2 the value y of one of the line's points.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // Put the first point in start.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.verticalLine = false;
        this.calculateEquation();
    }
    /**Calculate the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**Calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }
    /**Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }
    /**Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**Check if two lines intersect.
     *
     * @param other another line.
     *
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double deltaGradient, deltaYIntercept, x;
        if (!this.verticalLine && !other.verticalLine) {
            // Both lines are not vertical.
            if (this.gradient == other.gradient) {
                return false;
            }
            deltaGradient = this.gradient - other.gradient;
            deltaYIntercept = other.yIntercept - this.yIntercept;
            x = deltaYIntercept / deltaGradient;
            if (x >= this.minX() && x <= this.maxX() && x >= other.minX() && x <= other.maxX()) {
                return true;
            }
            return false;
        } else if (this.verticalLine && other.verticalLine) {
            // Both lines are vertical.
            return false;
        } else if (this.verticalLine) {
            // This line is vertical.
            double y = this.start.getX() * other.gradient + other.yIntercept;
            x = this.start.getX();
            return y >= this.minY() && y <= this.maxY() && x >= other.minX() && x <= other.maxX();
        } else {
            // The other line is vertical.
            double y = other.start.getX() * this.gradient + this.yIntercept;
            x = other.start.getX();
            return y >= other.minY() && y <= other.maxY() && x >= this.minX() && x <= this.maxX();
        }
    }
    /**Returns the minimum x of the line.
     *
     * @return the minimum x of the line.
     */
    public double minX() {
        return  Math.min(this.start.getX(), this.end.getX());
    }
    /**Returns the minimum y of the line.
    *
    * @return the minimum y of the line.
    */
    public double minY() {
        return  Math.min(this.start.getY(), this.end.getY());
    }
    /**Returns the maximum x of the line.
    *
    * @return the maximum x of the line.
    */
   public double maxX() {
       return  Math.max(this.start.getX(), this.end.getX());
   }
   /**Returns the maximum y of the line.
   *
   * @return the maximum y of the line.
   */
   public double maxY() {
       return  Math.max(this.start.getY(), this.end.getY());
   }
    /**Calculate the intersect between two lines.
     *
     * @param other another line.
     *
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double deltaGradient, deltaYIntercept, x, y;
        if (!this.isIntersecting(other)) {
            return null;
        }
        if (!this.verticalLine && !other.verticalLine) {
            // Both lines are not vertical.
            deltaGradient = this.gradient - other.gradient;
            deltaYIntercept = other.yIntercept - this.yIntercept;
            x = deltaYIntercept / deltaGradient;
            y = this.gradient * x + this.yIntercept;
            return new Point(x, y);
        } else if (this.verticalLine) {
            return other.intersectionWithVertical(this);
        }
        return this.intersectionWithVertical(other);
    }
    /**Calculate the intersect between this line and a vertical line.
     * (When the lines are intersecting).
     *
     * @param vertical the vertical line.
     *
     * @return the intersection point.
     */
    public Point intersectionWithVertical(Line vertical) {
        double y = vertical.start.getX() * this.gradient + this.yIntercept;
        return new Point(vertical.start.getX(), y);
    }
    /**Check if two lines are equal.
     *
     * @param other another line.
     *
     * @return if the lines are equal.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }
    /**Calculate the gradient and the y intercept of the line.
     */
    public void calculateEquation() {
        double deltaX, deltaY;
        // Calculate the delta x and y of the points
        deltaX = this.start.getX() - this.end.getX();
        deltaY = this.start.getY() - this.end.getY();
        if (deltaX != 0) {
        // Calculate the gradient of the line.
        this.gradient = (double) deltaY / deltaX;
        // Calculate the y intercept of the line.
        this.yIntercept = (double) this.start.getY() - this.gradient
               * this.start.getX();
        } else {
           this.verticalLine = true;
        }
    }
    /**Draw the Line on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     * @param color the color of the point.
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.drawLine((int) this.start().getX(), (int) this.start().getY(),
                (int) this.end().getX(), (int) this.end().getY());
    }
    /** Create a random line.
     *
     * @param surfaceWidth the width of surface to create in the line.
     * @param surfaceHeight the height of surface to create in the line.
     *
     * @return the random line.
     */
   public static Line randomLine(int surfaceWidth, int surfaceHeight) {
       Random rand = new Random();
       int x1 = rand.nextInt(surfaceWidth) + 1; // get integer in range 1-WIDTH
       int y1 = rand.nextInt(surfaceHeight) + 1; // get integer in range 1-HEIGHT
       int x2 = rand.nextInt(surfaceWidth) + 1; // get integer in range 1-WIDTH
       int y2 = rand.nextInt(surfaceHeight) + 1; // get integer in range 1-HEIGHT
       return new Line(x1, y1, x2, y2);
   }
   /** If this line does not intersect with the rectangle, return null.
    * Otherwise, return the closest intersection point to the
    * start of the line.
    *
    * @param rect the intersection rectangle.
    *
    * @return the closest intersection point to the start of the line.
    */
   public Point closestIntersectionToStartOfLine(Rectangle rect) {
       Point closest;
       double dis;
       List<Point> intersections = rect.intersectionPoints(this);
       // Check if there is intersection.
       if (intersections.isEmpty()) {
           return null;
       }
       // Find the closest intersection.
       closest = intersections.get(0);
       dis = this.start.distance(closest);
       for (Point point : intersections) {
           if (dis > this.start.distance(point)) {
               closest = point;
               dis = this.start.distance(point);
           }
       }
       return closest;
   }
}