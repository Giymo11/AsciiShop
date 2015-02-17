import java.util.Arrays;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public abstract class FilterOperation implements Operation {

    private String charset;

    @Override
    public AsciiImage execute(AsciiImage img) throws OperationException {
        this.charset = img.getCharset();
        AsciiImage newImg = new AsciiImage(img);

        for (int y = 0; y < img.getHeight(); ++y) {
            for (int x = 0; x < img.getWidth(); ++x) {

                newImg.setPixel(x, y, charset.charAt(filter(getNeighborsFor(img, x, y))));
            }
        }

        return newImg;
    }

    private int[] getNeighborsFor(AsciiImage img, int x, int y) {

        int[] neighbours = new int[9];

        int current = 0;

        --x;
        --y;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (!img.isInsideBounds(x + i, y + j))
                    neighbours[current++] = brightest();
                else
                    neighbours[current++] = getValueOf(img.getPixel(x + i, y + j));
            }
        }

        assert current == 9;

        Arrays.sort(neighbours);

        return neighbours;
    }

    private int getValueOf(char c) {
        return charset.indexOf(c);
    }

    private int brightest() {
        return charset.length() - 1;
    }

    public abstract int filter(int[] values);
}
