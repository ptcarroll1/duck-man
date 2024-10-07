package hws.hw8;

/**
 * The chase Enemy class.
 *
 * @author Patrick Carroll
 * @version 04/04/2024
 */
public class ChaseEnemy extends Enemy {
    public static final int RELEASE_TIME = 0;
    private Player player;

    /**
     * The chase enemy constructor.
     *
     * @param levelData the level.
     * @param spawnLocation the point.
     * @param player the player
     *
     */

    public ChaseEnemy(Level levelData, Point spawnLocation, Player player) {
        super(levelData, spawnLocation, RELEASE_TIME);
        this.player = player;
    }
    /**
     * the update method.
     */

    public void update() {
        if (isCenteredOnGrid()) {
            Point playerPos = player.getCurrentPosition();
            Point enemyPos = getCurrentPosition();

            if (getCurrentDirection().isUpDown()) {
                if (playerPos.getX() < enemyPos.getX()) {
                    setDesiredDirection(Direction.LEFT);
                } else if (playerPos.getX() > enemyPos.getX()) {
                    setDesiredDirection(Direction.RIGHT);
                } else {
                    setDesiredDirection(getCurrentDirection());
                }

            } else {
                double playerY = playerPos.getY();
                double enemyY = enemyPos.getY();
                if (playerY > enemyY) {
                    setDesiredDirection(Direction.UP);
                } else if (playerY < enemyY) {
                    setDesiredDirection(Direction.DOWN);
                } else {
                    setDesiredDirection(getCurrentDirection());
                }
            }
        }
        super.update();
    }

    @Override
    public void draw() {
        String img4 = "hws/hw8/img/semicolons.png";
        String img5 = "hws/hw8/img/semicolons_scared.png";
        if (this.isEdible()) {
            DuckManGame.drawImage(this.getCurrentPosition(), img5);
        } else {
            DuckManGame.drawImage(this.getCurrentPosition(), img4);
        }
    }




}
