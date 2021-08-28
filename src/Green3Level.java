import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The main class for the green 3 level.
 *
 * @author Amir Gheriani 212938724
 */
public class Green3Level implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(Velocity.fromAngleAndSpeed(45, 5));
        list.add(Velocity.fromAngleAndSpeed(-45, 5));
        return list;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public String levelName() {
        return "Green 3";
    }
    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }
    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        List<Block> list = new ArrayList<Block>();
        int y = 175;
        int width = 45;
        int height = 25;
        for (int i = 0; i < colors.length; i++) {
            int x = 775 - width;
            for (int j = 0; j < 10 - i; j++) {
                list.add(new StrokeBlock(Color.BLACK, new DefaultBlock(new Rectangle(
                        new Point(x, y), width, height), 1, colors[i])));
                x -= width;
            }
            y += height;
        }
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
