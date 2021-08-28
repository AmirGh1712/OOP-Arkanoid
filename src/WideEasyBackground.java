import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for the background of wide easy level.
 *
 * @author Amir Gheriani
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getWidth());;
        d.setColor(new Color(239, 231, 176));
        for (int i = 0; i < 700; i += 8) {
            d.drawLine(150, 150, i, 250);
        }
        d.fillCircle(150, 150, 65);
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(150, 150, 55);
        d.setColor(new Color(255, 255, 24));
        d.fillCircle(150, 150, 45);
    }
    @Override
    public void timePassed() {
        // The method doesn't do anything
    }
}
