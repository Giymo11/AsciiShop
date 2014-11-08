/**
 * The object representing an AsciiImage.
 */
public class AsciiImage {
    private char[][] image;

    public AsciiImage(int width, int heigth) {
        image = new char[heigth][width];
        clear();
        System.out.println("Height: " + getHeight() + ", Width: " + getWidth());
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
        fillOldWithNew(x, y+1, oldColor, c);
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
        //TODO: implement this method
    }

    public void replace(char oldChar, char newChar) {
        for (int x = 0; x < getWidth(); ++x)
            for (int y = 0; y < getHeight(); ++y)
                if (pixelAt(x, y) == oldChar)
                    setPixelAt(x, y, newChar);
    }
}
