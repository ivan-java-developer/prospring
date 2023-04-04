package com.prospring.ch5.proxy_factory_bean_introduction;

import com.prospring.ch5.introduction.Contact;
import com.prospring.ch5.introduction.IsModified;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionXmlConfigDemo {
    public static void main(String[] args) {
//        GenericXmlApplicationContext ctx =
//                new GenericXmlApplicationContext(
//                        "classpath:app-context-proxy-factory-bean-introduction.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Contact proxy = ctx.getBean("contact", Contact.class);
        IsModified isModified = ctx.getBean("contact", IsModified.class);

        System.out.println("Is bean instance of Contact " + (proxy instanceof Contact));
        System.out.println("Is bean instance of IsModified " + (proxy instanceof IsModified));
        System.out.println(isModified.isModified());
        proxy.setName("John");
        System.out.println(isModified.isModified());
        proxy.setName("Karl");
        System.out.println(isModified.isModified());
        ctx.close();
    }
}
