import java.awt.Color;

import biuoop.DrawSurface;
/**
 * The main class for background.
 *
 * @author Amir Gheriani 212938724
 */
public class Background implements Sprite {
    private Color color;
    /**Constructor.
     *
     * @param color the color of the background.
     */
    public Background(Color color) {
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }
    @Override
    public void timePassed() {
        // nothing
    }
}
