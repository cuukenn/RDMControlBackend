package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SerializationUtil {
    private static final Object lock = new Object();
    private static final Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();
    private static final Objenesis objenesis = new ObjenesisStd();

    /**
     * 序列化(对象 -> 字节数组)
     *
     * @param obj 对象
     * @return 字节数组
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化(字节数组 -> 对象)
     *
     * @param data        数据
     * @param targetClass 目标类型
     * @param <T>         类型
     */
    public static <T> T deserialize(byte[] data, Class<T> targetClass) {
        try {
            T message = objenesis.newInstance(targetClass);
            Schema<T> schema = getSchema(targetClass);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> targetClass) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(targetClass);
        if (schema == null) {
            synchronized (lock) {
                schema = (Schema<T>) cachedSchema.get(targetClass);
                if (schema == null) {
                    schema = RuntimeSchema.createFrom(targetClass);
                    cachedSchema.put(targetClass, schema);
                }
            }
        }
        return schema;
    }
}
