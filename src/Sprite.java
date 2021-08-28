import biuoop.DrawSurface;
/**
 * The interface for sprit.
 *
 * @author Amir Gheriani
 */
public interface Sprite {
    /**Draw the sprite to the screen.
     *
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);
    /**Notify the sprite that time has passed.
     */
    void timePassed();
}