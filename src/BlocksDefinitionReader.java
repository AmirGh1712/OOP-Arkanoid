import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
/**
 * The main class for blocks definition reader.
 *
 * @author Amir Gheriani 212938724
 */
public class BlocksDefinitionReader {
    /**Read the file and create a factory according to it.
     *
     * @param reader the file reader.
     * @return the new factory.
     * @throws Exception if there is an error in the file.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws Exception {
        Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
        Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        Map<String, String> defaluts = new TreeMap<String, String>();
        Map<Integer, FillStyle> fillDefaults = new TreeMap<Integer, FillStyle>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line = is.readLine();
            while (line != null) {
                if (line.equals("") || line.charAt(0) == '#') {
                    line = is.readLine();
                    continue;
                } else if (line.startsWith("bdef ")) {
                    addBlockCreatorFromString(line.substring(5), blockCreators, defaluts, fillDefaults);
                } else if (line.startsWith("sdef ")) {
                    addSpacerWidthFromString(line.substring(5), spacerWidths);
                } else if (line.startsWith("default ")) {
                    String[] arr = line.substring(8).split(" ");
                    for (String string : arr) {
                        String[] strs = string.split(":");
                        if (strs[0].startsWith("fill-")) {
                            if (strs[1].startsWith("image(")) {
                                Image img = null;
                                try {
                                    img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                                            strs[1].substring(6, strs[1].length() - 1)));
                                    fillDefaults.put(Integer.parseInt(strs[0].substring(5)), new ImageFillStyle(img));
                                } catch (IOException e) {
                                    System.err.println("error open image - " + strs[1].substring(6,
                                            strs[1].length() - 1));
                                }
                            } else {
                                ColorsParser cp = new ColorsParser();
                                fillDefaults.put(Integer.parseInt(strs[0].substring(5)), new ColorFillStyle(
                                        cp.colorFromString(strs[1].substring(6,  strs[1].length() - 1))));
                            }
                        } else {
                            defaluts.put(strs[0], strs[1]);
                        }
                    }
                } else {
                    System.err.println("error in block file in line: " + line);
                    throw new Exception("error in block file");
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong while reading !");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.err.println("Failed closing the file !");
                }
            }
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
    /**Add a block creator to the list according to the string.
     *
     * @param string the string.
     * @param blockCreators the list.
     * @param defaults the defaults.
     * @param fillDefaults the fill-k defaults.
     */
    public static void addBlockCreatorFromString(String string, Map<String, BlockCreator> blockCreators,
            Map<String, String> defaults, Map<Integer, FillStyle> fillDefaults) {
        String[] arr = string.split(" ");
        String symbol = null, fill = null;
        Color stroke = null;
        int width = 0, height = 0, hit = 0, biggestK = 0;
        boolean image = false, fillK = false;
        BlockCreator bc = null;
        ColorsParser cp = new ColorsParser();
        Map<Integer, FillStyle> map = new TreeMap<Integer, FillStyle>();
        Map<Integer, String> filMap = new TreeMap<Integer, String>();
        try {
            if (defaults.containsKey("width")) {
                width = Integer.parseInt(defaults.get("width"));
            }
            if (defaults.containsKey("height")) {
                height = Integer.parseInt(defaults.get("height"));
            }
            if (defaults.containsKey("hit_points")) {
                hit = Integer.parseInt(defaults.get("hit_points"));
            }
            if (defaults.containsKey("fill")) {
                fill = defaults.get("fill").substring(5);
                if (fill.startsWith("image(")) {
                    image = true;
                }
                fill = fill.substring(6, fill.length() - 1);
            }
            if (defaults.containsKey("stroke")) {
                String s = defaults.get("stroke");
                stroke = cp.colorFromString(s.substring(6, s.length() - 1));
            }
            for (Integer key : fillDefaults.keySet()) {
                map.put(key, fillDefaults.get(key));
            }
            for (String s : arr) {
                if (s.startsWith("symbol:")) {
                    symbol = s.substring(7);
                } else if (s.startsWith("width:")) {
                    width = Integer.parseInt(s.substring(6));
                } else if (s.startsWith("height:")) {
                    height = Integer.parseInt(s.substring(7));
                } else if (s.startsWith("hit_points:")) {
                    hit = Integer.parseInt(s.substring(11));
                } else if (s.startsWith("fill:")) {
                    fill = s.substring(5);
                    if (fill.startsWith("image(")) {
                        image = true;
                    }
                    fill = fill.substring(6, fill.length() - 1);
                } else if (s.startsWith("stroke:color(")) {
                    stroke = cp.colorFromString(s.substring(13, s.length() - 1));
                } else if (s.startsWith("fill-")) {
                    String[] strs = s.split(":");
                    int k = Integer.parseInt(strs[0].substring(5));
                    biggestK = Math.max(k, biggestK);
                    if (strs[1].startsWith("image(")) {
                        Image img = null;
                        try {
                            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                                    strs[1].substring(6,  strs[1].length() - 1)));
                            map.put(k, new ImageFillStyle(img));
                        } catch (IOException e) {
                            System.out.println("error open image - " + strs[1].substring(6,  strs[1].length() - 1));
                        }
                    } else {
                        map.put(k, new ColorFillStyle(cp.colorFromString(strs[1].substring(6,  strs[1].length() - 1))));
                    }
                    filMap.put(k, s.substring(7));
                    fillK = true;
                } else {
                    System.err.println("error in block file - unknown word: " + s);
                }
            }
            if (fill == null) {
                fill = filMap.get(biggestK).substring(6, filMap.get(biggestK).length() - 1);
            }
            if (image) {
                Image img = null;
                try {
                    img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fill));
                } catch (IOException e) {
                    System.err.println("error open image - " + fill);
                }
                bc = new ImageBlockCreator(width, height, hit, img);
            } else {
                bc = new DefaultBlockCreator(width, height, hit, cp.colorFromString(fill));
            }
            if (fillK) {
                if (image) {
                    Image img = null;
                    try {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fill));
                        map.put(0, new ImageFillStyle(img));
                    } catch (IOException e) {
                        System.err.println("error open image - " + fill.substring(6,  fill.length() - 1));
                    }
                } else {
                    map.put(0, new ColorFillStyle(cp.colorFromString(fill)));
                }
                bc = new ChangeFillBlockCreator(bc, map);
            }
            if (stroke != null) {
                bc = new StrokeBlockCreator(stroke, bc);
            }
            blockCreators.put(symbol, bc);
        } catch (Exception e) {
            System.err.println("block file error in line " + string);
        }
    }
    /**Add a space width according to the string.
     *
     * @param string the string.
     * @param spacerWidths the list.
     */
    public static void addSpacerWidthFromString(String string, Map<String, Integer> spacerWidths) {
        String[] arr = string.split(" ");
        String symbol = null;
        int width = 0;
        for (String s : arr) {
            if (s.startsWith("symbol:")) {
                symbol = s.substring(7);
            } else if (s.startsWith("width:")) {
                width = Integer.parseInt(s.substring(6));
            } else {
                System.err.println("error in block file in line: " + string);
            }
        }
        spacerWidths.put(symbol, width);
    }
}
