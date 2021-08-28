import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The main class for the background of the final four level.
 *
 * @author Amir Gheriani 212938724
 */
public class FinalFourLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(Velocity.fromAngleAndSpeed(45, 5));
        list.add(Velocity.fromAngleAndSpeed(-45, 5));
        list.add(Velocity.fromAngleAndSpeed(0, 5));
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
        return "Final Four";
    }
    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }
    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        List<Block> list = new ArrayList<Block>();
        int y = 100;
        int width = 750 / 15;
        int height = 25;
        for (int i = 0; i < colors.length; i++) {
            int x = 25;
            for (int j = 0; j < 15; j++) {
                list.add(new StrokeBlock(Color.BLACK,
                        new DefaultBlock(new Rectangle(new Point(x, y), width, height), 1, colors[i])));
                x += width;
            }
            y += height;
        }
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
