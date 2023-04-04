package com.prospring.ch6.dao;

import com.prospring.ch6.dao.support.*;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcNewSingerDao implements NewSingerDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcNewSingerDao.class);

    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SelectByFirstName selectByFirstName;
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;
    private DeleteSinger deleteSinger;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        selectAllSingers = new SelectAllSingers(dataSource);
        selectByFirstName = new SelectByFirstName(dataSource);
        updateSinger = new UpdateSinger(dataSource);
        insertSinger = new InsertSinger(dataSource);
        deleteSinger = new DeleteSinger(dataSource);
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", firstName);
        return selectByFirstName.executeByNamedParam(params);
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", singer.getId());
        params.put("firstName", singer.getFirstName());
        params.put("lastName", singer.getLastName());
        params.put("birthDate", singer.getBirthDate());
        updateSinger.updateByNamedParam(params);
        logger.info("updated singer with id = {}", singer.getId());
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", singer.getFirstName());
        params.put("lastName", singer.getLastName());
        params.put("birthDate", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(params, keyHolder);
        singer.setId(keyHolder.getKey().intValue());
        logger.info("insert singer with id = {}", singer.getId());
    }

    @Override
    public void delete(Singer singer) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", singer.getId());
        deleteSinger.updateByNamedParam(params);
        logger.info("delete singer with id = {}", singer.getId());
    }

    @Override
    public void insertWithAlbums(Singer singer) {
        insert(singer);
        List<Album> albums = singer.getAlbums();
        if (albums != null) {
            InsertSingerAlbum insertSingerAlbum = new InsertSingerAlbum(dataSource);
            for (Album album : albums) {
                Map<String, Object> params = new HashMap<>();
                params.put("singerId", singer.getId());
                params.put("title", album.getTitle());
                params.put("releaseDate", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(params);
                logger.info("Album with id = {} for singer with id = {} inserted",
                        album.getId(), singer.getId());
            }
            insertSingerAlbum.flush();
        }
    }
}
