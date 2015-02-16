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
    private static AsciiStack stack = new AsciiStack();

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

        if (command.equals("clear")) {
            stack.push(image);
            image.clear();
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
        } else if (command.equals("undo")) {

            if (stack.empty())
                System.out.println("STACK EMPTY");
            else {
                image = stack.pop();
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
                        if (scanner.hasNext()) {
                            String charset = scanner.next();
                            return new AsciiImage(width, heigth, charset);
                        }
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
