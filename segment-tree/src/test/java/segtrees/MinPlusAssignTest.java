package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinPlusAssignTest {
    @Test
    void assignTest() {
        Long[] a = {5L, 3L, 4L, 1L, 7L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        assertEquals(1L, st.query(0, 5));
        st.update(1, 4, 6L);
        assertEquals(5L, st.query(0, 5));
        st.update(0, 2, 10L);
        assertEquals(6L, st.query(1, 4));
        st.update(0, 5, 11L);
        assertEquals(11L, st.query(1, 5));
    }

    @Test
    void fullRangeAssignTest() {
        Long[] a = {7L, 8L, 9L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(0, 3, 5L);
        assertEquals(5L, st.query(0, 3));
    }


    @Test
    void assignSingleElementTest() {
        Long[] a = {9L, 4L, 6L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(1, 2, 10L);
        assertEquals(6L, st.query(0, 3));
    }


    @Test
    void doubleAssignTest() {
        Long[] a = {8L, 6L, 7L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(0, 2, 10L);
        st.update(1, 3, 5L);
        assertEquals(5L, st.query(0, 3));
    }


    @Test
    void borderAssignTest() {
        Long[] a = {3L, 4L, 5L, 6L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());
        st.update(0, 1, 2L);
        st.update(3, 4, 1L);
        assertEquals(1L, st.query(0, 4));
    }



}
