import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The main class for the direct hit level.
 *
 * @author Amir Gheriani 212938724
 */
public class DirectHitLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(new Velocity(0, -5));
        return list;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 80;
    }
    @Override
    public String levelName() {
        return "Direct Hit";
    }
    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }
    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        Block block = new DefaultBlock(new Rectangle(new Point(385, 150), 30, 30), 1, Color.RED);
        list.add(block);
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
