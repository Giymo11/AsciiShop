import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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


        // reads the next commands
        while (sysin.hasNext()) {
            String errorCode = interpretNextCommand(sysin, image);

            if (errorCode != null) {
                System.out.println(errorCode);
                return;
            }
        }

    }

    /**
     * Tries to read and interpret the next command
     *
     * @param scanner The scanner to read from
     * @param image   The image to execute the command on
     * @return The error-code or null if successful
     */
    private static String interpretNextCommand(Scanner scanner, AsciiImage image) {
        String command = scanner.next();

        if (command.equals("clear"))
            image.clear();
        else if (command.equals("line")) {
            int x0 = readNextInt(scanner);

            if (x0 < 0)
                return "INPUT MISMATCH";

            int y0 = readNextInt(scanner);
            if (y0 < 0)
                return "INPUT MISMATCH";

            int x1 = readNextInt(scanner);
            if (x1 < 0)
                return "INPUT MISMATCH";

            int y1 = readNextInt(scanner);
            if (y1 < 0)
                return "INPUT MISMATCH";

            char color = readNextChar(scanner);
            if (color == ' ')
                return "INPUT MISMATCH";

            if (!image.isInsideBounds(x0, y0) || !image.isInsideBounds(x1, y1))
                return "INPUT MISMATCH";

            image.drawLine(x0, y0, x1, y1, color);

        } else if (command.equals("load")) {
            String eof = scanner.next();
            List<String> newImage = new LinkedList<String>();

            while (scanner.hasNext()) {
                String line = scanner.next();

                if (line.equals(eof))
                    break;
                else if (line.length() != image.getWidth())
                    return "INPUT MISMATCH";
                else
                    newImage.add(line);
            }

            if (newImage.size() != image.getHeight())
                return "INPUT MISMATCH";
            else
                load(newImage, image);

        } else if (command.equals("print"))
            System.out.println(image.toString());
        else if (command.equals("replace")) {
            char oldChar = readNextChar(scanner);
            if (oldChar == ' ')
                return "INPUT MISMATCH";

            char newChar = readNextChar(scanner);
            if (newChar == ' ')
                return "INPUT MISMATCH";

            image.replace(oldChar, newChar);
        } else if (command.equals("transpose"))
            image.transpose();
        else if (command.equals("fill")) {
            int x = readNextInt(scanner);
            if (x == -1)
                return "INPUT MISMATCH";

            int y = readNextInt(scanner);
            if (y == -1)
                return "INPUT MISMATCH";

            if (!image.isInsideBounds(x, y))
                return "OPERATION FAILED";

            char color = readNextChar(scanner);
            if (color == ' ') {
                return "INPUT MISMATCH";
            }

            image.fill(x, y, color);
        } else {
            return "UNKNOWN COMMAND";
        }
        return null;
    }

    /**
     * loads a list of strings into an AsciiImage
     */
    private static void load(List<String> newImage, AsciiImage oldImage) {
        ListIterator<String> newImageIterator = newImage.listIterator();
        while (newImageIterator.hasNext()) {
            String line = newImageIterator.next();
            for (int i = 0; i < line.length(); ++i) {
                oldImage.setPixelAt(i, newImageIterator.previousIndex(), line.charAt(i));
            }
        }
    }

    /**
     * Reads the "create"-command.
     *
     * @param scanner The scanner to read with
     * @return The newly created AsciiImage, otherwise null
     */
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
}
