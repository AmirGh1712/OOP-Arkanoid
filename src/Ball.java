import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
/**
 * The main class for ball.
 *
 * @author Amir Gheriani 212938724
 */
public class Ball implements Sprite {
    private Point location;
    private int radius;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment gameEnvironment;
    private Paddle gamePaddle;
    /**Constructor.
     *
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.location = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
        this.gamePaddle = new Paddle(new Rectangle(new Point(0, 0), 0, 0), 0, 0, 0, null);
    }
    /**Constructor.
     *
     * @param centerX the x value of the center point.
     * @param centerY the y value of the center point.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int centerX, int centerY, int r, java.awt.Color color) {
        this.location = new Point(centerX, centerY);
        this.radius = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
        this.gamePaddle = new Paddle(new Rectangle(new Point(0, 0), 0, 0), 0, 0, 0, null);
    }
    /**Return the x value of the center point.
     *
     * @return the x value of the center point.
     */
    public int getX() {
        return (int) this.location.getX();
    }
    /**Return the y value of the center point.
     *
     * @return the y value of the center point.
     */
    public int getY() {
        return (int) this.location.getY();
    }
    /**Return the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }
    /**Return the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**Draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }
    /**Set the velocity of the ball.
     *
     * @param v the velocity.
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDX(), v.getDY());
    }
    /**Set the velocity of the ball.
     *
     * @param dx the delta x.
     * @param dy the delta y.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }
    /**Set a velocity with random angle.
     *
     * @param speed the speed of the velocity.
     */
    public void setRandomVelocity(double speed) {
        Random rand = new Random();
        int angle = rand.nextInt(360) + 1;
        this.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
    }
    /**Return the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }
    /**Add the velocity to the ball's location.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.location, this.vel.applyToPoint(this.location));
        if (this.gameEnvironment.getClosestCollision(trajectory) == null) {
            this.location = trajectory.end();
        } else {
            // Move the ball to "almost" the hit point.
            Point collosionPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            int x = (int) (collosionPoint.getX() - 1.5 * Integer.signum((int) this.vel.getDX()));
            int y = (int) (collosionPoint.getY() - 1.5 * Integer.signum((int) this.vel.getDY()));
            this.location = new Point(x, y);
            this.vel = this.gameEnvironment.getClosestCollision(trajectory).collisionObject()
                    .hit(this, collosionPoint, this.vel);
        }
    }
    /**Set the game environment of the ball.
    *
    * @param ge the game environment.
    */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }
    @Override
    public void timePassed() {
        this.inPaddle();
        this.moveOneStep();
    }
    /**Add this ball to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**Set the game paddle of the ball.
     *
     * @param paddle the paddle.
     */
    public void setPaddle(Paddle paddle) {
        this.gamePaddle = paddle;
    }
    /**If the ball is in the paddle it move it out.
     */
    public void inPaddle() {
        if (this.gamePaddle.getCollisionRectangle().includePoint(this.location)) {
            Rectangle rectangle = this.gamePaddle.getCollisionRectangle();
            this.location = new Point(this.location.getX(), rectangle.getUpperLeft().getY() - 1);
        }
    }
    /**Remove this ball from the game.
    *
    * @param game the game.
    */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}