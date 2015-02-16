import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giymo11 on 16.02.2015.
 */
public class MedianOperation implements Operation {

    private String charset;

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        this.charset = img.getCharset();
        AsciiImage newImg = new AsciiImage(img);

        for (int y = 0; y < img.getHeight(); ++y) {
            for (int x = 0; x < img.getWidth(); ++x) {

                newImg.setPixel(x, y, charset.charAt(getMedianFor(img, x, y)));
            }
        }

        return newImg;
    }

    private int getMedianFor(AsciiImage img, int x, int y) {

        List<Integer> neighbours = new ArrayList<Integer>(9);

        --x;
        --y;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (!img.isInsideBounds(x + i, y + j))
                    neighbours.add(brightest());
                else
                    neighbours.add(getValueOf(img.getPixel(x + i, y + j)));
            }
        }

        assert neighbours.size() == 9;

        Collections.sort(neighbours);
        return neighbours.get(4);
    }

    private int getValueOf(char c) {
        return charset.indexOf(c);
    }

    private int brightest() {
        return charset.length() - 1;
    }
}
