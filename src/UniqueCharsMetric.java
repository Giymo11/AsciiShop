/**
 * Created by Giymo11 on 17.02.2015.
 */
public class UniqueCharsMetric implements MetricSet.Metric<AsciiImage> {
    @Override
    public int distance(AsciiImage o1, AsciiImage o2) {
        return Math.abs(o1.getUniqueChars() - o2.getUniqueChars());
    }
}
