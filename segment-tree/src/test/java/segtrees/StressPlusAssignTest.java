package segtrees;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class StressPlusAssignTest {

    @Test
    void randomArraySumTest() {
        Random rnd = new Random(12345);
        int n = rnd.nextInt(1000) + 1;
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextLong(1000);
        }

        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        NaiveArray naive = new NaiveArray(a.clone());

        for (int i = 0; i < 1000; i++) {
            int l = rnd.nextInt(n);
            int r = rnd.nextInt(n - l) + l + 1;
            if (rnd.nextBoolean()) {
                long val = rnd.nextLong(100);
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
                a[i] += val;
            }
        }

        public long query(int l, int r) {
            long sum = 0;
            for (int i = l; i < r; i++) {
                sum += a[i];
            }
            return sum;
        }
    }
}
