import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class FilterFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            String type = scanner.next();
            if (type.equals("median"))
                return new MedianOperation();
            else if (type.equals("average"))
                return new AverageOperation();
            else
                throw new IllegalArgumentException();
        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
