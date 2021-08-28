import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The main class for game level.
 *
 * @author Amir Gheriani
 */
public class GameLevel implements Animation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int EDGE_WIDTH = 25;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remaindBlocks;
    private Counter remaindBalls;
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private boolean running;
    private AnimationRunner runner;
    private LevelInformation level;
    private KeyboardSensor keyboardSensor;
    /**Constructor.
     *
     * @param levelInformation the level to run.
     * @param keyboardSensor the keyboard sensor.
     * @param animationRunner the animation runner.
     * @param score the score of the whole game.
     * @param lives the lives of the whole game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
            AnimationRunner animationRunner, Counter score, Counter lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remaindBlocks = new Counter();
        this.remaindBalls = new Counter();
        this.score = score;
        this.lives = lives;
        this.running = true;
        this.level = levelInformation;
        this.runner = animationRunner;
        this.keyboardSensor = keyboardSensor;
    }
    /**Add collidable to the enviroment.
     *
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**Add sprite to the game.
    *
    * @param s the sprite.
    */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        /*int x = GUI_WIDTH - EDGE_WIDTH - BLOCK_WIDTH, y = EDGE_WIDTH + 3 * BLOCK_HEIGHT;
        Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};*/
        Block wall, bar;
        // Listeners:
        BlockRemover p = new BlockRemover(this, this.remaindBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remaindBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        // Background:
        this.addSprite(this.level.getBackground());
        bar = new DefaultBlock(new Rectangle(new Point(0, 0), GUI_WIDTH, 20), 1, Color.WHITE);
        bar.addToGame(this);
        // Indicators:
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);
        LevelNameIndIcator levelNameIndIcator = new LevelNameIndIcator(this.level.levelName());
        levelNameIndIcator.addToGame(this);
        // The Edges.
        wall = new DefaultBlock(new Rectangle(new Point(0, 20), GUI_WIDTH, EDGE_WIDTH), 0, Color.GRAY);
        wall.addToGame(this);
        wall = new DefaultBlock(new Rectangle(new Point(0, GUI_HEIGHT),
                GUI_WIDTH, EDGE_WIDTH), 0, Color.GRAY);
        wall.addToGame(this);
        wall.addHitListener(ballRemover);
        wall = new DefaultBlock(new Rectangle(new Point(0, EDGE_WIDTH + 20), EDGE_WIDTH, GUI_HEIGHT - EDGE_WIDTH - 20),
                0, Color.GRAY);
        wall.addToGame(this);
        wall = new DefaultBlock(new Rectangle(new Point(GUI_WIDTH - EDGE_WIDTH, EDGE_WIDTH + 20),
                EDGE_WIDTH, GUI_WIDTH - EDGE_WIDTH - 20), 0, Color.GRAY);
        wall.addToGame(this);
        // Blocks:
        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(scoreTrackingListener);
        }
        this.remaindBlocks.increase(this.level.numberOfBlocksToRemove());
        // The paddle.
        this.paddle = new Paddle(new Rectangle(new Point(GUI_WIDTH / 2 - this.level.paddleWidth() / 2,
                GUI_HEIGHT - 20 - EDGE_WIDTH), this.level.paddleWidth(), 20),
                EDGE_WIDTH, GUI_WIDTH - EDGE_WIDTH, this.level.paddleSpeed(), this.keyboardSensor);
        paddle.addToGame(this);
    }
    /**Play one turn -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBalls();
        this.paddle.getCollisionRectangle().setUpperLeft(new Point(GUI_WIDTH / 2 - this.level.paddleWidth() / 2,
                GUI_HEIGHT - 20 - EDGE_WIDTH));
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.remaindBlocks.getValue() <= 0) {
            this.score.increase(100);
        }
    }
    /** Remove collidable from the game.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /** Remove sprite from the game.
    *
    * @param s the sprite to remove.
    */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /** Create the balls for each turn.
     */
    public void createBalls() {
        Point center = new Point(GUI_WIDTH / 2, GUI_WIDTH / 2);
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(center, 3, Color.WHITE);
            ball.addToGame(this);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.setPaddle(this.paddle);
        }
        this.remaindBalls.increase(this.level.numberOfBalls());
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (!(this.remaindBlocks.getValue() > 0)) {
            this.running = false;
        }
        if (!(this.remaindBalls.getValue() > 0)) {
            this.lives.decrease(1);
            if (this.lives.getValue() <= 0) {
                this.running = false;
                return;
            }
            this.createBalls();
            this.paddle.getCollisionRectangle().setUpperLeft(new Point(GUI_WIDTH / 2 - this.level.paddleWidth() / 2,
                    GUI_HEIGHT - 20 - EDGE_WIDTH));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
         }
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
