package sk.msvitok.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class Jsons {

    private Jsons() {}

    /**
     * DO NOT: use in extensive loops - creates new instance of {@link ObjectMapper}.
     */
    public static <T> T tryReadObject(String json, Class<T> typeClass, T fallbackValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, typeClass);
        } catch (IOException e) {
            return fallbackValue;
        }
    }
}
