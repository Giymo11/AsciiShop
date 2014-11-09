/**
 * The object representing an AsciiImage.
 */
public class AsciiImage {
    private char[][] image;

    public AsciiImage(int width, int heigth) {
        image = new char[heigth][width];
        clear();
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
                builder.append(pixelAt(x, y));
            builder.append('\n');
        }
        return builder.toString();
    }

    /**
     * swaps rows and columns
     */
    public void transpose() {
        AsciiImage newImage = new AsciiImage(getHeight(), getWidth());
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                newImage.setPixelAt(y, x, pixelAt(x, y));

        image = newImage.image;
    }

    /**
     * Floodfill. Overwrites the old color with the new one.
     */
    public void fill(int x, int y, char c) {
        char oldColor = pixelAt(x, y);
        setPixelAt(x, y, c);
        fillOldWithNew(x + 1, y, oldColor, c);
        fillOldWithNew(x - 1, y, oldColor, c);
        fillOldWithNew(x, y + 1, oldColor, c);
        fillOldWithNew(x, y - 1, oldColor, c);
    }

    /**
     * Checks the constraints for the floodfill.
     */
    public void fillOldWithNew(int x, int y, char oldColor, char newColor) {
        if(isInsideBounds(x, y) && pixelAt(x, y) == oldColor)
            fill(x, y, newColor);
    }

    private char pixelAt(int x, int y) {
        return image[y][x];
    }

    public void setPixelAt(int x, int y, char color) {
        image[y][x] = color;
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
    }

    public void clear() {
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                setPixelAt(x, y, '.');
    }

    public void drawLine(int x0, int y0, int x1, int y1, char color) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;

        if (absoluteValueOf(deltaX) <= absoluteValueOf(deltaY)) {
            transpose();
            // invert y and x
            if (deltaY <= 0)
                //swap the starting and ending points
                drawLineOntoImage(y1, x1, y0, x0, color);
            else
                drawLineOntoImage(y0, x0, y1, x1, color);
            transpose();
        } else {
            //swap the starting and ending points
            if (deltaX <= 0)
                drawLineOntoImage(x1, y1, x0, y0, color);
            else
                drawLineOntoImage(x0, y0, x1, y1, color);
        }
    }

    private int absoluteValueOf(int number) {
        if (number < 0)
            return number * -1;
        return number;
    }

    private void drawLineOntoImage(int x0, double y0, int x1, int y1, char color) {
        setPixelAt(x0, (int) Math.round(y0), color);
        if (x0 != x1 || (float) y0 != y1)
            if (x1 - x0 == 0)
                drawLineOntoImage(x0 + 1, y0, x1, y1, color);
            else
                drawLineOntoImage(x0 + 1, y0 + (y1 - y0) / (x1 - x0), x1, y1, color);
    }

    public void replace(char oldChar, char newChar) {
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                if (pixelAt(x, y) == oldChar)
                    setPixelAt(x, y, newChar);
    }

    public AsciiPoint getCentroid(char c) {
        //TODO: implement this method
        return null;
    }

    public void growRegion(char c) {
        //TODO: implement this method
    }

    public void straightenRegion(char c) {
        //TODO: implement this method
    }
}
