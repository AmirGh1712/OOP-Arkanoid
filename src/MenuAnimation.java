import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The main class for menu anumation.
 *
 * @author Amir Gheriani
 *
 * @param <T> the type of the return value.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> seletions;
    private KeyboardSensor keyboardSensor;
    private T status;
    private boolean shouldStop;
    private List<Selection<Menu<T>>> subMenus;
    private AnimationRunner ar;
    private boolean pressed;
    /**Constructor.
     *
     * @param sensor the keyboard sensor.
     * @param ar the animation runner.
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner ar) {
        this.seletions = new ArrayList<Selection<T>>();
        this.subMenus = new ArrayList<Selection<Menu<T>>>();
        this.keyboardSensor = sensor;
        this.status = null;
        this.shouldStop = false;
        this.ar = ar;
        this.pressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.pressed) {
            boolean flag = false;
            for (Selection<T> selection : this.seletions) {
                if (this.keyboardSensor.isPressed(selection.getKey())) {
                    flag = true;
                }
            }
            if (flag) {
                return;
            } else {
                this.pressed = false;
            }
        }
        // Check for press
        for (Selection<Menu<T>> selection: this.subMenus) {
            if (this.keyboardSensor.isPressed(selection.getKey())) {
                this.shouldStop = true;
                this.ar.run(selection.getReturnValue());
                this.status = selection.getReturnValue().getStatus();
                return;
            }
        }
        for (Selection<T> selection : this.seletions) {
            if (this.keyboardSensor.isPressed(selection.getKey())) {
                this.status = selection.getReturnValue();
                this.shouldStop = true;
            }
        }
        // Draw the menu
        int i = 100;
        for (Selection<T> selection : this.seletions) {
            d.drawText(70, i, selection.getMessage(), 32);
            i += 70;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.seletions.add(new Selection<T>(key, message, returnVal));
    }
    @Override
    public T getStatus() {
        T temp = this.status;
        this.status = null;
        this.shouldStop = false;
        return temp;
    }
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenus.add(new Selection<Menu<T>>(key, message, subMenu));
        this.addSelection(key, message, null);
    }
}
