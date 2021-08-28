import biuoop.DrawSurface;
/**
 * The interface for fill style.
 *
 * @author Amir Gheriani 212938724
 */
public interface FillStyle {
    /**Draw a rectangle on the surface.
     *
     * @param d the surface.
     * @param x the x position.
     * @param y the y position.
     * @param width the width.
     * @param height the height.
     */
    void drawOn(DrawSurface d, int x, int y, int width, int height);
}
