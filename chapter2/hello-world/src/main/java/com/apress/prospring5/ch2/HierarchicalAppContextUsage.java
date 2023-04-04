package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.music.Song;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HierarchicalAppContextUsage {
    public static void main(String[] args) {
        GenericXmlApplicationContext parent =
                new GenericXmlApplicationContext("classpath:spring/parent-context.xml");
        GenericXmlApplicationContext child = new GenericXmlApplicationContext();
        child.load("classpath:spring/child-context.xml");
        child.setParent(parent);
        child.refresh();

        Song song1 = child.getBean("song1", Song.class);
        Song song2 = child.getBean("song2", Song.class);
        Song song3 = child.getBean("song3", Song.class);

        System.out.println(song1.getTitle());
        System.out.println(song2.getTitle());
        System.out.println(song3.getTitle());

        child.close();
        parent.close();
    }
}
