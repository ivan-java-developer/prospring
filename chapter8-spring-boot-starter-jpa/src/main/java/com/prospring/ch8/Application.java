package com.prospring.ch8;

import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    SingerRepository singerRepository;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.in.read();
        ctx.close();
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {
        singerRepository.findByFirstName("John").forEach(singer -> {
            System.out.println(singer);
            Set<Album> albums = singer.getAlbums();
            if (albums != null) {
                albums.forEach(System.out::println);
            }
            Set<Instrument> instruments = singer.getInstruments();
            if (instruments != null) {
                instruments.forEach(System.out::println);
            }
        });
    }
}
