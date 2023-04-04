package com.prospring.ch5.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;

    Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (!isModified) {
            if (mi.getMethod().getName().startsWith("set") && mi.getArguments().length == 1) {
                Method getter = getGetter(mi.getMethod());
                if (getter != null) {
                    Object oldVal = getter.invoke(mi.getThis());
                    Object newVal = mi.getArguments()[0];
                    if ((oldVal == null) && (newVal == null)) {
                        isModified = false;
                    } else if ((oldVal == null) && (newVal != null)) {
                        isModified = true;
                    } else if ((oldVal != null) && (newVal == null)) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        return super.invoke(mi);
    }

    private Method getGetter(Method setter) {
        Method getter = methodCache.get(setter);
        if (getter != null) {
            return getter;
        }
        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            getter = setter.getDeclaringClass().getDeclaredMethod(getterName);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
