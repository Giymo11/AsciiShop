/**
 * Created by Giymo11 on 16.02.2015.
 */
public class ClearOperation implements Operation {

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        for (int x = 0; x < img.getWidth(); ++x)
            for (int y = 0; y < img.getHeight(); ++y)
                img.setPixel(x, y, img.getCharset().charAt(img.getCharset().length() - 1));

        return img;
    }
}
