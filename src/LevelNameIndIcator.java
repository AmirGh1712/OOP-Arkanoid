import biuoop.DrawSurface;
/**
 * The main class for level name indicator.
 *
 * @author Amir Gheriani
 */
public class LevelNameIndIcator implements Sprite {
    private String levelName;
    /**Constructor.
     *
     * @param levelName the level name to show.
     */
    public LevelNameIndIcator(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(550, 14, "Level Name: " + this.levelName, 14);
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
