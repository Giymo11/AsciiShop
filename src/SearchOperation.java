/**
 * Created by Giymo11 on 17.02.2015.
 */
public class SearchOperation implements Operation {

    private final MetricSet<AsciiImage> saved;
    private final MetricSet.Metric<AsciiImage> metric;

    public SearchOperation(MetricSet<AsciiImage> saved, MetricSet.Metric<AsciiImage> m) {
        this.saved = saved;
        this.metric = m;
    }

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        try {
            return saved.search(img, metric).iterator().next();
        } catch (Exception ex) {
            throw new OperationException();
        }
    }
}
