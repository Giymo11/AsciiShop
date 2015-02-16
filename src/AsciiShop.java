import java.util.HashMap;
import java.util.Map;
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
    private static Map<String, Factory> factoryMap = new HashMap<String, Factory>();

    public static void main(String[] args) {

        Scanner sysin = new Scanner(System.in);

        try {
            // reads the create command
            image = readCreateCommand(sysin);

            initFactoryMap();

            // reads the next commands
            while (sysin.hasNext()) {
                Operation op = interpretNextCommand(sysin);
                if (op != null) {
                    stack.push(image);
                    image = op.execute(image);
                }
            }
        } catch (OperationException ex) {
            System.out.println("OPERATION FAILED");
        } catch (IllegalArgumentException ex) {
            if (ex.getMessage() != null)
                System.out.println(ex.getMessage());
            else
                System.out.println("INPUT MISMATCH");

        } catch (Exception ex) {
            System.out.println("INPUT MISMATCH");
        } finally {
            sysin.close();
        }
    }

    private static void initFactoryMap() {
        factoryMap.put("clear", new ClearFactory());
        factoryMap.put("load", new LoadFactory());
        factoryMap.put("replace", new ReplaceFactory());
        factoryMap.put("filter", new FilterFactory());
        factoryMap.put("binary", new BinaryFactory());
    }

    /**
     * Tries to read and interpret the next command
     *
     * @param scanner The scanner to read from
     * @return The error-code or null if successful
     */
    private static Operation interpretNextCommand(Scanner scanner) throws FactoryException {
        String command = scanner.next();

        if (command.equals("print")) {

            System.out.println(image.toString());

        } else if (command.equals("undo")) {

            if (stack.empty())
                System.out.println("STACK EMPTY");
            else
                image = stack.pop();

        } else {
            Factory commandFactory = factoryMap.get(command);

            if (commandFactory == null)
                throw new IllegalArgumentException("UNKNOWN COMMAND");
            else
                return commandFactory.create(scanner);
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
    public static char readNextChar(Scanner scanner) {
        if (!scanner.hasNext())
            throw new IllegalArgumentException();

        String nextWord = scanner.next();
        if (nextWord.length() != 1)
            throw new IllegalArgumentException();

        return nextWord.charAt(0);
    }

    /**
     * Reads the next integer.
     * @return The read integer or -1 for invalid input.
     */
    public static int readNextInt(Scanner scanner) {
        if(scanner.hasNextInt())
            return scanner.nextInt();
        return -1;
    }
}
