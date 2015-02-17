import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class SearchFactory implements Factory {
    private final MetricSet<AsciiImage> saved;

    public SearchFactory(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        try {
            String metric = scanner.next();

            if (metric.equals("pixelcount"))
                return new SearchOperation(saved, new PixelCountMetric());
            else if (metric.equals("uniquechars"))
                return new SearchOperation(saved, new UniqueCharsMetric());
            else
                throw new FactoryException();

        } catch (Exception ex) {
            throw new FactoryException();
        }
    }
}
