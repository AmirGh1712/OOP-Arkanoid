import biuoop.DrawSurface;
/**
 * The main class for high scores animation.
 *
 * @author Amir Gheriani 212938724
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
    /**Constructor.
     *
     * @param scores the table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.table = scores;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        int i = 1;
        for (ScoreInfo scoreInfo : this.table.getHighScores()) {
            d.drawText(d.getWidth() / 2 - 100, 70 * i, i + ". " + scoreInfo.getName() + ":   " + scoreInfo.getScore(),
                    32);
            i++;
        }
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}