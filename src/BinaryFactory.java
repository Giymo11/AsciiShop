import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class BinaryFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            char threshold = AsciiShop.readNextChar(scanner);

            return new BinaryOperation(threshold);
        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
