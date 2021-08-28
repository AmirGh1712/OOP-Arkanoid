import java.util.Map;
import biuoop.DrawSurface;
/**
 * The main class for change fill block.
 *
 * @author Amir Gheriani
 */
public class ChangeFillBlock extends BlockDecorator {
    private Map<Integer, FillStyle> map;
    /**Constructor.
     *
     * @param decorator the decorator.
     * @param fillMap the map of the different fill styles.
     */
    public ChangeFillBlock(Block decorator, Map<Integer, FillStyle> fillMap) {
        super(decorator);
        this.map = fillMap;
    }
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = this.getCollisionRectangle();
        Point point = rectangle.getUpperLeft();
        if (this.map.containsKey(this.getHitPoints())) {
           this.map.get(this.getHitPoints()).drawOn(d, (int) point.getX(), (int) point.getY(),
                   (int) rectangle.getWidth(), (int) rectangle.getHeight());
        } else {
            this.map.get(0).drawOn(d, (int) point.getX(), (int) point.getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }
}
