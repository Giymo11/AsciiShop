import java.util.LinkedList;
import java.util.List;

/**
 * The object representing an AsciiImage.
 */
public class AsciiImage {
    private char[][] image;
    private String charset;

    public AsciiImage(int width, int heigth, String charset) {
        if (width < 0 || heigth < 0)
            throw new IllegalArgumentException();

        if (charset == null || charset.equals(""))
            throw new IllegalArgumentException();

        for (char c : charset.toCharArray()) {
            if (charset.indexOf(c) != charset.lastIndexOf(c))
                throw new IllegalArgumentException();
        }

        image = new char[heigth][width];
        this.charset = charset;

        try {
            new ClearOperation().execute(this);
        } catch (OperationException e) {
            // should never happen
            e.printStackTrace();
            assert false;
        }
    }

    public AsciiImage(AsciiImage asciiImage) {
        image = new char[asciiImage.getHeight()][asciiImage.getWidth()];
        charset = asciiImage.getCharset();

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

    public char getPixel(int x, int y) {
        boundCheck(x, y);
        return image[y][x];
    }

    private char getPixel(AsciiPoint p) {
        return getPixel(p.getX(), p.getY());
    }

    private void boundCheck(int x, int y) {
        if (!isInsideBounds(x, y))
            throw new IndexOutOfBoundsException();
    }

    public void setPixel(int x, int y, char color) {
        boundCheck(x, y);
        colorCheck(color);
        image[y][x] = color;
    }

    private void colorCheck(char color) {
        if (!charset.contains("" + color))
            throw new IndexOutOfBoundsException();
    }

    public void setPixel(AsciiPoint point, char color) {
        setPixel(point.getX(), point.getY(), color);
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsciiImage other = (AsciiImage) o;

        if (!other.getCharset().equals(charset))
            return false;

        if (other.getHeight() != getHeight() || other.getWidth() != getWidth())
            return false;

        for (int x = 0; x < getWidth(); ++x) {
            for (int y = 0; y < getHeight(); ++y) {
                if (other.getPixel(x, y) != getPixel(x, y))
                    return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;

        for (int x = 0; x < getWidth(); ++x) {
            for (int y = 0; y < getHeight(); ++y) {
                sum += getPixel(x, y);
            }
        }

        return sum;
    }

    public int getUniqueChars() {
        String uniques = "";

        for (int x = 0; x < getWidth(); ++x) {
            for (int y = 0; y < getHeight(); ++y) {
                char c = getPixel(x, y);
                if (!uniques.contains("" + c))
                    uniques += c;
            }
        }

        return uniques.length();
    }
}
