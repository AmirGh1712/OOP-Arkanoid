import java.util.ArrayList;
import java.util.List;
/**
 * The main class for game environment.
 *
 * @author Amir Gheriani 212938724
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**Add the given collidable to the environment.
     *
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**Remove the given collidable from the environment.
    *
    * @param c the collidable.
    */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the object moving line.
     *
     * @return the info of the closest collision.
     */
   public CollisionInfo getClosestCollision(Line trajectory) {
       Point closestPoint = null;
       List<Point> collisionPoints = new ArrayList<Point>();
       List<Collidable> collisionObjects = new ArrayList<Collidable>();
       double dis;
       List<Collidable> list = new ArrayList<Collidable>(this.collidables);
       Collidable closestObject = null;
       // Find all collisions.
       for (Collidable collidable : list) {
           Point point = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
           if (point != null) {
               collisionPoints.add(point);
               collisionObjects.add(collidable);
           }
       }
       // If there is no collision return null.
       if (collisionPoints.isEmpty()) {
           return null;
       }
       // Find the closest.
       closestPoint = collisionPoints.get(0);
       closestObject = collisionObjects.get(0);
       dis = trajectory.start().distance(collisionPoints.get(0));
       for (int i = 1; i < collisionPoints.size(); i++) {
           if (trajectory.start().distance(collisionPoints.get(i)) < dis) {
               closestPoint = collisionPoints.get(i);
               closestObject = collisionObjects.get(i);
               dis = trajectory.start().distance(collisionPoints.get(i));
           }
       }
       return new CollisionInfo(closestPoint, closestObject);
   }

}