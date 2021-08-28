import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * The main class for high score table.
 *
 * @author Amir Gheriani 212938724
 */
class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int size;
    /**Constructor.
     *
     * @param size the size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<ScoreInfo>();
    }
    /**Add a high score.
     *
     * @param score the new score.
     */
    public void add(ScoreInfo score) {
       if (this.scores.size() < this.size) {
           this.scores.add(score);
       } else {
           if (this.scores.get(this.size - 1).getScore() > score.getScore()) {
               return;
           }
           this.scores.remove(this.size - 1);
           this.scores.add(score);
       }
       int i = 2;
       while (i < this.scores.size() + 1 && this.scores.get(this.scores.size() - i).getScore() < score.getScore()) {
           this.scores.set(this.scores.size() - i + 1, this.scores.get(this.scores.size() - i));
           this.scores.set(this.scores.size() - i, score);
           i++;
       }
    }
    /**Return table size.
     *
     * @return table size.
     */
    public int size() {
        return this.size;
    }
    /**Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return  the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }
    /**return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score the score.
     * @return the rank.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); i++) {
            if (score > this.scores.get(i).getScore()) {
                return i + 1;
            }
        }
        return this.scores.size() + 1;
    }
    /** Clears the table.
     */
    public void clear() {
        this.scores = new ArrayList<ScoreInfo>();
    }
    /**Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the file.
     */
    public void load(File filename) {
        // use conventional 'ser' file extension for java serialized objects
        HighScoresTable highScoresTable;
        ObjectInputStream objectInputStream = null;
        try {
             objectInputStream = new ObjectInputStream(
                                     new FileInputStream(filename));
             // unsafe down casting, we better be sure that the stream really contains a Person!
             highScoresTable = (HighScoresTable) objectInputStream.readObject();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        this.clear();
        for (ScoreInfo scoreInfo : highScoresTable.getHighScores()) {
            this.add(scoreInfo);
        }
    }
    /**Save table data to the specified file.
     *
     * @param filename the file
     */
    public void save(File filename) {
        // use conventional 'ser' file extension for java serialized objects
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                                   new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
    /**Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the file.
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            HighScoresTable highScoresTable = new HighScoresTable(5);
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (Exception e) {
            HighScoresTable highScoresTable = new HighScoresTable(5);
            try {
                highScoresTable.save(filename);
            } finally {
                return highScoresTable;
            }
        }
    }
}
