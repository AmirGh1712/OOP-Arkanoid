import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The main class for wide easy level.
 *
 * @author Amir Gheriani 212938724
 */
public class WideEasyLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 30; i < 90; i += 10) {
            list.add(Velocity.fromAngleAndSpeed(i, 5));
            list.add(Velocity.fromAngleAndSpeed(-i, 5));
        }
        return list;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 600;
    }
    @Override
    public String levelName() {
        return "Wide Easy";
    }
    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }
    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        List<Block> list = new ArrayList<Block>();
        int width = 750 / colors.length;
        int x = 25;
        for (int i = 0; i < colors.length; i++) {
            list.add(new StrokeBlock(Color.BLACK,
                    new DefaultBlock(new Rectangle(new Point(x, 250), width, 30), 1, colors[i])));
            x += width;
        }
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
