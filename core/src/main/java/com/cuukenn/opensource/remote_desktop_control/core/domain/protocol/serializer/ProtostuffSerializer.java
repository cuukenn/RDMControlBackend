package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.serializer;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.PacketWrapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @author changgg
 */
public class ProtostuffSerializer implements Serializer<Object> {
    private final Schema<PacketWrapper> schema = RuntimeSchema.createFrom(PacketWrapper.class);

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(new PacketWrapper(object), schema, buffer);
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            PacketWrapper wrapper = new PacketWrapper();
            ProtostuffIOUtil.mergeFrom(bytes, wrapper, schema);
            return wrapper.getPayload();
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }
}
