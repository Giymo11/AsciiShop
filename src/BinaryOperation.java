/**
 * Created by Giymo11 on 17.02.2015.
 */
public class BinaryOperation implements Operation {

    private char threshold;

    private String charset;

    public BinaryOperation(char threshold) {
        this.threshold = threshold;
    }

    private char brightest() {
        return charset.charAt(charset.length() - 1);
    }

    private char darkest() {
        return charset.charAt(0);
    }

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {

        charset = img.getCharset();

        for (int x = 0; x < img.getWidth(); ++x) {
            for (int y = 0; y < img.getHeight(); ++y) {
                if (valueOf(img.getPixel(x, y)) < valueOf(threshold))
                    img.setPixel(x, y, darkest());
                else
                    img.setPixel(x, y, brightest());
            }
        }

        return img;
    }

    private int valueOf(char c) {
        return charset.indexOf(c);
    }
}
