import java.awt.Color;
/**
 * The main class for default block creator.
 *
 * @author Amir Gheriani 212938724
 */
public class DefaultBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private int hit;
    private Color color;
    /**Constructor.
     *
     * @param width the width of the block.
     * @param height the height of the block.
     * @param hit the hit points of the block.
     * @param color the color of the block.
     */
    public DefaultBlockCreator(int width, int height, int hit, Color color) {
        this.width = width;
        this.height = height;
        this.hit = hit;
        this.color = color;
    }
    @Override
    public Block create(int xpos, int ypos) {
        return new DefaultBlock(new Rectangle(new Point(xpos, ypos), this.width, this.height), this.hit, this.color);
    }
}
