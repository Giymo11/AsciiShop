import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public interface Factory {
    public Operation create(Scanner scanner) throws FactoryException;
}
