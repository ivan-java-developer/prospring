package com.prospring.ch10;

import com.prospring.ch10.objects.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropEditorDemo {
    private static Logger logger = LoggerFactory.getLogger(PropEditorDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/prop-editor-app-context.xml");
        Singer eric = ctx.getBean("eric", Singer.class);
        logger.info("Eric info: " + eric);
        Singer countrySinger = ctx.getBean("countrySinger", Singer.class);
        logger.info("Country singer info: " + countrySinger);
        ctx.close();
    }
}
