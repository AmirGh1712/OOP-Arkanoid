import java.awt.Color;
/**
 * The main class for stroke block creator.
 *
 * @author Amir Gheriani
 */
public class StrokeBlockCreator extends BlockCreatorDecorator {
    private Color strokeColor;
    /**Constructor.
     *
     * @param strokeColor the stroke color.
     * @param deccorator the decorator.
     */
    public StrokeBlockCreator(Color strokeColor, BlockCreator deccorator) {
        super(deccorator);
        this.strokeColor = strokeColor;
    }
    @Override
    public Block create(int xpos, int ypos) {
        return new StrokeBlock(this.strokeColor, super.create(xpos, ypos));
    }
}
