package hws.hw8;

/**
 * The dot class.
 *
 * @author Patrick Carroll
 * @version 03/25/2024
 */
public class Dot implements Drawable {
    public static final int POINTS = 10;
    protected Point position;

    /**
     * The constructor.
     *
     * @param position the position of the dot.
     *
     */
    public Dot(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
    /**
     * draws dots.
     */

    public void draw() {
        String image = "hws/hw8/img/dot.png";
        DuckManGame.drawImage(position, image);
    }
}
