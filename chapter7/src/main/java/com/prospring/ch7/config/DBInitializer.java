package com.prospring.ch7.config;

import com.prospring.ch7.dao.InstrumentDao;
import com.prospring.ch7.dao.SingerDao;
import com.prospring.ch7.entities.Album;
import com.prospring.ch7.entities.Instrument;
import com.prospring.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class DBInitializer {
    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired private SingerDao singerDao;
    @Autowired private InstrumentDao instrumentDao;


    @PostConstruct
    public void initDb() {
        logger.info("Starting database initialization...");

        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);

        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentDao.save(voice);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(LocalDate.of(1977, 10, 16));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);

        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(LocalDate.of(2017, 1, 20));
        singer.addAlbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(LocalDate.of(2009, 10, 17));
        singer.addAlbum(album2);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(LocalDate.of(1945, 2, 20));
        singer.addInstrument(guitar);

        Album album = new Album();
        album.setTitle("From The Cradle");
        album.setReleaseDate(LocalDate.of(1994, 8, 13));
        singer.addAlbum(album);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(LocalDate.of(1975, 3, 1));
        singer.addInstrument(guitar);

        singerDao.save(singer);
        logger.info("Database initialization finished.");
    }
}
