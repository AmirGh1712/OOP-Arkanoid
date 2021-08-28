/**
 * The main class for score tracking listener.
 *
 * @author Amir Gheriani
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;
   /**Constructor.
    *
    * @param scoreCounter counter for the score in the game.
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
       if (beingHit.getHitPoints() <= 0) {
           this.currentScore.increase(10);
           beingHit.removeHitListener(this);
       }
   }
}