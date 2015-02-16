import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class LoadFactory implements Factory {
    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            String eof = scanner.next();
            scanner.nextLine();

            scanner.useDelimiter(Pattern.quote(eof));

            String newImage = scanner.next();

            scanner.reset();
            scanner.nextLine();

            return new LoadOperation(newImage);
        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
