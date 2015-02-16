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

        try {
            // reads the create command
            image = readCreateCommand(sysin);

            // reads the next commands
            while (sysin.hasNext()) {
                Operation op = interpretNextCommand(sysin);
                if (op != null)
                    op.execute(image);
            }
        } catch (OperationException ex) {
            System.out.println("OPERATION FAILED");
            //ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            if (ex.getMessage() != null)
                System.out.println(ex.getMessage());
            else
                System.out.println("INPUT MISMATCH");
        } finally {
            sysin.close();
        }
    }

    /**
     * Tries to read and interpret the next command
     *
     * @param scanner The scanner to read from
     * @return The error-code or null if successful
     */
    private static Operation interpretNextCommand(Scanner scanner) {
        String command = scanner.next();

        if (command.equals("clear")) {
            stack.push(image);

            return new ClearOperation();

        } else if (command.equals("load")) {
            stack.push(image);

            String eof = scanner.next();
            scanner.nextLine();

            scanner.useDelimiter(eof);

            String newImage = scanner.next();

            scanner.reset();
            scanner.nextLine();

            return new LoadOperation(newImage);

        } else if (command.equals("print"))

            System.out.println(image.toString());

        else if (command.equals("replace")) {

            stack.push(image);

            char oldChar = readNextChar(scanner);

            char newChar = readNextChar(scanner);

            return new ReplaceOperation(oldChar, newChar);

        } else if (command.equals("undo")) {

            if (stack.empty())
                System.out.println("STACK EMPTY");
            else {
                image = stack.pop();
            }

        } else {
            throw new IllegalArgumentException("UNKNOWN COMMAND");
        }
        return null;
    }


    /**
     * Reads the "create"-command.
     *
     * @param scanner The scanner to read with
     * @return The newly created AsciiImage, otherwise null
     */
    private static AsciiImage readCreateCommand(Scanner scanner) {

        String command = scanner.next();
        if (!command.equals("create"))
            throw new IllegalArgumentException("UNKNOWN COMMAND");

        int width = readNextInt(scanner);

        int heigth = readNextInt(scanner);

        String charset = scanner.next();
        return new AsciiImage(width, heigth, charset);
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
