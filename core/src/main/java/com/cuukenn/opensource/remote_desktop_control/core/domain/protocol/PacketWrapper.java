package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 某些序列化工具需要提供类信息、使用该对象进行包装
 *
 * @author changgg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacketWrapper implements Serializable {
    private static final long serialVersionUID = -1L;
    private Object payload;
}
