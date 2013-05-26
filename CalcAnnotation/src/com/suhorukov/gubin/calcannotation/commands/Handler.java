package com.suhorukov.gubin.calcannotation.commands;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {
    Object obj;

    public Handler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        return method.invoke(obj, args);
    }
}
