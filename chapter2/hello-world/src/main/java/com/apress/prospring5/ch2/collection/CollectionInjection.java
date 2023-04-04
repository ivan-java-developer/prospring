package com.apress.prospring5.ch2.collection;

import com.apress.prospring5.ch2.music.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service("injectCollection")
public class CollectionInjection {
//    @Resource(name="map")
    @Autowired
    @Qualifier("map")
    private Map<String, Song> map;

    @Resource(name="list")
    private List list;

    @Resource(name="set")
    private Set set;

    @Resource(name="properties")
    private Properties properties;

    public void setMap(Map<String, Song> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void displayCollections() {
        System.out.println("Map:");
        map.forEach((key, value) -> System.out.println("Key: " + key + " , Value: " + value));
        System.out.println("\nList:");
        list.forEach(System.out::println);
        System.out.println("\nSet:");
        set.forEach(System.out::println);
        System.out.println("\nProperties:");
        properties.forEach((key, value) -> System.out.println("Key: " + key + " , Value: " + value));
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext(
                        "classpath:spring/collection-app-context-used-util.xml");
        CollectionInjection collectionInjection =
                ctx.getBean("injectCollection", CollectionInjection.class);
        collectionInjection.displayCollections();
        ctx.close();
    }
}
