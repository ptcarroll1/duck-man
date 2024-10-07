package hws.hw8;

/**
 * JMU CS themed Pac-Man clone.
 *
 * @author CS159 Faculty and Patrick Carroll
 * @version 03/25/2024
 */
public class Player extends Actor {
    public static final double PLAYER_SPEED = 0.07;

    /**
     * The constructor of this class.
     *
     * @param level the level.
     * @param spawnLocation the location of spawn.
     *
     */

    public Player(Level level, Point spawnLocation) {
        super(level, spawnLocation, Direction.LEFT, PLAYER_SPEED);

    }

    @Override
    public void draw() {
        if (super.getCurrentDirection() == Direction.LEFT) {
            DuckManGame.drawImage(super.getCurrentPosition(),
                    "hws/hw8/img/duck_left.png");
        }
        if (super.getCurrentDirection() == Direction.RIGHT) {
            DuckManGame.drawImage(super.getCurrentPosition(),
                    "hws/hw8/img/duck_right.png");
        }
        if (super.getCurrentDirection() == Direction.UP) {
            DuckManGame.drawImage(super.getCurrentPosition(),
                    "hws/hw8/img/duck_up.png");
        }
        if (super.getCurrentDirection() == Direction.DOWN) {
            DuckManGame.drawImage(super.getCurrentPosition(),
                    "hws/hw8/img/duck_down.png");
        }

    }

    /**
     * updates the direction.
     */
    public void update() {
        if (GameDriver.upPressed()) {
            setDesiredDirection(Direction.UP);
        }
        if (GameDriver.downPressed()) {
            setDesiredDirection(Direction.DOWN);
        }
        if (GameDriver.rightPressed()) {
            setDesiredDirection(Direction.RIGHT);
        }
        if (GameDriver.leftPressed()) {
            setDesiredDirection(Direction.LEFT);
        }
        super.update();

    }
    /**
     * The collides with method.
     *
     * @param dot the dot.
     * @return a boolean value.
     */

    public boolean collidesWith(Dot dot) {
        if (this.getCurrentPosition().distance(dot.position) < 0.5) {
            return true;
        }
        return false;
    }
}
