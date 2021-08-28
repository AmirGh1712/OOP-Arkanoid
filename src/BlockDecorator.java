import java.util.ArrayList;
import java.util.List;
/**
 * The abstract class for block decorator.
 *
 * @author Amir Gheriani 212938724
 */
public abstract class BlockDecorator implements Block  {
    private Block decorator;
    private ArrayList<HitListener> hitListeners;
    /**Constructor.
    *
    * @param decorator the decorator.
    */
    public BlockDecorator(Block decorator) {
        this.decorator = decorator;
        this.hitListeners = new ArrayList<HitListener>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.decorator.getCollisionRectangle();
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = this.decorator.hit(hitter, collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        return velocity;
    }
    @Override
    public void timePassed() {
        this.decorator.timePassed();
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
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
        return this.decorator.getHitPoints();
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
}
