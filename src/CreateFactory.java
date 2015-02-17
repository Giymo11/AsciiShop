import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class CreateFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            int width = AsciiShop.readNextInt(scanner);

            int heigth = AsciiShop.readNextInt(scanner);

            String charset = scanner.next();

            return new CreateOperation(width, heigth, charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FactoryException();
        }
    }
}
