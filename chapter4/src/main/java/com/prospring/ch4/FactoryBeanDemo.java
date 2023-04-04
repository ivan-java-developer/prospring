package com.prospring.ch4;

import com.prospring.ch4.factory.MessageDigestFactoryBean;
import com.prospring.ch4.factory.MessageDigester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class FactoryBeanDemo {

    @Configuration
    public static class MessageDigestConfig {
        @Bean
        public MessageDigestFactoryBean messageDigestSha1() {
            MessageDigestFactoryBean factoryBean = new MessageDigestFactoryBean();
            factoryBean.setAlgorithm("SHA1");
            return factoryBean;
        }

        @Bean
        public MessageDigestFactoryBean messageDigestDefault() {
            return new MessageDigestFactoryBean();
        }

        @Bean
        public MessageDigester messageDigester() throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setMessageDigestOne(messageDigestSha1().getObject());
            messageDigester.setMessageDigestTwo(messageDigestDefault().getObject());
            return messageDigester;
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/app-context-05.xml");
//        AnnotationConfigApplicationContext ctx =
//                new AnnotationConfigApplicationContext(MessageDigestConfig.class);
        MessageDigester messageDigester = ctx.getBean("messageDigester", MessageDigester.class);
        MessageDigestFactoryBean messageDigestFactoryBean =
                ctx.getBean("&messageDigestSha1", MessageDigestFactoryBean.class);
        try {
            MessageDigest digest = messageDigestFactoryBean.getObject();
            if (digest != null) {
                System.out.println(new String(digest.digest("hello world".getBytes(StandardCharsets.UTF_8))));
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
        messageDigester.digest("hello world");
        ctx.close();
    }
}
