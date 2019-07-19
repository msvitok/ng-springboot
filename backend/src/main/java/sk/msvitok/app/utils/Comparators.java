package sk.msvitok.app.utils;

import java.util.Comparator;
import java.util.function.UnaryOperator;

public class Comparators {

    private Comparators() {}

    /**
     * <p>USE-CASE</p>
     *
     * <p>Useful, when some method is passing "comparator" order mutator,
     * {@code UnaryOperator<Comparator<T>>}, to give a chance to reverse ordering,
     * which is done by passing {@code Comparator::reversed}, in case we want
     * to preserve the default ordering scheme, we have to pass lambda expression
     * {@code comparator -> comparator}. This method offers more readable way
     * of expressing "no mutation".</p>
     */
    public static <T> UnaryOperator<Comparator<T>> defaultOrder() {
        return comparator -> comparator;
    }
}
