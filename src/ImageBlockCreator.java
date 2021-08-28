import java.awt.Image;
/**
 * The main class for image block creator.
 *
 * @author Amir Gheriani
 */
public class ImageBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private int hit;
    private Image image;
    /**Constructor.
     *
     * @param width the width of the block.
     * @param height the height of the block.
     * @param hit the hit points of the block.
     * @param image the image of the block.
     */
    public ImageBlockCreator(int width, int height, int hit, Image image) {
        this.width = width;
        this.height = height;
        this.hit = hit;
        this.image = image;
    }
    @Override
    public Block create(int xpos, int ypos) {
        return new ImageBlock(new Rectangle(new Point(xpos, ypos), this.width, this.height), this.hit, this.image);
    }

}
