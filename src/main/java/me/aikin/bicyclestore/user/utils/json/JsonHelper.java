package me.aikin.bicyclestore.user.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
//        mapper.registerModule(new DateTimeModule());
//        mapper.registerModule(new JodaMoneyModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
    }

    public static String toJson(Object obj) {
        String string;
        try {
            string = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return string;
    }

    public static <T> T parseJson(String json, Class<T> klass) {
        try {
            return mapper.readValue(json, klass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
