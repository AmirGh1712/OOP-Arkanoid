/**
 * A BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Amir Gheriani 212938724
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**Constructor.
     *
     * @param game the game.
     * @param remainedBalls the counter for balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainedBalls) {
        this.game = game;
        this.remainingBalls = remainedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}