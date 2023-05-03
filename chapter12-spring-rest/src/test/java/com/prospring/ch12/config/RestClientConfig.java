package com.prospring.ch12.config;

import com.prospring.ch12.entities.Singer;
import com.prospring.ch12.entities.Singers;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {

    @Bean
    public Credentials credentials() {
        return new UsernamePasswordCredentials("prospring", "secret");
    }

    @Bean
    public CredentialsProvider credentialsProvider() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials());
        return credentialsProvider;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        CloseableHttpClient client = HttpClients
                .custom()
                .setDefaultCredentialsProvider(credentialsProvider())
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(singerMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
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
        xStreamMarshaller.setStreamDriver(new DomDriver());
        xStreamMarshaller.setAnnotatedClasses(Singer.class, Singers.class);
        xStreamMarshaller.getXStream().addDefaultImplementation(java.util.Date.class, java.sql.Date.class);
        xStreamMarshaller.getXStream().allowTypes(new Class[] {Singer.class, Singers.class});
        return xStreamMarshaller;
    }
}
