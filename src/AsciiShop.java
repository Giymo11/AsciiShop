import java.util.Scanner;

/**
 * @author Benjamin Potzmann
 *
 * Reads an ASCII-picture from standard input and prints the width & length.
 * Reads fill commands and does floodfills if necessary.
 */
public class AsciiShop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // reads the read-command
        int expectedImageLength = readReadCommand(scanner);
        if(expectedImageLength == -1) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        // reads the image
        String[] image = readImage(scanner, expectedImageLength);
        if(image == null) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        // reads any fill commands
        while(scanner.hasNext()) {
            String word = scanner.next();
            int x = -1;
            int y = -1;
            char c = ' ';

            if(word.equals("fill")) {
                x = readNextInt(scanner);
                if(x < 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                y = readNextInt(scanner);
                if(y < 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                c = readNextChar(scanner);
                if(c == ' ') {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
            }
            // sanity check
            if(!isInsideBounds(image, x, y)) {
                System.out.println("OPERATION FAILED");
            }
            fill(image, x, y, c);
        }

        // Prints the finished input
        for(String row : image) {
            System.out.println(row);
        }
        // Prints the dimensions of the image
        System.out.println(image[0].length() + " " + image.length);
    }

    /**
     * Reads the next character.
     * @return The read character or -1 for invalid input.
     */
    private static char readNextChar(Scanner scanner) {
        if(scanner.hasNext()) {
            String nextWord = scanner.next();
            if(nextWord.length() == 1)
                return nextWord.charAt(0);
        }
        return ' ';
    }

    /**
     * Reads the next integer.
     * @return The read integer or -1 for invalid input.
     */
    private static int readNextInt(Scanner scanner) {
        if(scanner.hasNextInt())
            return scanner.nextInt();
        return -1;
    }

    /**
     * Reads the image.
     * @return The read image or null for invalid input.
     */
    private static String[] readImage(Scanner scanner, int expectedImageLength) {
        String[] image = new String[expectedImageLength];
        for(int rowCount = 0; rowCount < expectedImageLength && scanner.hasNext(); ++rowCount) {
            image[rowCount] = scanner.next();
            if(image[rowCount].length() != image[0].length()) {
                return null;
            }
        }
        return image;
    }

    /**
     * Reads the "read"-command.
     * @return The expected length of the image or -1 for invalid input.
     */
    private static int readReadCommand(Scanner scanner) {
        if(scanner.hasNext()) {
            String word = scanner.next();
            if(word.equals("read") && scanner.hasNextInt()) {
                int expectedPictureLength = scanner.nextInt();
                if(expectedPictureLength > 0)
                    return expectedPictureLength;
            }
        }
        return -1;
    }

    /**
     * Floodfill. Overwrites the old color with the new one.
     */
    public static void fill(String[] image, int x, int y, char c) {
        char oldColor = pixelAt(image, x, y);
        String newRow = image[y].substring(0, x) + c + image[y].substring(x+1, image[0].length());
        image[y] = newRow;
        fillOldWithNew(image, x + 1, y, oldColor, c);
        fillOldWithNew(image, x - 1, y, oldColor, c);
        fillOldWithNew(image, x, y+1, oldColor, c);
        fillOldWithNew(image, x, y - 1, oldColor, c);
    }

    /**
     * Checks the constraints for the floodfill.
     */
    public static void fillOldWithNew(String[] image, int x, int y, char oldColor, char newColor) {
        if(isInsideBounds(image, x, y) && pixelAt(image, x, y) == oldColor)
            fill(image, x, y, newColor);
    }

    private static char pixelAt(String[] image, int x, int y) {
        return image[y].charAt(x);
    }

    private static boolean isInsideBounds(String[] image, int x, int y) {
        return x >= 0 && y >= 0 && x < image[0].length() && y < image.length;
    }
}
