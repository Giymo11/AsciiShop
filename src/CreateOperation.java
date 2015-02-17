/**
 * Created by Giymo11 on 17.02.2015.
 */
public class CreateOperation implements Operation {
    private int width, height;
    private String charset;

    public CreateOperation(int width, int height, String charset) {
        this.width = width;
        this.height = height;
        this.charset = charset;
    }

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        return new AsciiImage(width, height, charset);
    }
}
