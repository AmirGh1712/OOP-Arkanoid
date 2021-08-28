import biuoop.DrawSurface;
/**
 * The main class for the game over screen.
 *
 * @author Amir Gheriani 212938724
 */
public class GameOverScreen implements Animation {
    private int score;
    /**Constructor.
     *
     * @param score the score to show.
     */
    public GameOverScreen(int score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2, "Game Over! Your score is " + this.score, 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
