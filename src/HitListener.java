/**
 * The interface for HitListener.
 *
 * @author Amir Gheriani
 */
public interface HitListener {
    /** This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}