import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for the background of the direct hit level.
 *
 * @author Amir Gheriani 212938724
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getWidth());
        d.setColor(Color.BLUE);
        d.drawCircle(d.getWidth() / 2, 165, 120);
        d.drawCircle(d.getWidth() / 2, 165, 90);
        d.drawCircle(d.getWidth() / 2, 165, 60);
        d.drawLine(d.getWidth() / 2, 140, d.getWidth() / 2, 45);
        d.drawLine(d.getWidth() / 2, 190, d.getWidth() / 2, 310);
        d.drawLine(d.getWidth() / 2 - 25, 165, d.getWidth() / 2 - 145, 165);
        d.drawLine(d.getWidth() / 2 + 25, 165, d.getWidth() / 2 + 145, 165);
    }
    @Override
    public void timePassed() {
        // The method doesn't do anything
    }
}
