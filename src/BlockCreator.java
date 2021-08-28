/**
 * The interface for block creator.
 *
 * @author Amir Gheriani
 */
public interface BlockCreator {
    /**Create a block at the specified location.
     *
     * @param xpos the x position.
     * @param ypos the y position.
     * @return the new block.
     */
    Block create(int xpos, int ypos);
}