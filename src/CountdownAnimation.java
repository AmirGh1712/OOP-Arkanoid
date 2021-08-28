import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for the countdown animation.
 *
 * @author Amir Gheriani 212938724
 */
public class CountdownAnimation implements Animation {
    private boolean stop;
    private double milisecondsPerNumber;
    private int count;
    private SpriteCollection gameScreen;
    private Long startTime;
    /**Constructor.
     *
     * @param numOfSeconds the number of seconds of the animation.
     * @param countFrom the first number of the countdown.
     * @param gameScreen the game screen to show.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.stop = false;
        this.milisecondsPerNumber = numOfSeconds / countFrom * 1000;
        this.count = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        int num = (int) ((System.currentTimeMillis() - this.startTime) / this.milisecondsPerNumber);
        if (num >= this.count) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, (this.count - num) + "", 30);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
