import java.util.Map;
/**
 * The main class for change fill block creator.
 *
 * @author Amir Gheriani 212938724
 */
public class ChangeFillBlockCreator extends BlockCreatorDecorator {
    private BlockCreator decorator;
    private Map<Integer, FillStyle> map;
    /**Constructor.
     *
     * @param deccorator the decorator.
     * @param map the map of the different fill styles..
     */
    public ChangeFillBlockCreator(BlockCreator deccorator, Map<Integer, FillStyle> map) {
        super(deccorator);
        this.decorator = deccorator;
        this.map = map;
    }
    @Override
    public Block create(int xpos, int ypos) {
        return new ChangeFillBlock(this.decorator.create(xpos, ypos), this.map);
    }
}
