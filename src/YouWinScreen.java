import biuoop.DrawSurface;
/**
 * The main class for the winning screen.
 *
 * @author Amir Gheriani
 */
public class YouWinScreen implements Animation {
    private int score;
    /**Constructor.
    *
    * @param score the score to show.
    */
    public YouWinScreen(int score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
