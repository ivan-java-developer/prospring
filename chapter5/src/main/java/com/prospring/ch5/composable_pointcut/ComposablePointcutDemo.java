package com.prospring.ch5.composable_pointcut;

import com.prospring.ch5.control_flow_pointcut.SimpleBeforeAdvice;
import com.prospring.ch5.name_match_method_pointcut.GrammyGuitarist;
import com.prospring.ch5.name_match_method_pointcut.Guitar;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutDemo {
    public static void main(String[] args) {
        GrammyGuitarist guitarist = new GrammyGuitarist();
        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        GrammyGuitarist proxy = getProxy(guitarist, pc);
        System.out.println("test 1");
        test(proxy);
        System.out.println("---= end of test =---");
        System.out.println();

        pc.union(new TalkMethodMatcher());

        proxy = getProxy(guitarist, pc);
        System.out.println("test 2");
        test(proxy);
        System.out.println("---= end of test =---");
        System.out.println();

        pc.intersection(new RestMethodMatcher());

        proxy = getProxy(guitarist, pc);
        System.out.println("test 3");
        test(proxy);
        System.out.println("---= end of test =---");
        System.out.println();
    }

    private static GrammyGuitarist getProxy(GrammyGuitarist target, Pointcut pc) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice()));
        return (GrammyGuitarist) pf.getProxy();
    }

    private static void test(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return "sing".equals(method.getName());
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().startsWith("tal");
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().endsWith("st");
        }
    }
}
