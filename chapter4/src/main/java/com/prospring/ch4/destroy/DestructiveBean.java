package com.prospring.ch4.destroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PreDestroy;
import java.io.File;

@Configuration
public class DestructiveBean implements DisposableBean {
    private File file;
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void init() throws Exception {
        System.out.println("Initialize bean");
        if (filePath == null) {
            throw new IllegalArgumentException("property filePath must set for " + DestructiveBean.class);
        }
        file = new File(filePath);
        file.createNewFile();
        System.out.println("File exist " + file.exists());
    }

    public void destroy() throws Exception {
        System.out.println("destroy()");
    }

    private void destroyMethod() {
        System.out.println("Destroy bean");
        file.delete();
        System.out.println("File exist " + file.exists());
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("preDestroy()");
    }

    @Lazy
    @Bean(destroyMethod="destroyMethod", initMethod="init")
    public DestructiveBean destructiveBeanOne() {
        DestructiveBean destructiveBean = new DestructiveBean();
        destructiveBean.setFilePath(
                System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "test.txt");
        return destructiveBean;
    }

    public static void main(String[] args) {
//        GenericXmlApplicationContext ctx =
//                new GenericXmlApplicationContext("classpath:spring/app-context-02.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DestructiveBean.class);
        DestructiveBean destructiveBeanOne = ctx.getBean("destructiveBeanOne", DestructiveBean.class);
        System.out.println("Calling destroy");
        ctx.registerShutdownHook();
        System.out.println("Called destroy");
    }
}
