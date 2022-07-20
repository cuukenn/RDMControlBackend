package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.serializer;

/**
 * @author changgg
 */
public interface Serializer<T> {
    /**
     * 序列化
     *
     * @param object 对象
     * @return 序列化数据
     */
    byte[] serialize(T object);

    /**
     * 反序列化
     *
     * @param bytes 数据
     * @return 数据
     */
    T deserialize(byte[] bytes);
}
