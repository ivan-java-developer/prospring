package com.apress.prospring5.ch2.replacer;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class FormatMessageReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if (isFormatMessageMethod(method)) {
            String msg = (String) args[0];
            return "<h2>" + msg + "</h2>";
        }
        throw new IllegalArgumentException("Unable to reimplement method " + method.getName());
    }

    private boolean isFormatMessageMethod(Method method) {
        if (!"format".equals(method.getName())) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        if (method.getParameterTypes()[0] != String.class) {
            return false;
        }
        if (method.getReturnType() != String.class) {
            return false;
        }
        return true;
    }
}
