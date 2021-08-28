import java.io.File;
import java.util.List;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
/**
 * The main class for the game flow.
 *
 * @author Amir Gheriani 212938724
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;
    private DialogManager dialog;
    private static final File FILENAME = new File("highscores.ser");
    /**Constructor.
     *
     * @param ar the animation runner of the game.
     * @param ks the keyboard sensor.
     * @param dialog a dialog manager.
     * @param highScoresTable the high score table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DialogManager dialog, HighScoresTable highScoresTable) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.lives = new Counter();
        this.lives.increase(7);
        this.score = new Counter();
        this.highScoresTable = highScoresTable;
        this.dialog = dialog;
    }
    /**Run the given levels in order.
     *
     * @param levels the levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, this.score, this.lives);
            level.initialize();
            while (!level.shouldStop()) {
                level.playOneTurn();
            }
            if (this.lives.getValue() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new GameOverScreen(this.score.getValue())));
                break;
            }
        }
        if (this.lives.getValue() > 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new YouWinScreen(this.score.getValue())));
        }
        if (this.highScoresTable.getRank(this.score.getValue()) <= this.highScoresTable.size()) {
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScoresTable.add(new ScoreInfo(name, score.getValue()));
        }
        this.highScoresTable.save(FILENAME);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.highScoresTable)));
    }
}
