import java.awt.Image;
import biuoop.DrawSurface;
/**
 * The main class for image fill style.
 *
 * @author Amir Gheriani
 */
public class ImageFillStyle implements FillStyle {
    private Image img;
    /**Constructor.
     *
     * @param img the image.
     */
    public ImageFillStyle(Image img) {
        this.img = img;
    }
    @Override
    public void drawOn(DrawSurface d, int x, int y, int width, int height) {
        d.drawImage(x, y, this.img);
    }
}
