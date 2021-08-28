import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * The main class for default block that implements sprite, collidable and HitNotifier.
 *
 * @author Amir Gheriani 212938724
 */
public class DefaultBlock implements Block {
    private Rectangle rectangle;
    private Color color;
    private int hit;
    private ArrayList<HitListener> hitListeners;
    /**Constructor.
     *
     * @param rectangle the rectangle of the block.
     * @param hit the hit count of the block.
     * @param color the color of the block.
     */
    public DefaultBlock(Rectangle rectangle, int hit, Color color) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.hit = hit;
        this.hitListeners = new ArrayList<HitListener>();
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, color);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX(), dy = currentVelocity.getDY();
        if (hit > 0) {
            hit--;
        }
        Point upperLeft = this.rectangle.getUpperLeft();
        if (Math.round(collisionPoint.getX()) == upperLeft.getX()
                || Math.round(collisionPoint.getX()) == upperLeft.getX() + this.rectangle.getWidth()) {
            dx = -dx;
        }
        if (Math.round(collisionPoint.getY()) == upperLeft.getY()
                || Math.round(collisionPoint.getY()) == upperLeft.getY() + this.rectangle.getHeight()) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    @Override
    public void timePassed() {
        // This function is doing nothing right now.
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**Notify to the block listeners about a hit.
    *
    * @param hitter the ball that hit.
    */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    @Override
    public int getHitPoints() {
        return this.hit;
    }
}
