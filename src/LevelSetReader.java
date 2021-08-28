import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
 * The main class for level sets reader.
 *
 * @author Amir Gheriani
 */
public class LevelSetReader {
    private KeyboardSensor sensor;
    private AnimationRunner ar;
    private GUI gui;
    private HighScoresTable highScoresTable;
    /**Constructor.
     *
     * @param sensor the keyboard sensor.
     * @param ar the animation runner.
     * @param gui the gui.
     * @param highScoresTable the table.
     */
    public LevelSetReader(KeyboardSensor sensor, AnimationRunner ar, GUI gui, HighScoresTable highScoresTable) {
        this.sensor = sensor;
        this.ar = ar;
        this.gui = gui;
        this.highScoresTable = highScoresTable;
    }
    /**Create menu from the file.
     *
     * @param reader the file.
     * @return the menu.
     */
    public Menu<Task<Void>> fromReader(java.io.Reader reader) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(this.sensor, this.ar);
        LineNumberReader is = null;
        try {
            is = new LineNumberReader(new BufferedReader(reader));
            String line = is.readLine();
            int num = is.getLineNumber();
            List<String> keys = new ArrayList<String>();
            List<String> messages = new ArrayList<String>();
            List<Task<Void>> values = new ArrayList<Task<Void>>();
            while (line != null) {
                if (num % 2 == 1) {
                    if (line.contains(":")) {
                        String[] arr = line.split(":");
                        keys.add(arr[0]);
                        messages.add(arr[1]);
                    } else {
                        System.err.println("level set file has error in line " + num + " : " + line);
                    }
                } else {
                    LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                    List<LevelInformation> levels = null;
                    try {
                        levels = levelSpecificationReader.fromReader(
                                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(line)));
                    } catch (Exception e) {
                        System.err.println("Something went wrong while reading levels file!" + line);
                    }
                    Task<Void> task = new StartGameTask(this.ar, this.gui, levels, this.highScoresTable);
                    values.add(task);
                }
                line = is.readLine();
                num = is.getLineNumber();
            }
            for (int i = 0; i < keys.size(); i++) {
                String msg = "Press \"" + keys.get(i) + "\" to " + messages.get(i);
                menu.addSelection(keys.get(i), msg, values.get(i));
            }
        } catch (IOException e) {
            System.err.println("Something went wrong while reading level sets file!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.err.println("Failed closing the file !");
                }
            }
        }
        return menu;
    }
}
