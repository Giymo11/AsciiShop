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

    private static AsciiImage image;
    private static AsciiStack stack = new AsciiStack(3);
    public static void main(String[] args) {

        Scanner sysin = new Scanner(System.in);

        // reads the create command
        image = readCreateCommand(sysin);
        if (image == null) {
            System.out.println("INPUT MISMATCH");
            return;
        }


        // reads the next commands
        while (sysin.hasNext()) {
            String errorCode = interpretNextCommand(sysin);

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
     * @return The error-code or null if successful
     */
    private static String interpretNextCommand(Scanner scanner) {
        String command = scanner.next();

        if (command.equals("centroid")) {
            char c = readNextChar(scanner);
            if (c == ' ')
                return "INPUT MISMATCH";
            System.out.println(image.getCentroid(c));
        } else if (command.equals("clear")) {
            stack.push(image);
            image.clear();
        } else if (command.equals("grow")) {
            stack.push(image);
            char c = readNextChar(scanner);
            if (c == ' ')
                return "INPUT MISMATCH";
            image.growRegion(c);
        } else if (command.equals("line")) {
            stack.push(image);
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
            stack.push(image);
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
            stack.push(image);
            char oldChar = readNextChar(scanner);
            if (oldChar == ' ')
                return "INPUT MISMATCH";

            char newChar = readNextChar(scanner);
            if (newChar == ' ')
                return "INPUT MISMATCH";

            image.replace(oldChar, newChar);
        } else if (command.equals("straighten")) {
            stack.push(image);
            char c = readNextChar(scanner);
            if (c == ' ')
                return "INPUT MISMATCH";
            image.straightenRegion(c);
        } else if (command.equals("transpose")) {
            stack.push(image);
            image.transpose();
        } else if (command.equals("fill")) {
            stack.push(image);
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
        } else if (command.equals("undo")) {

            if (stack.empty())
                System.out.println("STACK EMPTY");
            else {
                image = stack.pop();
                System.out.println("STACK USAGE " + stack.size() + "/" + stack.capacity());
            }

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
                oldImage.setPixel(i, newImageIterator.previousIndex(), line.charAt(i));
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
