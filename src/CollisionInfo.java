/**
 * The main class for collision info.
 *
 * @author Amir Gheriani 212938724
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /** Constructor.
     *
     * @param collisionPoint the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint  = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }
    /**Returns the collidable object involved in the collision.
     *
     * @return the collision object.
     */
    public Collidable collisionObject() {
       return this.collisionObject;
    }
}