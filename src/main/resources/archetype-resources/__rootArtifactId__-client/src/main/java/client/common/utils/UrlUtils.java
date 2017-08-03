package ${package}.client.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Created by ${userName} on ${today}.
 */
@Slf4j
public class UrlUtils {
    public static final String UTF_8 = "utf-8";

    public static final Map<Class<? extends Convert>, Convert> CONVERT = new ConcurrentHashMap<>();

    public static Convert getConvert(Class<? extends Convert> clazz) {
        if (clazz == null) {
            throw new NullPointerException("Convert clazz is null");
        }
        Convert convert = CONVERT.get(clazz);
        if (convert == null) {
            try {
                synchronized (clazz) {
                    convert = CONVERT.get(clazz);
                    if (convert == null) {
                        convert = Convert.class.cast(clazz.newInstance());
                        CONVERT.put(clazz, convert);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return convert;
    }

    public static Convert getDefaultConvert() {
        return getConvert(GsonConvert.class);
    }

    public static String getDefaultCharset() {
        return UTF_8;
    }

    public static Class<String> getDefaultValueClass() {
        return String.class;
    }

    public static String decodeValue(String value) {
        return decodeValue(value, getDefaultCharset());
    }

    public static String decodeValue(String value, String charset) {
        if (charset == null || charset.trim().isEmpty()) {
            charset = getDefaultCharset();
        }
        try {
            return URLDecoder.decode(value, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] decodeValue(String[] values) {
        return decodeValue(values, getDefaultCharset());
    }

    public static String[] decodeValue(String[] values, String charset) {
        if (values == null || values.length == 0) {
            return values;
        }
        return Arrays.stream(values).map(value -> decodeValue(value, charset)).toArray(String[]::new);
    }

    public static String encodeValue(String value) {
        return encodeValue(value, getDefaultCharset());
    }

    public static String encodeValue(String value, String charset) {
        if (charset == null || charset.trim().isEmpty()) {
            charset = getDefaultCharset();
        }
        try {
            return URLEncoder.encode(value, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] encodeValue(String[] values) {
        return encodeValue(values, getDefaultCharset());
    }

    public static String[] encodeValue(String[] values, String charset) {
        if (values == null || values.length == 0) {
            return values;
        }
        return Arrays.stream(values).map(value -> encodeValue(value, charset)).toArray(String[]::new);
    }

    public static <Value> String encodeValue(Value value) {
        return encodeValue(value, getDefaultCharset(), getDefaultConvert());
    }

    public static <Value> String encodeValue(Value value, Class<? extends Convert> clazz) {
        return encodeValue(value, getDefaultCharset(), clazz);
    }

    public static <Value> String encodeValue(Value value, Convert convert) {
        return encodeValue(value, getDefaultCharset(), convert);
    }

    public static <Value> String encodeValue(Value value, String charset) {
        return encodeValue(value, charset, getDefaultConvert());
    }

    public static <Value> String encodeValue(Value value, String charset, Class<? extends Convert> clazz) {
        return encodeValue(value, charset, getConvert(clazz));
    }

    public static <Value> String encodeValue(Value value, String charset, Convert convert) {
        if (value == null) {
            return null;
        }
        if (convert == null) {
            throw new NullPointerException("Convert object is null");
        }
        return convert.encodeConvert(value, charset);
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query) {
        return encodeValue(query, getDefaultCharset(), getDefaultConvert());
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query, Class<? extends Convert> clazz) {
        return encodeValue(query, getDefaultCharset(), clazz);
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query, Convert convert) {
        return encodeValue(query, getDefaultCharset(), convert);
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query, String charset) {
        return encodeValue(query, charset, getDefaultConvert());
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query, String charset, Class<? extends Convert> clazz) {
        return encodeValue(query, charset, getConvert(clazz));
    }

    public static <Value> Map<String, String> encodeValue(Map<String, Value> query, String charset, Convert convert) {
        if (query == null) {
            return null;
        } else if (query.isEmpty()) {
            return Collections.emptyMap();
        }
        if (convert == null) {
            throw new NullPointerException("Convert object is null");
        }
        Map<String, String> result = new HashMap<>();
        query.forEach((k, v) -> result.put(k, encodeValue(v, charset, convert)));
        return result;
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue) {
        return decodeValue(value, clazzValue, getDefaultCharset(), getDefaultConvert());
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue, Class<? extends Convert> clazz) {
        return decodeValue(value, clazzValue, getDefaultCharset(), clazz);
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue, Convert convert) {
        return decodeValue(value, clazzValue, getDefaultCharset(), convert);
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue, String charset) {
        return decodeValue(value, clazzValue, charset, getDefaultConvert());
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue, String charset, Class<? extends Convert> clazz) {
        return decodeValue(value, clazzValue, charset, getConvert(clazz));
    }

    public static <Value> Value decodeValue(String value, Class<Value> clazzValue, String charset, Convert convert) {
        if (value == null) {
            return null;
        }
        if (convert == null) {
            throw new NullPointerException("Convert object is null");
        }
        return convert.decodeConvert(value, clazzValue, charset);
    }

    public static Map<String, String> decodeValue(Map<String, String> query) {
        return decodeValue(query, getDefaultValueClass(), getDefaultCharset(), getDefaultConvert());
    }

    public static Map<String, String> decodeValueD(Map<String, String> query, Class<? extends Convert> clazz) {
        return decodeValue(query, getDefaultValueClass(), getDefaultCharset(), clazz);
    }

    public static Map<String, String> decodeValue(Map<String, String> query, Convert convert) {
        return decodeValue(query, getDefaultValueClass(), getDefaultCharset(), convert);
    }

    public static Map<String, String> decodeValue(Map<String, String> query, String charset) {
        return decodeValue(query, getDefaultValueClass(), charset, getDefaultConvert());
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> Clazz) {
        return decodeValue(query, Clazz, getDefaultCharset(), getDefaultConvert());
    }

    public static Map<String, String> decodeValue(Map<String, String> query, String charset, Class<? extends Convert> clazz) {
        return decodeValue(query, getDefaultValueClass(), charset, getConvert(clazz));
    }

    public static Map<String, String> decodeValue(Map<String, String> query, String charset, Convert convert) {
        return decodeValue(query, getDefaultValueClass(), charset, convert);
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> clazzValue, Class<? extends Convert> clazz) {
        return decodeValue(query, clazzValue, getDefaultCharset(), clazz);
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> clazzValue, Convert convert) {
        return decodeValue(query, clazzValue, getDefaultCharset(), convert);
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> clazzValue, String charset) {
        return decodeValue(query, clazzValue, charset, getDefaultConvert());
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> clazzValue, String charset, Class<? extends Convert> clazz) {
        return decodeValue(query, clazzValue, charset, getConvert(clazz));
    }

    public static <Value> Map<String, Value> decodeValue(Map<String, String> query, Class<Value> clazzValue, String charset, Convert convert) {
        if (query == null) {
            return null;
        } else if (query.isEmpty()) {
            return Collections.emptyMap();
        }
        if (convert == null) {
            throw new NullPointerException("Convert object is null");
        }
        Map<String, Value> result = new HashMap<>();
        query.forEach((k, v) -> result.put(k, decodeValue(v, clazzValue, charset, convert)));
        return result;
    }

    public static <Value> String encodeUrl(String url, Map<String, Value> query) {
        return encodeUrl(url, query, getDefaultCharset(), getDefaultConvert());
    }

    public static <Value> String encodeUrl(String url, Map<String, Value> query, String charset) {
        return encodeUrl(url, query, charset, getDefaultConvert());
    }

    public static <Value> String encodeUrl(String url, Map<String, Value> query, Convert convert) {
        return encodeUrl(url, query, getDefaultCharset(), convert);
    }

    public static <Value> String encodeUrl(String url, Map<String, Value> query, String charset, Convert convert) {
        return encodeUrl(url, query, charset, convert, value -> value.indexOf("?") == -1);
    }

    public static <Value> String encodeUrl(String url, Map<String, Value> query, String charset, Convert convert, Predicate<String> predicate) {
        Map<String, String> queryString = encodeValue(query, charset, convert);
        if (queryString == null) {
            return url;
        } else if (queryString.isEmpty()) {
            return url == null ? "" : url;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (url != null) {
            stringBuilder.append(url).append(predicate.test(url) ? "?" : (url.endsWith("&") ? "" : "&"));
        }
        queryString.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("&"));
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    public interface Convert {
        default <Value> String encodeConvert(Value from) {
            return encodeConvert(from, getDefaultCharset());
        }

        <Value> String encodeConvert(Value from, String charset);

        default String decodeConvert(String from) {
            return decodeConvert(from, getDefaultCharset());
        }

        default String decodeConvert(String from, String charset) {
            return decodeConvert(from, String.class, charset);
        }

        default <Value> Value decodeConvert(String from, Class<Value> clazz) {
            return decodeConvert(from, clazz, getDefaultCharset());
        }

        <Value> Value decodeConvert(String from, Class<Value> clazz, String charset);
    }

    public static class JacksonConvert implements Convert {
        private ${package}.client.common.utils.JsonUtils jsonUtils = ${package}.client.common.utils.JsonUtils.getJsonUtils(${package}.client.common.utils.JsonUtils.JsonUtilsType.Jackson);

        @Override
        public <Value> String encodeConvert(Value from, String charset) {
            return ${package}.client.common.utils.UrlUtils.encodeValue(jsonUtils.toJson(from), charset);
        }

        @Override
        public <Value> Value decodeConvert(String from, Class<Value> clazz, String charset) {
            return jsonUtils.fromJson(${package}.client.common.utils.UrlUtils.decodeValue(from, charset), clazz);
        }
    }

    public static class GsonConvert implements Convert {
        private ${package}.client.common.utils.JsonUtils jsonUtils = ${package}.client.common.utils.JsonUtils.getJsonUtils(${package}.client.common.utils.JsonUtils.JsonUtilsType.Gson);

        @Override
        public <Value> String encodeConvert(Value from, String charset) {
            return ${package}.client.common.utils.UrlUtils.encodeValue(jsonUtils.toJson(from), charset);
        }

        @Override
        public <Value> Value decodeConvert(String from, Class<Value> clazz, String charset) {
            return jsonUtils.fromJson(${package}.client.common.utils.UrlUtils.decodeValue(from, charset), clazz);
        }
    }
}

