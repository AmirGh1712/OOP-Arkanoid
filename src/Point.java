import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The main class for point.
 *
 * @author Amir Gheriani
 */
public class Point {
    private double x;
    private double y;
    /**Constructor.
     *
     * @param x the first coordinate.
     * @param y the second coordinate.
     */
   public Point(double x, double y) {
        this.x = x;
        this.y = y;
   }
   /**Calculate the distance to a point.
     *
     * @param other another point.
     *
     * @return the distance of this point to the other point.
     */
   public double distance(Point other) {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
   }
   /**Check if two points are equal.
     *
     * @param other another point.
     *
     * @return if the points are equal.
     */
   public boolean equals(Point other) {
       return (this.x == other.x) && (this.y == other.y);
   }
   /**Return the x value.
     *
     * @return the x value.
     */
   public double getX() {
        return this.x;
   }
   /**Return the y value.
     *
     * @return the y value.
     */
   public double getY() {
        return this.y;
   }
   /**Draw the point on the given DrawSurface.
    *
    * @param surface the DrawSurface to draw on.
    * @param color the color of the point.
    * @param radius the radius of the point.
    */
   public void drawOn(DrawSurface surface, Color color, int radius) {
       surface.setColor(color);
       surface.fillCircle((int) this.getX(), (int) this.getY(), radius);
   }
}