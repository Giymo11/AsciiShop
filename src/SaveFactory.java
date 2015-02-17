import java.util.Scanner;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class SaveFactory implements Factory {

    private MetricSet<AsciiImage> images;

    public SaveFactory(MetricSet<AsciiImage> asciiImageMetricSet) {
        images = asciiImageMetricSet;
    }

    @Override
    public Operation create(Scanner scanner) throws FactoryException {
        return new SaveOperation(images);
    }
}
