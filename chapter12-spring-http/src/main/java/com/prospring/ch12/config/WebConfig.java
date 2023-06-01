package com.prospring.ch12.config;

import com.prospring.ch12.AppStatistics;
import com.prospring.ch12.AppStatisticsImpl;
import com.prospring.ch12.jmx.CustomStatistics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AppStatistics appStatisticsBean() {
        return new AppStatisticsImpl();
    }

    @Bean
    public CustomStatistics hibernateStatisticsBean() {
        return new CustomStatistics();
    }

    @Bean
    public MBeanExporter jmxExporter() {
        MBeanExporter mBeanExporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("bean:name=ProSpringSingerApp", appStatisticsBean());
        beans.put("bean:name=ProSpringSingerApp-hibernate", hibernateStatisticsBean());
        mBeanExporter.setBeans(beans);
        return mBeanExporter;
    }
}
