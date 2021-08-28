import java.awt.Color;
/**
 * The main class for color praser.
 *
 * @author Amir Gheriani 212938724
 */
public class ColorsParser {
    /**Parse color definition and return the specified color.
     *
     * @param s the string.
     * @return the new color.
     */
    public Color colorFromString(String s) {
        Color c;
        if (s.startsWith("RGB(")) {
            s = s.substring(4, s.length() - 1);
            String[] rgb = s.split(",");
            return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]),
                   Integer.parseInt(rgb[2]));
        }
        switch (s) {
        case "black":
            c = Color.BLACK;
            break;
        case "blue":
            c = Color.BLUE;
            break;
        case "cyan":
            c = Color.CYAN;
            break;
        case "gray":
            c = Color.GRAY;
            break;
        case "lightGray":
            c = Color.LIGHT_GRAY;
            break;
        case "green":
            c = Color.GREEN;
            break;
        case "orange":
            c = Color.ORANGE;
            break;
        case "pink":
            c = Color.PINK;
            break;
        case "red":
            c = Color.RED;
            break;
        case "white":
            c = Color.WHITE;
            break;
        case "yellow":
            c = Color.YELLOW;
            break;
        default:
            System.out.println("not a color " + s);
            return null;
        }
        return c;
    }
}
