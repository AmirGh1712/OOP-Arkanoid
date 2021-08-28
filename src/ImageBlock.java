import java.awt.Image;
import biuoop.DrawSurface;
/**
 * The main class for image block.
 *
 * @author Amir Gheriani
 */
public class ImageBlock extends DefaultBlock {
    private Image image;
    /**Constructor.
     *
     * @param rectangle the rectangle.
     * @param hit the hit points.
     * @param image the image.
     */
    public ImageBlock(Rectangle rectangle, int hit, Image image) {
        super(rectangle, hit, null);
        this.image = image;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        Rectangle rectangle = this.getCollisionRectangle();
        Point point = rectangle.getUpperLeft();
        surface.drawImage((int) point.getX(), (int) point.getY(), this.image);
    }
}
