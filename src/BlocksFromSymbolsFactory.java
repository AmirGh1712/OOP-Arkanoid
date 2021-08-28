import java.util.Map;
/**
 * The main class for blocks factory from symbols.
 *
 * @author Amir Gheriani
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    /**Constructor.
     *
     * @param spacerWidths the spaces map.
     * @param blockCreators the blocks map.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }
    /**Returns true if 's' is a valid space symbol.
     *
     * @param s the symbol
     * @return if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
    /**Returns true if 's' is a valid block symbol.
    *
    * @param s the symbol
    * @return if 's' is a valid block symbol.
    */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
    /**Return a block according to the definitions associated with symbol s.
     *
     * @param s the symbol.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return the new block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }
    /**Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the symbol.
     * @return the width.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}