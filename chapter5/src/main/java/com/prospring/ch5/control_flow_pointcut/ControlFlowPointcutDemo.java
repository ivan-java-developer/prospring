package com.prospring.ch5.control_flow_pointcut;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowPointcutDemo {
    public static void main(String[] args) {
        ControlFlowPointcutDemo ex = new ControlFlowPointcutDemo();
        ex.run();
    }

    private void run() {
        SimpleBean bean = new SimpleBean();

        ControlFlowPointcut pc = new ControlFlowPointcut(ControlFlowPointcutDemo.class, "test");
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(bean);
        pf.addAdvisor(new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice()));
        SimpleBean proxy = (SimpleBean) pf.getProxy();

        proxy.foo();
        test(proxy);
    }

    private void test(SimpleBean bean) {
        bean.foo();
    }
}
