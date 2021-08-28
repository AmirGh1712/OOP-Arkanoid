import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The main class for paddle that implements sprite and collidable.
 *
 * @author Amir Gheriani
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int startX;
    private int endX;
    private int speed;
    /**Constructor.
     *
     * @param rectangle the rectangle of the paddle.
     * @param startX the minimum x the paddle can be in.
     * @param endX the maximum x the paddle can be in.
     * @param speed the speed of the paddle.
     * @param ks the keyboard sensor that control the paddle.
     */
    public Paddle(Rectangle rectangle, int startX, int endX, int speed, KeyboardSensor ks) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.startX = startX;
        this.endX = endX;
        this.speed = speed;
        this.keyboard = ks;
    }
    /**Move the paddle to the left.
     */
    public void moveLeft() {
        Point upperLeft = this.rectangle.getUpperLeft();
        int x = (int) upperLeft.getX() - speed;
        if (x < this.startX) {
            x = this.startX;
        }
        this.rectangle.setUpperLeft(new Point(x, upperLeft.getY()));
    }
    /**Move the paddle to the right.
     */
    public void moveRight() {
        Point upperLeft = this.rectangle.getUpperLeft();
        int x = (int) upperLeft.getX() + speed;
        if (x + this.rectangle.getWidth() > this.endX) {
            x = (int) (this.endX - this.rectangle.getWidth());
        }
        this.rectangle.setUpperLeft(new Point(x, upperLeft.getY()));
    }
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface, Color.ORANGE);
        this.rectangle.drawLineOn(surface, Color.BLACK);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX(), dy = currentVelocity.getDY();
        Point upperLeft = this.rectangle.getUpperLeft();
        if (Math.round(collisionPoint.getX()) == upperLeft.getX()
                || Math.round(collisionPoint.getX()) == upperLeft.getX() + this.rectangle.getWidth()) {
            dx = -dx;
        } else if (Math.round(collisionPoint.getY()) == upperLeft.getY() + this.rectangle.getHeight()) {
            dy = -dy;
        } else if (Math.round(collisionPoint.getY()) == upperLeft.getY()) {
            int region = (int) ((collisionPoint.getX() - upperLeft.getX()) / (this.rectangle.getWidth() / 5)) + 1;
            switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            case 2:
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            case 3:
                dy = -dy;
                break;
            case 4:
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            default:
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        return new Velocity(dx, dy);
    }
    /**Add this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}