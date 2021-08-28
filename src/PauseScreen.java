import biuoop.DrawSurface;
/**
 * The main class for the pause screen.
 *
 * @author Amir Gheriani
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
