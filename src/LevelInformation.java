import java.util.List;
/**
 * The main interface for a level information.
 *
 * @author Amir Gheriani 212938724
 */
public interface LevelInformation {
    /**Return the number of balls in the game.
     *
     * @return the number of balls in the game.
     */
    int numberOfBalls();
    /**Return the initial velocity of each ball.
     *
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**Return the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();
    /**Return the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();
    /**Return the level name.
     *
     * @return the level name.
     */
    String levelName();
    /**Return a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    /**Return the Blocks that make up this level.
     *
     * @return the Blocks that make up this level.
     */
    List<Block> blocks();
    /**Return the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
