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
        AsciiImage image = readImage(scanner, expectedImageLength);
        if(image == null) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        // reads any fill commands
        while(scanner.hasNext()) {
            String word = scanner.next();
            int x, y;
            char c;

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
                // sanity check
                if(!image.isInsideBounds(x, y)) {
                    System.out.println("OPERATION FAILED");
                    return;
                }
                image.fill(x, y, c);
            } else if(word.equals("transpose")) {
                image.transpose();
            } else if(word.equals("flip-v")) {
                image.flipV();
            } else if(word.equals("uniqueChars")) {
                System.out.println(image.getUniqueChars());
            } else {
                System.out.println("INPUT MISMATCH");
                return;
            }

        }

        // Prints the finished input
        System.out.println(image.toString());
        // Prints the dimensions of the image
        System.out.println(image.getWidth() + " " + image.getHeigth());
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
    private static AsciiImage readImage(Scanner scanner, int expectedImageLength) {
        AsciiImage image = new AsciiImage();
        for(int rowCount = 0; rowCount < expectedImageLength && scanner.hasNext(); ++rowCount) {
            if(!image.addLine(scanner.next()))
                return null;
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
}
