package sort;

import java.util.List;

public interface Komparable<T extends Komparable> {

    T better(T other);

    static <S extends Komparable<S>> S max(List<S> komparables) {
        return komparables.stream()
                .reduce(S::better)
                .orElse(null);
    }

//    static void assertEqualsTypes(List<Komparable> dings) {
//        if (!dings.stream().allMatch(dings1 -> dings.get(0).getClass().equals(dings1.getClass())))
//            throw new UnsupportedOperationException();
//    }

}
