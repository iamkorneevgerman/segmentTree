package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinPlusAssignTest {
    @Test
    void assignTest() {
        Long[] a = {5L, 3L, 4L, 1L, 7L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignMin());
        assertEquals(1L, st.query(0, 5));
        st.update(1, 4, 6L);
        assertEquals(5L, st.query(0, 5));
        st.update(0, 2, 10L);
        assertEquals(6L, st.query(1, 4));
    }
}
