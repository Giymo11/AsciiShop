import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class ClearFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            return new ClearOperation();
        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
