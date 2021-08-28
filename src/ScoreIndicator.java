import biuoop.DrawSurface;
/**
 * The main class for score indicator.
 *
 * @author Amir Gheriani
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**Constructor.
     *
     * @param currentScore counter for the score in the game.
     */
    public ScoreIndicator(Counter currentScore) {
        this.score = currentScore;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(d.getWidth() / 2 - 30, 14, "Score: " + this.score.getValue(), 14);
    }
    @Override
    public void timePassed() {
        // This function is doing nothing right now.
    }
    /**Add this score indicator to the game.
    *
    * @param g the game.
    */
   public void addToGame(GameLevel g) {
       g.addSprite(this);
   }
}
