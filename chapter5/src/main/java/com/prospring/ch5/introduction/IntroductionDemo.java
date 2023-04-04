package com.prospring.ch5.introduction;

import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args) {
        Contact contact = new Contact();
        contact.setName("John");

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(contact);
        pf.addAdvisor(new IsModifiedAdvisor());
        pf.setOptimize(true);

        Contact proxy = (Contact) pf.getProxy();
        IsModified isModified = (IsModified) proxy;

        System.out.println("Is bean instance of Contact " + (proxy instanceof Contact));
        System.out.println("Is bean instance of IsModified " + (proxy instanceof IsModified));
        System.out.println(isModified.isModified());
        proxy.setName("John");
        System.out.println(isModified.isModified());
        proxy.setName("Karl");
        System.out.println(isModified.isModified());

    }
}
