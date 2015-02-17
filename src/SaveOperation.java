/**
 * Created by Giymo11 on 17.02.2015.
 */
public class SaveOperation implements Operation {

    private MetricSet<AsciiImage> saved;

    public SaveOperation(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        if (img != null && !saved.contains(img))
            saved.add(new AsciiImage(img));

        return img;
    }

    public MetricSet<AsciiImage> getSaved() {
        return saved;
    }
}
