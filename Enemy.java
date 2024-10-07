package hws.hw8;

/**
 * The Enemy class.
 *
 * @author Patrick Carroll
 * @version 04/04/2024
 */
public abstract class Enemy extends Actor {
    public static final double ENEMY_SPEED = 0.06;
    public static final int EDIBLE_DURATION = 300;
    public static final int POINTS = 200;
    protected int timeUntilReleased;
    protected int timeUntilNormal;
    /**
     * The enemy constructor.
     *
     * @param levelData the level.
     * @param spawnLocation the point.
     * @param timeUntilReleased the time.
     *
     */

    public Enemy(Level levelData, Point spawnLocation, int timeUntilReleased) {
        super(levelData, spawnLocation, Direction.UP, ENEMY_SPEED);
        this.timeUntilReleased = timeUntilReleased;
        this.timeUntilNormal = 0;
    }
    /**
     * the make edible method.
     */

    public void makeEdible() {
        if (timeUntilReleased == 0) {
            this.timeUntilNormal = EDIBLE_DURATION;
            setDesiredDirection(this.getCurrentDirection().getOpposite());

        }
    }
    /**
     * the is edible method.
     *
     * @return a boolean value.
     *
     */

    public boolean isEdible() {
        return timeUntilNormal > 0;
    }
    /**
     * the reset method.
     */

    public void reset() {
        super.reset();
        this.timeUntilNormal = 0;
    }
    /**
     * the update method.
     */

    public void update() {
        if (timeUntilReleased > 0) {
            timeUntilReleased--;
        } else {
            if (isEdible()) {
                timeUntilNormal--;
            }
            if (isStopped()) {
                setDesiredDirection(getDesiredDirection().getRandomTurn());
            }
            super.update();
        }
    }



}
