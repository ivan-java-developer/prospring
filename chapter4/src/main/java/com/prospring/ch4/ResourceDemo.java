package com.prospring.ch4;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class ResourceDemo {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:spring/empty-app-context.xml");
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();

        Resource res1 = ctx.getResource("file://" + file.getPath());
        displayInfo(res1);
        Resource res2 = ctx.getResource("classpath:spring/app-context-01.xml");
        displayInfo(res2);
        Resource res3 = ctx.getResource("https://google.com");
        displayInfo(res3);
        ctx.registerShutdownHook();
    }

    private static void displayInfo(Resource resource) throws IOException {
        System.out.println(resource.getClass());
        System.out.println(resource.getURL().getContent());
        System.out.println(resource.getInputStream());
        System.out.println();
    }
}
