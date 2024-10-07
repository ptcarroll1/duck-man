package hws.hw8;

/**
 * The wander Enemy class.
 *
 * @author Patrick Carroll
 * @version 04/04/2024
 */
public class WanderEnemy extends Enemy {
    public static final int RELEASE_TIME = 180;
    /**
     * The wander enemy constructor.
     *
     * @param levelData the level.
     * @param spawnLocation the point.
     *
     */

    public WanderEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }
    /**
     * the update method.
     */

    public void update() {
        if (this.isCenteredOnGrid()) {
            setDesiredDirection(getCurrentDirection().getRandomTurn());
        }
        super.update();
    }

    /**
     * the draw.
     */

    public void draw() {

        String img1 = "hws/hw8/img/brackets.png";
        String img3 = "hws/hw8/img/brackets_scared.png";
        if (this.isEdible()) {
            DuckManGame.drawImage(this.getCurrentPosition(), img3);
        } else {
            DuckManGame.drawImage(this.getCurrentPosition(), img1);
        }
    }


}
