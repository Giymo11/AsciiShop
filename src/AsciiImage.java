import java.util.LinkedList;
import java.util.List;

/**
 * The object representing an AsciiImage.
 */
public class AsciiImage {
    private char[][] image;
    private String charset;

    public AsciiImage(int width, int heigth, String charset) {
        image = new char[heigth][width];
        this.charset = charset;
        clear();
    }

    public AsciiImage(AsciiImage asciiImage) {
        image = new char[asciiImage.getHeight()][asciiImage.getWidth()];
        for (int y = 0; y < getHeight(); ++y)
            for (int x = 0; x < getWidth(); ++x)
                setPixel(x, y, asciiImage.getPixel(x, y));
    }

    public int getHeight() {
        return image.length;
    }

    public int getWidth() {
        return image[0].length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x)
                builder.append(getPixel(x, y));
            builder.append('\n');
        }
        return builder.toString();
    }

    private char getPixel(int x, int y) {
        return image[y][x];
    }

    private char getPixel(AsciiPoint p) {
        return getPixel(p.getX(), p.getY());
    }

    public void setPixel(int x, int y, char color) {
        image[y][x] = color;
    }

    public void setPixel(AsciiPoint point, char color) {
        setPixel(point.getX(), point.getY(), color);
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
    }

    private boolean isInsideBounds(AsciiPoint point) {
        return isInsideBounds(point.getX(), point.getY());
    }

    public void clear() {
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                setPixel(x, y, '.');
    }

    public void replace(char oldChar, char newChar) {
        for (AsciiPoint p : getPointList(oldChar))
            setPixel(p, newChar);
    }

    public List<AsciiPoint> getPointList(char color) {
        List<AsciiPoint> points = new LinkedList<AsciiPoint>();
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                if (getPixel(x, y) == color)
                    points.add(new AsciiPoint(x, y));
        return points;
    }

    public String getCharset() {
        return charset;
    }
}
