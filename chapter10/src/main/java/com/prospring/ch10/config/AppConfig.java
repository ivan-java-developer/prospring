package com.prospring.ch10.config;

import com.prospring.ch10.converters.ApplicationConversionServiceFactoryBean;
import com.prospring.ch10.objects.Singer;
import com.prospring.ch10.converters.SingerToAnotherSingerConverter;
import com.prospring.ch10.converters.StringToDateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.prospring.ch10")
@Configuration
public class AppConfig {

    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Autowired
    private ApplicationConversionServiceFactoryBean appConversionService;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Singer john(
            @Value("${countrySinger.firstName}") String firstName,
            @Value("${countrySinger.lastName}") String lastName,
            @Value("${countrySinger.birthDate}") DateTime birthDate,
            @Value("${countrySinger.site}") URL site) {

        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setBirthDate(birthDate);
        singer.setSite(site);
        return singer;
    }

    @Bean Singer viktor() throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("Viktor");
        singer.setLastName("Tsoy");
        singer.setBirthDate(appConversionService.getDateTimeFormatter().parse("21-06-1962", Locale.ROOT));
        singer.setSite(new URL("https://kino.band/"));
        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> convs = new HashSet<>();
        convs.add(converter());
        convs.add(singerConverter());
        conversionServiceFactoryBean.setConverters(convs);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean
    public StringToDateTimeConverter converter() {
        StringToDateTimeConverter stringToDateTimeConverter = new StringToDateTimeConverter();
        stringToDateTimeConverter.setDatePattern(dateFormatPattern);
        stringToDateTimeConverter.init();
        return stringToDateTimeConverter;
    }

    @Bean
    public SingerToAnotherSingerConverter singerConverter() {
        return new SingerToAnotherSingerConverter();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
