import java.util.Scanner;

/**
 * @author Benjamin Potzmann
 *
 * Reads an ASCII-picture from standard input and prints the width & length.
 * Reads fill commands and does floodfills if necessary.
 */
public class AsciiShop {

    public static void main(String[] args) {

        Scanner sysin = new Scanner(System.in);

        // reads the create command
        AsciiImage image = readCreateCommand(sysin);
        if (image == null) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        while (sysin.hasNext()) {
            String command = sysin.next();

            if (command.equals("clear"))
                image.clear();
            else if (command.equals("line")) {
                int x0 = readNextInt(sysin);
                if (x0 <= 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int y0 = readNextInt(sysin);
                if (y0 <= 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int x1 = readNextInt(sysin);
                if (x1 <= 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int y1 = readNextInt(sysin);
                if (y1 <= 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                char color = readNextChar(sysin);
                if (color == ' ') {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                image.drawLine(x0, y0, x1, y1, color);
            }
        }

        // reads any fill commands
        while (sysin.hasNext()) {
            String word = sysin.next();
            int x, y;
            char c;

            if(word.equals("fill")) {
                x = readNextInt(sysin);
                if(x < 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                y = readNextInt(sysin);
                if(y < 0) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                c = readNextChar(sysin);
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
            } else if(word.equals("symmetric-h")) {
                System.out.println(image.isSymmetricH());
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

    private static AsciiImage readCreateCommand(Scanner scanner) {
        if (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("create")) {
                int width = readNextInt(scanner);
                if (width > 0) {
                    int heigth = readNextInt(scanner);
                    if (heigth > 0)
                        return new AsciiImage(width, heigth);
                }
            }
        }
        return null;
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
