import java.awt.Image;
import biuoop.DrawSurface;
/**
 * The main class for image background.
 *
 * @author Amir Gheriani 212938724
 */
public class ImageBackground extends Background {
    private Image image;
    /**Constructor.
     *
     * @param image the image.
     */
    public ImageBackground(Image image) {
        super(null);
        this.image = image;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }
}
