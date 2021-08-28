import biuoop.DrawSurface;
/**
 * The interface for animation.
 *
 * @author Amir Gheriani 212938724
 */
public interface Animation {
    /**Draw the animation frame on the surface.
     *
     * @param d the surface.
     */
    void doOneFrame(DrawSurface d);
    /**If the animation should stop.
     *
     * @return If the animation should stop.
     */
    boolean shouldStop();
}
