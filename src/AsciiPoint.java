/**
 * Created by Giymo11 on 09.11.2014.
 */
public class AsciiPoint {
    private int x, y;

    public AsciiPoint(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}
