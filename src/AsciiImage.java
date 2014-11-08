/**
 * The object representing an AsciiImage.
 */
public class AsciiImage {
    private String image;
    private int heigth, width;

    public boolean addLine(String line) {
       if(width == 0) {
           if (line.length() > 0){
               width = line.length();
               heigth = 1;
           }
           else
               return false;
           image = line;
       } else if(line.length() == width) {
           image += line;
           ++heigth;
       }
        else
           return false;
       return true;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int count = 0; count < image.length(); ++count) {
            if(count % width == 0 && count != 0)
                builder.append("\n");
            builder.append(image.charAt(count));
        }
        return builder.toString();
    }

    /**
     * checks if the image is a palindrom (horizontally symmetric)
     */
    public boolean isSymmetricH() {
        for(int y = 0; y < heigth; ++y) {
            for(int x = 0; x < width/2; ++x) {
                if(pixelAt(x, y) != pixelAt(width-x-1, y))
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @return the number of unique colors in the image
     */
    public int getUniqueChars() {
        String uniqueChars = "";
        for(char c : image.toCharArray()) {
            if(uniqueChars.indexOf(c) == -1)
                uniqueChars += c;
        }
        return uniqueChars.length();
    }

    /**
     * flips the image vertically -> (╯°□°）╯︵ ┻━┻)
     */
    public void flipV() {
        StringBuilder builder = new StringBuilder();
        for(int y = heigth - 1; y >= 0; --y) {
            builder.append(lineAt(y));
        }
        image = builder.toString();
    }

    /**
     * swaps rows and columns
     */
    public void transpose() {
        StringBuilder builder = new StringBuilder();
        for(int x = 0; x < width; ++x)
            for(int y = 0; y < heigth; ++y)
                builder.append(pixelAt(x, y));
        int oldHeigth = heigth;
        heigth = width;
        width = oldHeigth;
        image = builder.toString();
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
        return image.charAt(y * width + x);
    }

    private void setPixelAt(int x, int y, char color) {
        image = image.substring(0, y * width + x) + color + image.substring(y * width + x + 1);
    }

    private String lineAt(int y) {
        StringBuilder builder = new StringBuilder();
        for(int x = 0; x < width; ++x) {
            builder.append(pixelAt(x, y));
        }
        return builder.toString();
    }

    private void setLineAt(int y, String line) {
        for(int count = 0; count < line.length(); ++count) {
            setPixelAt(count, y, line.charAt(count));
        }
    }

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < heigth;
    }
}
