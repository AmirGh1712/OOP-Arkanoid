import biuoop.GUI;
/**
 * The main class for quit task.
 *
 * @author Amir Gheriani
 */
public class QuitTask implements Task<Void> {
    private GUI gui;
    /**Constructor.
     *
     * @param gui the gui.
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }
    @Override
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
