/**
 * The interface for block.
 *
 * @author Amir Gheriani
 */
public interface Block extends Collidable, Sprite, HitNotifier {
    /**Return the hit points.
    *
    * @return the hit points.
    */
    int getHitPoints();
    /**Add this block to the game.
    *
    * @param g the game.
    */
    void addToGame(GameLevel g);
    /**Remove this block to the game.
    *
    * @param game the game.
    */
    void removeFromGame(GameLevel game);
}
