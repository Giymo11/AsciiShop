/**
 * Created by Giymo11 on 17.02.2015.
 */
public class AverageOperation extends FilterOperation {
    @Override
    public int filter(int[] values) {

        double sum = 0;

        for (int value : values) {
            sum += value;
        }

        return (int) Math.round(sum / values.length);
    }
}
