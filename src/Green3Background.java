import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The main class for the background of green 3 level.
 *
 * @author Amir Gheriani
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, d.getWidth(), d.getWidth());
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(75, 450, 100, 150);
        d.setColor(Color.WHITE);
        int y = 455;
        for (int i = 0; i < 5; i++) {
            int x = 80;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x, y, 14, 27);
                x += 19;
            }
            y += 32;
        }
        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(110, 400, 30, 50);
        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(120, 225, 10, 175);
        d.setColor(new Color(216, 172, 102));
        d.fillCircle(125, 225, 10);
        d.setColor(new Color(246, 77, 54));
        d.fillCircle(125, 225, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(125, 225, 4);
    }
    @Override
    public void timePassed() {
        // The method doesn't do anything
    }
}
