package com.prospring.ch6.boot;

import com.prospring.ch6.boot.dao.SingerDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        System.out.println(singerDao.findNameById(1));
        System.out.println(singerDao.findNameById(2));
        ctx.close();
    }
}
