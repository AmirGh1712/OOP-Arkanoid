import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The main class for key pressed stoppable animation.
 *
 * @author Amir Gheriani 212938724
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;
    /**Constructor.
     *
     * @param sensor the keyboard sensor.
     * @param key the key to press.
     * @param animation the animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.running = true;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!isAlreadyPressed) {
            this.animation.doOneFrame(d);
            if (this.keyboardSensor.isPressed(this.key)) {
                this.running = false;
            }
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
