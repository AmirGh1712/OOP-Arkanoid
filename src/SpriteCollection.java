import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * The main class for sprite collection.
 *
 * @author Amir Gheriani
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**Add sprite to the collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**Remove sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> list = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : list) {
            sprite.timePassed();
        }
    }
    /**Call drawOn(d) on all sprites.
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}