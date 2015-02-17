/**
 * Created by Giymo11 on 17.02.2015.
 */
public class PixelCountMetric implements MetricSet.Metric<AsciiImage> {
    @Override
    public int distance(AsciiImage o1, AsciiImage o2) {
        int metric1 = o1.getHeight() * o1.getWidth();
        int metric2 = o2.getHeight() * o2.getWidth();

        return Math.abs(metric1 - metric2);
    }
}
