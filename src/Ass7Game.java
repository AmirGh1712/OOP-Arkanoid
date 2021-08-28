import java.io.File;
import java.io.InputStreamReader;
import biuoop.GUI;
/**
 * The main class for ass7 game.
 *
 * @author Amir Gheriani 212938724
 */
public class Ass7Game {
    private static final File FILENAME = new File("highscores.ser");
    /**The main method.
     * Create a game flow object, initializes and runs.
     *
     * @param args command line arguments - the level sets file.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        HighScoresTable h = HighScoresTable.loadFromFile(FILENAME);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), ar);
        LevelSetReader levelSetReader = new LevelSetReader(gui.getKeyboardSensor(), ar, gui, h);
        String file = null;
        if (args.length > 0) {
            file = args[0];
        } else {
            file = "level_sets.txt";
        }
        Menu<Task<Void>> subMenu = null;
        try {
            subMenu = levelSetReader.fromReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(file)));
        } catch (Exception e) {
            System.err.println("file not found: " + file);
        }
        subMenu = levelSetReader.fromReader(new InputStreamReader(
                ClassLoader.getSystemClassLoader().getResourceAsStream(file)));
        menu.addSubMenu("s", "Press \"s\" to start a new game", subMenu);
        menu.addSelection("h", "Press \"h\" to see the high scores.",
                new ShowHiScoresTask(ar,  h, gui.getKeyboardSensor()));
        menu.addSelection("q", "Press \"q\" to quit.", new QuitTask(gui));
        while (true) {
            ar.run(menu);
            Task<Void> status = menu.getStatus();
            status.run();
         }
    }
}
