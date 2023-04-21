package com.prospring.ch12.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prospring.ch12.entities.Singer;
import com.prospring.ch12.entities.Singers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.prospring.ch12"})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(singerMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        objectMapper.setDateFormat(sdf);
        return objectMapper;
    }

    @Bean
    public MarshallingHttpMessageConverter singerMessageConverter() {
        MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
        mc.setMarshaller(xStreamMarshaller());
        mc.setUnmarshaller(xStreamMarshaller());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mt = new MediaType("application", "xml");
        mediaTypes.add(mt);
        mc.setSupportedMediaTypes(mediaTypes);
        return mc;
    }

    @Bean
    public XStreamMarshaller xStreamMarshaller() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAnnotatedClasses(Singer.class, Singers.class);
        xStreamMarshaller.getXStream().addDefaultImplementation(java.util.Date.class, java.sql.Date.class);
        return xStreamMarshaller;
    }
}
