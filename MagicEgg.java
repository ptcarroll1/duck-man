package hws.hw8;

/**
 * The Magic Egg class.
 *
 * @author Patrick Carroll
 * @version 03/25/2024
 */
public class MagicEgg extends Dot {

    public static final int POINTS = 50;
    /**
     * The constructor.
     *
     * @param position the position of the egg.
     *
     */

    public MagicEgg(Point position) {
        super(position);
    }

    /**
     * draws dots.
     */

    public void draw() {
        String image1 = "hws/hw8/img/egg.png";
        DuckManGame.drawImage(position, image1);
    }

}
