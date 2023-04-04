package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.lookup.DemoBean;
import com.apress.prospring5.ch2.lookup.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch2.lookup")
public class LookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LookupDemo.class);
        DemoBean standardLookup = ctx.getBean("standardLookupBean", DemoBean.class);
        DemoBean abstractLookupBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        displayInfo(standardLookup);
        displayInfo(abstractLookupBean);
        ctx.close();
    }

    public static void displayInfo(DemoBean demoBean) {
        Singer singerFirst = demoBean.getMySinger();
        Singer singerSecond = demoBean.getMySinger();
        System.out.println(
                demoBean.getClass().getSimpleName() + ": Is singer the same? " + (singerFirst == singerSecond));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100_000; i++) {
            Singer singer = demoBean.getMySinger();
            singer.sing();
        }
        stopWatch.stop();
        System.out.println("100 000 gets took " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
