package sk.msvitok.app.utils;

import java.util.List;

public final class Lists {

    private Lists() {}

    public static <T> T lastOf(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> T firstOf(List<T> list) {
        return list.get(0);
    }
}
