/**
 * Created by Giymo11 on 16.02.2015.
 */
public class MedianOperation extends FilterOperation {

    @Override
    public int filter(int[] values) {
        return values[values.length / 2];
    }
}
