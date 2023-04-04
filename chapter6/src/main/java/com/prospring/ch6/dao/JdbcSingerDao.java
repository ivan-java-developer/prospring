package com.prospring.ch6.dao;

import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static class SingerMapper implements RowMapper<Singer> {

        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Singer singer = new Singer();
            singer.setId(rs.getInt("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        }
    }

    @Override
    public List<Singer> findAll() {
        String sql = "select id, first_name, last_name, birth_date from singer";
        return namedParameterJdbcTemplate.query(sql, new SingerMapper());
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date " +
                "from singer s where s.first_name = :firstName";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("firstName", firstName);
        return namedParameterJdbcTemplate.query(sql, namedParameters, rs -> {
            List<Singer> list = new ArrayList<>();
            while (rs.next()) {
                Singer singer = new Singer();
                singer.setId(rs.getInt("id"));
                singer.setFirstName(rs.getString("first_name"));
                singer.setLastName(rs.getString("last_name"));
                singer.setBirthDate(rs.getDate("birth_date"));
                singer.setAlbums(new ArrayList<>());
                list.add(singer);
            }
            return list;
        });
    }

    @Override
    public String findLastNameById(Integer id) {
        String sql = "select last_name from singer where id = :singerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public String findFirstNameById(Integer id) {
        String sql = "select first_name from singer where id = :singerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public void insert(Singer singer) {
        String sql = "insert into singer (first_name, last_name, birth_date) " +
                "values (:firstName, :lastName, :birthDate)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        Map<java.lang.String, java.lang.Object> namedParameters = new HashMap<>();
        namedParameters.put("firstName", singer.getFirstName());
        namedParameters.put("lastName", singer.getLastName());
        namedParameters.put("birthDate", singer.getBirthDate());
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(namedParameters), keyHolder);
        int id = keyHolder.getKey().intValue();
        singer.setId(id);
    }

    @Override
    public void update(Singer singer) {
        String sql = "update singer set first_name = :firstName, last_name = :lastName, " +
                "birth_date = :birthDate where id = :singerId";
        Map<java.lang.String, java.lang.Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", singer.getId());
        namedParameters.put("firstName", singer.getFirstName());
        namedParameters.put("lastName", singer.getLastName());
        namedParameters.put("birthDate", singer.getBirthDate());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void delete(Singer singer) {
        String sql = "delete from singer where id = :singerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", singer.getId());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public List<Singer> findAllWithDetail() {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date, " +
                "a.id album_id, a.title, a.release_date " +
                "from singer s left join album a on s.id = a.singer_id";
        return namedParameterJdbcTemplate.query(sql, new SingerWithDetailsExtractor());
    }

    private static class SingerWithDetailsExtractor implements ResultSetExtractor<List<Singer>> {

        @Override
        public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Singer> map = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                Singer singer = map.get(id);
                if (map.get(id) == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Date releaseDate = rs.getDate("release_date");
                if (releaseDate != null) {
                    Album album = new Album();
                    album.setId(rs.getInt("album_id"));
                    album.setSingerId(id);
                    album.setReleaseDate(releaseDate);
                    album.setTitle(rs.getString("title"));
                    singer.addAlbum(album);
                }
            }
            return new ArrayList<Singer>(map.values());
        }
    }

    @Override
    public void insertWithDetail(Singer singer) {
        insert(singer);
        String sql = "insert into album (singer_id, title, release_date) " +
                "values (:singerId, :title, :releaseDate)";
        if (singer.getAlbums() != null) {
            singer.getAlbums().forEach(album -> {
                GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
                Map<String, Object> namedParameters = new HashMap<>();
                namedParameters.put("singerId", singer.getId());
                namedParameters.put("title", album.getTitle());
                namedParameters.put("releaseDate", album.getReleaseDate());
                namedParameterJdbcTemplate
                        .update(sql, new MapSqlParameterSource(namedParameters), keyHolder);
                int albumId = keyHolder.getKey().intValue();
                album.setId(albumId);
            });
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("namedParameterJdbcTemplate must not be null");
        }
    }
}
