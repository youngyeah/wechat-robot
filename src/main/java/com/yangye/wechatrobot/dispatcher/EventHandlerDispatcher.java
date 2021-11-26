package com.yangye.wechatrobot.dispatcher;

import com.yangye.wechatrobot.WxEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yangye37
 * @email: yangye37@jd.com
 * @date: 2021/11/24 11:33
 */
@Component
public class EventHandlerDispatcher implements InitializingBean, ApplicationContextAware {

    Map<WxEvent, EventExecution> handlerMap = new HashMap<>();

    ApplicationContext context;

    public void dispatch(WxMessage wxMessage) {
        String event = wxMessage.getEvent();
        EventExecution eventExecution = handlerMap.get(WxEvent.valueOf(event));
        if (eventExecution == null) {
            throw new RuntimeException(event + "没有对应的handler");
        }
        Method method = eventExecution.getMethod();
        try {
            method.invoke(eventExecution.getObj(), wxMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> eventHandlers = context.getBeansWithAnnotation(EventHandler.class);

        for (String beanName : eventHandlers.keySet()) {
            Object eventHandler = eventHandlers.get(beanName);
            Class<?> clazz = eventHandler.getClass();
            Method[] allMethods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method method : allMethods) {
                if (method.isAnnotationPresent(EventMapping.class)) {
                    EventMapping eventMapping = method.getAnnotation(EventMapping.class);
                    WxEvent event = eventMapping.event();
                    if (handlerMap.get(event) != null) {
                        throw new RuntimeException("event: " + event + "重复");
                    }
                    EventExecution eventExecution = new EventExecution();
                    eventExecution.setMethod(method);
                    eventExecution.setObj(eventHandler);
                    handlerMap.put(event, eventExecution);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
