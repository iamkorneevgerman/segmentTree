package segtrees;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StressMinAssignTest {

    @Test
    void randomArrayMinTest() {
        Random rnd = new Random(54321); // фиксированный seed
        int n = rnd.nextInt(1000) + 1; // длина от 1 до 1000
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextLong(1000); // случайные значения
        }

        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        NaiveArray naive = new NaiveArray(a.clone());

        for (int i = 0; i < 1000; i++) {
            int l = rnd.nextInt(n);
            int r = rnd.nextInt(n - l) + l + 1;
            if (rnd.nextBoolean()) {
                long val = rnd.nextLong(1000);
                st.update(l, r, val);
                naive.update(l, r, val);
            } else {
                long expected = naive.query(l, r);
                long actual = st.query(l, r);
                assertEquals(expected, actual, "Несовпадение на запросе (" + l + ", " + r + ")");
            }
        }
    }

    static class NaiveArray {
        Long[] a;

        public NaiveArray(Long[] a) {
            this.a = a;
        }

        public void update(int l, int r, long val) {
            for (int i = l; i < r; i++) {
                a[i] = val;
            }
        }

        public long query(int l, int r) {
            long min = Long.MAX_VALUE;
            for (int i = l; i < r; i++) {
                min = Math.min(min, a[i]);
            }
            return min;
        }
    }
}
