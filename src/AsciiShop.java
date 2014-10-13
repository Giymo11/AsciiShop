import java.util.Scanner;

/**
 * @author Benjamin Potzmann
 *
 * Reads an ASCII-picture from standard input and prints the width & length
 */
public class AsciiShop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int firstRowLength = 0;

        if(scanner.hasNextLine())
            firstRowLength = scanner.nextLine().length();

        int rowCount = 1;

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if(row.length() != firstRowLength) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            ++rowCount;
        }
        System.out.println(firstRowLength + " " + rowCount);
    }
}
