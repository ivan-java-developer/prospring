package com.prospring.ch5.method_interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        sw.stop();
        System.out.println("Task name - " + sw.getLastTaskName());
        dumpInfo(invocation, sw.getTotalTimeMillis());
        return retVal;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Object target = invocation.getThis();
        Method method = invocation.getMethod();
        Object[] arguments = invocation.getArguments();
        System.out.println("Invoked method " + method.getName());
        System.out.println("of type " + target.getClass().getName());
        System.out.println("With arguments: ");
        for (Object arg : arguments) {
            System.out.print(" > " + arg);
        }
        System.out.println();
        System.out.println("Took: " + ms + "ms");
    }
}
