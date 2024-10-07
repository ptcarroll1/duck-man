package hws.hw8;

import java.util.ArrayList;

/**
 * JMU CS themed Pac-Man clone.
 *
 * @author CS159 Faculty and YOUR NAME HERE
 * @version 03/25/2024
 */
public class DuckManGame implements Playable {

    public static final int GRID_SIZE = 32;
    public static final Point PLAYER_SPAWN = new Point(8.5, 5.5);
    public static final Point ENEMY_SPAWN = new Point(8.5, 11.5);

    private boolean running;
    private Level levelMap;
    private Player player;

    private ArrayList<Drawable> drawables;
    private ArrayList<Dot> dots;
    private ArrayList<Updatable> updatables;
    private ArrayList<Enemy> enemies;


    private NumericDisplay score;
    private NumericDisplay lives;

    private final double adj = .5;

    /**
     * Default constructor.
     */
    public DuckManGame() {
        levelMap = new Level(GRID_SIZE);
        drawables = new ArrayList<Drawable>();
        dots = new ArrayList<Dot>();
        updatables = new ArrayList<Updatable>();
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Draw an image on the screen.
     *
     * @param position where to draw the image
     * @param imagePath path to the image file
     */
    public static void drawImage(Point position, String imagePath) {
        GameDriver.picture(position, GRID_SIZE, imagePath);
    }

    /**
     * Add a dot to the game.
     *
     * @param dot the dot to add
     */
    public void addDot(Dot dot) {
        // TODO
        drawables.add(dot);
        dots.add(dot);

    }
    /**
     * adds the enemy.
     *
     * @param enemy the enemy.
     *
     */

    public void addEnemy(Enemy enemy) {
        drawables.add(enemy);
        updatables.add(enemy);
        enemies.add(enemy);

    }

    /**
     * Update the game state when the player collides with dots and enemies.
     */
    public void handlePlayerCollisions() {
        ArrayList<Dot> toRemove = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            if (player.collidesWith(dots.get(i))) {
                if (dots.get(i) instanceof MagicEgg) {
                    score.setValue(score.getValue() + MagicEgg.POINTS);
                    for (Enemy enemy : enemies) {
                        enemy.makeEdible();
                    }
                } else {
                    score.setValue(score.getValue() + Dot.POINTS);
                }
                toRemove.add(dots.get(i));
            }
        }
        for (int j = 0; j < toRemove.size(); j++) {
            dots.remove(toRemove.get(j));
            drawables.remove(toRemove.get(j));
        }
        if (dots.size() == 0) {
            running = false;
        }

        for (int n = 0; n < enemies.size(); n++) {
            if (player.collidesWith(enemies.get(n))) {
                if (enemies.get(n).isEdible()) {
                    score.setValue(score.getValue() + Enemy.POINTS);
                    enemies.get(n).reset();
                } else if ((!enemies.get(n).isEdible())) {
                    lives.setValue(lives.getValue() - 1);
                    player.reset();
                }
            }
            if (lives.getValue() <= 0) {
                running = false;
            }
        }
    }
    /**
     * Update the game state when the player collides with dots and enemies.
     */

    public void handleEnemyCollisions() {
        for (int n = 0; n < enemies.size(); n++) {
            if (player.collidesWith(enemies.get(n))) {
                if (enemies.get(n).isEdible()) {
                    score.setValue(score.getValue() + Enemy.POINTS);
                    enemies.get(n).reset();
                }
                if ((!enemies.get(n).isEdible())) {
                    lives.setValue(lives.getValue() - 1);
                    player.reset();
                }
            }
            if (lives.getValue() <= 0) {
                running = false;
            }
        }
    }

    /**
     * Iterate through the level map and create a new dot/egg object if the map
     * has a dot/egg at that position.
     */
    public void spawnNewDots() {

        for (int i = 0; i < levelMap.getHeight(); i++) {
            for (int j = 0; j < levelMap.getHeight(); j++) {
                if (levelMap.isDot(i, j)) {
                    Dot d = new Dot(new Point((i + adj), (j + adj)));
                    addDot(d);

                }
                if (levelMap.isEgg(i, j)) {
                    MagicEgg e = new MagicEgg(new Point(i + adj, j + adj));
                    addDot(e);
                }

            }
        }

    }

    /**
     * Create and add a player to the game.
     */
    public void spawnNewPlayer() {
        this.player = new Player(levelMap, PLAYER_SPAWN);
        drawables.add(player);
        updatables.add(player);
    }

    /**
     * Create and add enemies to the game.
     */
    public void spawnNewEnemies() {
        WallEnemy wall = new WallEnemy(levelMap, ENEMY_SPAWN);
        WanderEnemy wand = new WanderEnemy(levelMap, ENEMY_SPAWN);
        ChaseEnemy chase = new ChaseEnemy(levelMap, ENEMY_SPAWN, player);
        addEnemy(wall);
        addEnemy(wand);
        addEnemy(chase);


    }

    @Override
    public void startGame() {
        Point upperLeft = new Point(10, GameDriver.SCREEN_HEIGHT - 10);
        score = new NumericDisplay(upperLeft, "Points", 0);
        drawables.add(score);

        Point upperRight = new Point(GameDriver.SCREEN_WIDTH - 80,
                GameDriver.SCREEN_HEIGHT - 10);
        lives = new NumericDisplay(upperRight, "Lives", 3);
        drawables.add(lives);

        spawnNewDots();
        spawnNewPlayer();
        spawnNewEnemies();

        running = true;
    }

    @Override
    public void updateAll() {
        if (!running) {
            return;
        }
        for (int i = 0; i < updatables.size(); i++) {
            updatables.get(i).update();
        }

        handlePlayerCollisions();

    }

    @Override
    public void drawAll() {
        levelMap.draw();
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
}
