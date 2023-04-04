package com.prospring.ch5.after_returning;

import org.springframework.aop.framework.ProxyFactory;

public class KeyGeneratorAdviceDemo {
    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();

        for (int i = 0; i < 10; i++) {
            try {
                long key = keyGenerator.getKey();
                System.out.println(key);
            } catch (SecurityException e) {
                System.out.println("Weak key generated!");
            }
        }
    }

    private static KeyGenerator getKeyGenerator() {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new KeyGeneratorAfterReturningAdvice());
        pf.setTarget(new KeyGenerator());
        return (KeyGenerator) pf.getProxy();
    }
}
