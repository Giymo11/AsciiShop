import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class ReplaceFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            char oldChar = AsciiShop.readNextChar(scanner);

            char newChar = AsciiShop.readNextChar(scanner);

            return new ReplaceOperation(oldChar, newChar);
        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
