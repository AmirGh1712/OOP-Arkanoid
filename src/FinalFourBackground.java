import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for the background of the final four level.
 *
 * @author Amir Gheriani 212938724
 */
public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, d.getWidth(), d.getWidth());
        d.setColor(Color.WHITE);
        for (int i = 75; i < 150; i += 8) {
            d.drawLine(i, 400, i - 20, 600);
        }
        for (int i = 630; i < 710; i += 8) {
            d.drawLine(i, 510, i - 25, 600);
        }
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(70, 400, 20);
        d.fillCircle(85, 415, 20);
        d.fillCircle(630, 500, 20);
        d.fillCircle(650, 530, 25);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(105, 390, 25);
        d.fillCircle(660, 508, 25);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(114, 415, 18);
        d.fillCircle(133, 398, 27);
        d.fillCircle(675, 520, 18);
        d.fillCircle(695, 513, 25);
    }
    @Override
    public void timePassed() {
        // The method doesn't do anything
    }
}
