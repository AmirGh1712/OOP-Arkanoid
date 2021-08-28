/**
 * The main class for velocity.
 *
 * @author Amir Gheriani 212938724
 */
public class Velocity {
    private double dx;
    private double dy;
    /**Constructor.
     *
     * @param dx the x velocity.
     * @param dy the y velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**Take a point with position (x,y) and return a new point
     * with position (x + dx, y + dy).
     *
     * @param p the point to apply on.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**Return the x velocity.
     *
     * @return the x velocity.
     */
    public double getDX() {
        return this.dx;
    }
    /**Return the y velocity.
     *
     * @return the y velocity.
     */
    public double getDY() {
        return this.dy;
    }
    /**Set the x velocity.
     *
     * @param deltaX the new x velocity.
     */
    public void setDX(double deltaX) {
        this.dx = deltaX;
    }
    /**Set the y velocity.
     *
     * @param deltaY the new y velocity.
     */
    public void setDY(double deltaY) {
        this.dy = deltaY;
    }
    /**Create a velocity from an angle and speed.
     *
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double dx = speed * Math.sin(Math.toRadians(angle));
      double dy = -1 * speed * Math.cos(Math.toRadians(angle));
      return new Velocity(dx, dy);
    }
    /**Return the total speed.
     *
     * @return the total speed.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}