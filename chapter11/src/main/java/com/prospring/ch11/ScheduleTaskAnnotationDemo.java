package com.prospring.ch11;

import com.prospring.ch11.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ScheduleTaskAnnotationDemo {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.in.read();
        ctx.close();
    }
}
