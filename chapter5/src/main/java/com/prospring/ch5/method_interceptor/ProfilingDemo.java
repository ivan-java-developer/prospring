package com.prospring.ch5.method_interceptor;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean workerBean = getWorkerBean();
        workerBean.doSomeWork(10_000_000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();
        ProfilingInterceptor advice = new ProfilingInterceptor();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(advice);
        return (WorkerBean) pf.getProxy();
    }
}
