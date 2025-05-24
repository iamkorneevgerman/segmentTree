package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SumPlusAddTest {
    @Test
    void basicTest() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        assertEquals(9L, st.query(1, 4));
        st.update(0, 5, 3L);
        assertEquals(15L, st.query(0, 3));
        st.update(2, 5, 1L);
        assertEquals(24L, st.query(2, 5));
    }
}
