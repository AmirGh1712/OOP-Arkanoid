import java.util.List;

import biuoop.GUI;
/**
 * The main class for start game task.
 *
 * @author Amir Gheriani 212938724
 */
public class StartGameTask implements Task<Void> {
    private GUI gui;
    private HighScoresTable highScoresTable;
    private List<LevelInformation> levels;
    private AnimationRunner ar;
    /**Constructor.
     *
     * @param ar the animation runner.
     * @param gui the gui.
     * @param levels the levels list
     * @param highScoresTable the high score table.
     */
    public StartGameTask(AnimationRunner ar, GUI gui, List<LevelInformation> levels, HighScoresTable highScoresTable) {
        this.levels = levels;
        this.gui = gui;
        this.ar = ar;
        this.highScoresTable = highScoresTable;
    }
    @Override
    public Void run() {
        GameFlow gameFlow = new GameFlow(this.ar, this.gui.getKeyboardSensor(), this.gui.getDialogManager(),
                this.highScoresTable);
        gameFlow.runLevels(this.levels);
        return null;
    }
}
