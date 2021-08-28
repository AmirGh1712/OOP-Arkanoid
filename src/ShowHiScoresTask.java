import biuoop.KeyboardSensor;
/**
 * The main class for show high score task game.
 *
 * @author Amir Gheriani
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresTable highScoresTable;
    private KeyboardSensor sensor;
    /**Constructor.
     *
     * @param runner the animation runner.
     * @param highScoresTable the table.
     * @param sensor the keyboard sensor.
     */
    public ShowHiScoresTask(AnimationRunner runner, HighScoresTable highScoresTable, KeyboardSensor sensor) {
        this.runner = runner;
        this.highScoresTable = highScoresTable;
        this.sensor = sensor;
    }
    @Override
    public Void run() {
        Animation animation = new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.highScoresTable));
        this.runner.run(animation);
        return null;
    }
}
