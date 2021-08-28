import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for color fill style.
 *
 * @author Amir Gheriani
 */
public class ColorFillStyle implements FillStyle {
    private Color color;
    /**Constructor.
     *
     * @param color the color.
     */
    public ColorFillStyle(Color color) {
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d, int x, int y, int width, int height) {
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }
}
