package com.cuukenn.opensource.remote_desktop_control.core.domain.event;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author changgg
 */
public abstract class AbstractRegistrableEventHandler implements IEventHandler, InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        EventFactory.register(this);
    }

    @Override
    public void destroy() throws Exception {
        EventFactory.unregister(this);
    }
}
