package ${package}.client.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.function.Supplier;

/**
 * CCreated by ${userName} on ${today}.
 */
public abstract class JsonUtils {
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<Class<?>, Class<?>>(8);

    private static ObjectMapper objectMapper;

    private final static Gson gson;

    private final static ${package}.client.common.utils.JsonUtils jacksonUtils;

    private final static ${package}.client.common.utils.JsonUtils gsonUtils;

    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = gsonBuilder.create();
        jacksonUtils = new ${package}.client.common.utils.JsonUtils() {
            @Override
            public String toJson(Object object) {
                try {
                    if (object == null) {
                        return null;
                    }
                    return objectMapper.writeValueAsString(object);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("toJson Error : ", e);
                }
            }

            @Override
            public Map<String, Object> toMap(Object object) {
                if (object == null) {
                    return null;
                }
                if (object.getClass().isPrimitive() || primitiveWrapperTypeMap.containsKey(object.getClass())) {
                    throw new RuntimeException("object class is primitive");
                }
                return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
                });
            }

            @Override
            public <T> T fromJson(String json, Class<T> clazz) {
                try {
                    if (json == null) {
                        return null;
                    }
                    return objectMapper.readValue(json, clazz);
                } catch (IOException e) {
                    throw new RuntimeException("fromJson2Obj Error : ", e);
                }
            }

            @Override
            public Map<String, String> toMap(String json) {
                if (json == null) {
                    return null;
                }
                try {
                    json = json.trim();
                    if (json.startsWith("{")) {
                        return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
                        });
                    }
                    throw new RuntimeException("json is not start with '{'");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public <T> T fromJson(String json, Supplier supplier) {
                if (json == null) {
                    return null;
                }
                Object typeToken = supplier.get();
                if (typeToken == null) {
                    return null;
                } else if (typeToken instanceof TypeReference) {
                    try {
                        return objectMapper.readValue(json, (TypeReference) typeToken);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                throw new RuntimeException(String.format("not support Supplier<%s>", typeToken.getClass().getSimpleName()));
            }
        };

        gsonUtils = new ${package}.client.common.utils.JsonUtils() {
            @Override
            public String toJson(Object object) {
                if (object == null) {
                    return null;
                }
                return gson.toJson(object);
            }

            @Override
            public <T> T fromJson(String json, Class<T> clazz) {
                if (json == null) {
                    return null;
                }
                return gson.fromJson(json, clazz);
            }

            @Override
            public <T> T fromJson(String json, Supplier supplier) {
                if (json == null) {
                    return null;
                }
                Object typeToken = supplier.get();
                if (typeToken == null) {
                    return null;
                } else if (typeToken instanceof TypeToken) {
                    return gson.fromJson(json, ((TypeToken) typeToken).getType());
                } else if (typeToken instanceof Type) {
                    return gson.fromJson(json, (Type) typeToken);
                }
                throw new RuntimeException(String.format("not support Supplier<%s>", typeToken.getClass().getSimpleName()));
            }

            @Override
            public Map<String, String> toMap(String json) {
                if (json == null) {
                    return null;
                }
                json = json.trim();
                if (json.startsWith("{")) {
                    return gson.fromJson(json, new TypeToken<Map<String, String>>() {
                    }.getType());
                }
                throw new RuntimeException("json is not start with '{'");
            }

            @Override
            public Map<String, Object> toMap(Object object) {
                if (object == null) {
                    return null;
                }
                if (object.getClass().isPrimitive() || primitiveWrapperTypeMap.containsKey(object.getClass())) {
                    throw new RuntimeException("object class is primitive");
                }
                return this.fromJson(this.toJson(object), () -> new TypeToken<Map<String, Object>>() {
                });
            }
        };
    }

    public static ${package}.client.common.utils.JsonUtils getJsonUtils(JsonUtilsType jsonUtilsType) {
        if (jsonUtilsType == null) {
            return jacksonUtils;
        }
        switch (jsonUtilsType) {
            case Jackson:
                return jacksonUtils;
            case Gson:
                return gsonUtils;
        }
        return jacksonUtils;
    }

    public abstract String toJson(Object object);

    public abstract <T> T fromJson(String json, Class<T> clazz);

    public abstract <T> T fromJson(String json, Supplier supplier);

    public <T> List<T> toList(String json, Class<T[]> clazz) {
        if (json == null) {
            return null;
        }
        json = json.trim();
        if (json.startsWith("[")) {
            T[] result = this.fromJson(json, clazz);
            return result == null ? null : Arrays.asList(result);
        }
        throw new RuntimeException("json is not start with '['");
    }

    public abstract Map<String, String> toMap(String json);

    public abstract Map<String, Object> toMap(Object object);

    public enum JsonUtilsType {
        Jackson, Gson;
    }

}
