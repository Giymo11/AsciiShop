import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by Giymo11 on 17.02.2015.
 */
public class MetricSet<E> extends LinkedHashSet<E> {

    public MetricSet(Collection<? extends E> c) {
        super(c);
    }

    public MetricSet() {
        super();
    }

    public MetricSet<E> search(E e, Metric<? super E> m) {
        int leastDistance = Integer.MAX_VALUE;

        for (E elem : this) {
            leastDistance = Math.min(leastDistance, m.distance(elem, e));
        }

        MetricSet<E> newSet = new MetricSet<E>();

        for (E elem : this) {
            if (m.distance(elem, e) == leastDistance)
                newSet.add(elem);
        }

        return newSet;
    }

    /**
     * Created by Giymo11 on 17.02.2015.
     */
    public interface Metric<T> {
        public int distance(T o1, T o2);
    }
}
