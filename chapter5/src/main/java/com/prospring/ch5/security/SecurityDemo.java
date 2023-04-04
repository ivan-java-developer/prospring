package com.prospring.ch5.security;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager mrg = new SecurityManager();
        SecurityBean securityBean = getSecurityBean();

        mrg.login("John", "pass");
        securityBean.writeSecureMessage();
        mrg.logout();

        try {
            mrg.login("invalid user", "pass");
            securityBean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            mrg.logout();
        }

        try {
            securityBean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static SecurityBean getSecurityBean() {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SecurityAdvice());
        pf.setTarget(new SecurityBean());
        return (SecurityBean) pf.getProxy();
    }
}
