import java.io.Serializable;
/**
 * The main class for score information.
 *
 * @author Amir Gheriani
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
    /**Constructor.
     *
     * @param name the name of the player.
     * @param score the score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**Return the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }
    /**Return the score.
    *
    * @return the score.
    */
    public int getScore() {
        return this.score;
    }
}
