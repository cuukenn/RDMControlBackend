package com.cuukenn.opensource.remote_desktop_control.core.domain.ui;

import cn.hutool.extra.spring.SpringUtil;

/**
 * @author changgg
 */
public class AbstractRegistrableControlledStage implements ControlledStage {
    {
        SpringUtil.registerBean(this.getClass().getName(), this);
    }
}
