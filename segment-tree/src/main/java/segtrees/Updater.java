package segtrees;

public interface Updater<T, U> {
    T apply(T value, U update, int size);
    U merge(U oldUpdate, U newUpdate);
    U identity();

    static Updater<Long, Long> addLongs() {
        return new Updater<>() {
            public Long apply(Long val, Long upd, int size) {
                return val + upd * size;
            }
            public Long merge(Long oldUpd, Long newUpd) {
                return oldUpd + newUpd;
            }
            public Long identity() { return 0L; }
        };
    }

    static Updater<Long, Long> assignMin() {
        return new Updater<>() {
            public Long apply(Long val, Long upd, int size) {
                return upd * size;
            }
            public Long merge(Long oldUpd, Long newUpd) {
                return newUpd;
            }
            public Long identity() { return null; }
        };
    }
}
