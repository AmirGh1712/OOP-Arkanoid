import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
/**
 * The main class for level specification reader.
 *
 * @author Amir Gheriani
 */
public class LevelSpecificationReader {
    private static final String START = "START_LEVEL";
    private static final String END = "END_LEVEL";
    /**Create a list of levels from the file.
     *
     * @param reader the file.
     * @return the list.
     * @throws Exception if there is error in the file.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws Exception {
        List<LevelInformation> list = new ArrayList<LevelInformation>();
        for (String string : this.splitLevels(reader)) {
            list.add(this.stringToLevel(string));
        }
        return list;
    }
    /**Create a list of level strings from the file.
     *
     * @param reader the file.
     * @return the list.
     */
    public List<String> splitLevels(java.io.Reader reader) {
        List<String> levels = new ArrayList<String>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line = is.readLine();
            String level = "";
            while (line != null) {
                if (line.equals("") || line.charAt(0) == '#') {
                    line = is.readLine();
                    continue;
                } else if (line.equals(START)) {
                    line = is.readLine();
                    while (line != null) {
                        if (line.equals(END)) {
                            level = level.substring(0, level.length() - 1);
                            break;
                        } else {
                            level += line + '\n';
                            line = is.readLine();
                        }
                    }
                    levels.add(level);
                    level = "";
                } else {
                    try {
                        is.close();
                    } catch (IOException e) {
                        System.err.println(" Failed closing the file !");
                    }
                    return null;
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close();
                } catch (IOException e) {
                    System.err.println("Failed closing the file !");
                }
            }
        }
        return levels;
    }
    /**Turn string to level object.
     *
     * @param leveString the string.
     * @return the level.
     * @throws Exception if there is an error in the file.
     */
    public GeneralLevel stringToLevel(String leveString) throws Exception {
        GeneralLevel level = new GeneralLevel();
        String[] lines = leveString.split("\n");
        BlocksFromSymbolsFactory factory = null;
        List<Block> blocks = new ArrayList<Block>();
        int xpos = 0, ypos = 0, rowHeight = 0, xposFirst = 0;
        boolean block = false;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("level_name:")) {
                level.setLevelName(lines[i].substring(11));
            } else if (lines[i].startsWith("ball_velocities:")) {
                String[] vels = lines[i].substring(16).split(" ");
                List<Velocity> list = new ArrayList<Velocity>();
                for (int j = 0; j < vels.length; j++) {
                    String[] vel = vels[j].split(",");
                    list.add(Velocity.fromAngleAndSpeed(Integer.parseInt(vel[0]), Integer.parseInt(vel[1])));
                }
                level.setVelocities(list);
            } else if (lines[i].startsWith("background:")) {
                level.setBackground(this.backgroundFromString(lines[i].substring(11)));
            } else if (lines[i].startsWith("paddle_speed:")) {
                level.setPaddleSpeed(Integer.parseInt(lines[i].substring(13)));
            } else if (lines[i].startsWith("paddle_width:")) {
                level.setPaddleWidth(Integer.parseInt(lines[i].substring(13)));
            } else if (lines[i].startsWith("block_definitions:")) {
                factory = BlocksDefinitionReader.fromReader(new InputStreamReader(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(lines[i].substring(18))));
            } else if (lines[i].startsWith("blocks_start_x:")) {
                xposFirst = Integer.parseInt(lines[i].substring(15));
            } else if (lines[i].startsWith("blocks_start_y:")) {
                ypos = Integer.parseInt(lines[i].substring(15));
            } else if (lines[i].startsWith("row_height:")) {
                rowHeight = Integer.parseInt(lines[i].substring(11));
            } else if (lines[i].startsWith("num_blocks:")) {
                level.setToRemove(Integer.parseInt(lines[i].substring(11)));
            } else if (lines[i].equals("START_BLOCKS")) {
                block = true;
            } else if (lines[i].equals("END_BLOCKS")) {
                block = false;
            } else if (block) {
                xpos = xposFirst;
                for (int j = 0; j < lines[i].length(); j++) {
                    if (factory.isBlockSymbol(lines[i].charAt(j) + "")) {
                        Block b = factory.getBlock(lines[i].charAt(j) + "", xpos, ypos);
                        xpos += b.getCollisionRectangle().getWidth();
                        blocks.add(b);
                    } else if (factory.isSpaceSymbol(lines[i].charAt(j) + "")) {
                        xpos += factory.getSpaceWidth(lines[i].charAt(j) + "");
                    } else {
                        System.out.println("error");
                    }
                }
                ypos += rowHeight;
            } else {
                System.out.println("error in level specification - " + lines[i]);
                throw new Exception("error in level specification");
            }
        }
        level.setBlocks(blocks);
        return level;
    }
    /**Create background from string.
     *
     * @param string the string.
     * @return the background.
     */
    public Sprite backgroundFromString(String string) {
        if (string.startsWith("image(")) {
            string = string.substring(6, string.length() - 1);
            try {
                return new ImageBackground(ImageIO.read(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(string)));
            } catch (IOException e) {
                System.out.println("error - bgI " + string);
                return null;
            }
        } else if (string.startsWith("color(")) {
            string = string.substring(6, string.length() - 1);
            ColorsParser cp = new ColorsParser();
            return new Background(cp.colorFromString(string));
        } else {
            System.out.println("error - bg " + string);
            return null;
        }
    }
}