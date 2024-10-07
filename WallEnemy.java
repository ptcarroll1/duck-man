package hws.hw8;

/**
 * The wall Enemy class.
 *
 * @author Patrick Carroll
 * @version 04/04/2024
 */
public class WallEnemy extends Enemy {
    public static final int RELEASE_TIME = 360;

    /**
     * The wall enemy constructor.
     *
     * @param levelData the level.
     * @param spawnLocation the point.
     *
     */
    public WallEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }
    /**
     * the draw method.
     */

    public void draw() {

        String img = "hws/hw8/img/parentheses.png";
        String img2 = "hws/hw8/img/parentheses_scared.png";

        if (isTouchingWall(getCurrentDirection())) {
            setDesiredDirection(getCurrentDirection().getRandomTurn());
        }
        if (isEdible()) {
            DuckManGame.drawImage(this.getCurrentPosition(), img2);
        } else {
            DuckManGame.drawImage(this.getCurrentPosition(), img);
        }
    }
}
