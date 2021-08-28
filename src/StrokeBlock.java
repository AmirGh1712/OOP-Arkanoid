import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for stroke block.
 *
 * @author Amir Gheriani
 */
public class StrokeBlock extends BlockDecorator {
    private Block decorator;
    private Color color;
    /**Constructor.
     *
     * @param color the stroke color.
     * @param decorator the decorator.
     */
    public StrokeBlock(Color color, Block decorator) {
        super(decorator);
        this.decorator = decorator;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.decorator.drawOn(d);
        this.decorator.getCollisionRectangle().drawLineOn(d, color);
    }
}
