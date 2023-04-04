package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.replacer.ReplacementTarget;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacerDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/method-replacer-app-context.xml");
        ReplacementTarget standardFormatMessageBean =
                ctx.getBean("standardFormatMessageBean", ReplacementTarget.class);
        ReplacementTarget replacementFormatMessageBean =
                ctx.getBean("replacementFormatMessageBean", ReplacementTarget.class);

        displayInfo(standardFormatMessageBean);
        displayInfo(replacementFormatMessageBean);

        ctx.close();
    }

    private static void displayInfo(ReplacementTarget replacementTarget) {
        System.out.println(replacementTarget.formatMessage("Hello"));

        StopWatch stopWatch = new StopWatch("replacement");
        stopWatch.start();
        for (int i = 0; i < 100_000; i++) {
            String test = replacementTarget.formatMessage("test performance");
//            System.out.println(test);
        }
        stopWatch.stop();
        System.out.println("100 000 invocations took " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
