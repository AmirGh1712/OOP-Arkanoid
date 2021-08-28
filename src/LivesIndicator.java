import biuoop.DrawSurface;
/**
 * The main class for LivesIndicator.
 *
 * @author Amir Gheriani
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    /**Constructor.
     *
     * @param currentlives counter for lives in the game.
     */
    public LivesIndicator(Counter currentlives) {
        this.lives = currentlives;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(170 , 14, "Lives: " + this.lives.getValue(), 14);
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
