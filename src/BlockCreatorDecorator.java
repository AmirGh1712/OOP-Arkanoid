/**
 * The abstract class for block creator decorator.
 *
 * @author Amir Gheriani
 */
public abstract class BlockCreatorDecorator implements BlockCreator {
    private BlockCreator decorator;
    /**Constructor.
     *
     * @param deccorator the decorator.
     */
    public BlockCreatorDecorator(BlockCreator deccorator) {
        this.decorator = deccorator;
    }
    @Override
    public Block create(int xpos, int ypos) {
        return this.decorator.create(xpos, ypos);
    }
}
