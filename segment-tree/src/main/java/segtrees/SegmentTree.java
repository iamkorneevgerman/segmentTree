package segtrees;

public class SegmentTree<T, U> {
    private final int size;
    private final T[] tree;
    private final U[] lazy;
    private final boolean[] pending;
    private final Combiner<T> combiner;
    private final Updater<T, U> updater;

    public SegmentTree(T[] array, Combiner<T> combiner, Updater<T, U> updater) {
        this.size = array.length;
        this.combiner = combiner;
        this.updater = updater;
        tree = (T[]) new Object[4 * size];
        lazy = (U[]) new Object[4 * size];
        pending = new boolean[4 * size];
        build(0, 0, size, array);
    }

    private void build(int v, int tl, int tr, T[] array) {
        if (tr - tl == 1) {
            tree[v] = array[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(2*v+1, tl, tm, array);
            build(2*v+2, tm, tr, array);
            tree[v] = combiner.combine(tree[2*v+1], tree[2*v+2]);
        }
        lazy[v] = updater.identity();
    }

    private void push(int v, int tl, int tr) {
        if (!pending[v]) return;
        tree[v] = updater.apply(tree[v], lazy[v], tr - tl);
        if (tr - tl > 1) {
            applyLazy(2*v+1, lazy[v]);
            applyLazy(2*v+2, lazy[v]);
        }
        lazy[v] = updater.identity();
        pending[v] = false;
    }

    private void applyLazy(int v, U val) {
        if (!pending[v]) {
            lazy[v] = val;
            pending[v] = true;
        } else {
            lazy[v] = updater.merge(lazy[v], val);
        }
    }

    public void update(int l, int r, U val) {
        update(0, 0, size, l, r, val);
    }

    private void update(int v, int tl, int tr, int l, int r, U val) {
        push(v, tl, tr);
        if (l >= r) return;
        if (l == tl && r == tr) {
            applyLazy(v, val);
            push(v, tl, tr);
        } else {
            int tm = (tl + tr) / 2;
            update(2*v+1, tl, tm, l, Math.min(r, tm), val);
            update(2*v+2, tm, tr, Math.max(l, tm), r, val);
            tree[v] = combiner.combine(tree[2*v+1], tree[2*v+2]);
        }
    }

    public T query(int l, int r) {
        return query(0, 0, size, l, r);
    }

    private T query(int v, int tl, int tr, int l, int r) {
        push(v, tl, tr);
        if (l >= r) return combiner.neutral();
        if (l == tl && r == tr) return tree[v];
        int tm = (tl + tr) / 2;
        return combiner.combine(
            query(2*v+1, tl, tm, l, Math.min(r, tm)),
            query(2*v+2, tm, tr, Math.max(l, tm), r)
        );
    }
}
