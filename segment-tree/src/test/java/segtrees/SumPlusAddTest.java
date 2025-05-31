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

    @Test
    void pointRangeTest() {
        Long[] a = {10L, 20L, 30L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        assertEquals(20L, st.query(1, 2)); // только 1 элемент
        st.update(1, 2, 5L);
        assertEquals(25L, st.query(1, 2));
    }


    @Test
    void fullRangeTest() {
        Long[] a = {1L, 1L, 1L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        st.update(0, 4, 3L);
        assertEquals(16L, st.query(0, 4)); // каждый стал 4
    }


    @Test
    void doubleUpdateTest() {
        Long[] a = {1L, 2L, 3L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        st.update(0, 2, 2L); // +2 -> 3 4 3
        st.update(1, 3, 1L); // +1 -> 3 5 4
        assertEquals(12L, st.query(0, 3)); // 3+5+4
    }


    @Test
    void disjointUpdateQueryTest() {
        Long[] a = {1L, 2L, 3L, 4L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        st.update(0, 2, 5L);  // изменим только первые два элемента (индексы 0 и 1)
        assertEquals(3L, st.query(2, 3)); // ожидаем значение на позиции 2
    }



}
