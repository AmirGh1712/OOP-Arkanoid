import java.util.ArrayList;
import java.util.List;
/**
 * The main class for general level.
 *
 * @author Amir Gheriani
 */
public class GeneralLevel implements LevelInformation {
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int toRemove;
    /**Constructor.
     */
    public GeneralLevel() {
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.toRemove = 1;
        this.blocks = new ArrayList<Block>();
    }
    @Override
    public int numberOfBalls() {
        if (this.velocities != null) {
            return this.velocities.size();
        }
        return 0;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }
    /**Set the velocities.
    *
    * @param v the velocities.
    */
    public void setVelocities(List<Velocity> v) {
        this.velocities = v;
    }
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**Set the paddle speed.
    *
    * @param ps the paddle speed.
    */
    public void setPaddleSpeed(int ps) {
        this.paddleSpeed = ps;
    }
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**Set the paddle width.
    *
    * @param pw the paddle width.
    */
    public void setPaddleWidth(int pw) {
        this.paddleWidth = pw;
    }
    @Override
    public String levelName() {
        return this.levelName;
    }
    /**Set the level name.
    *
    * @param ln the level name.
    */
    public void setLevelName(String ln) {
        this.levelName = ln;
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    /**Set the background.
     *
     * @param bg the background.
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    /**Set the blocks.
    *
    * @param b the blocks.
    */
    public void setBlocks(List<Block> b) {
        this.blocks = b;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.toRemove;
    }
    /**Set the number of blocks to remove.
    *
    * @param tr the number.
    */
    public void setToRemove(int tr) {
        this.toRemove = tr;
    }
}
