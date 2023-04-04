package com.prospring.ch8.service;

import com.prospring.ch8.entities.Album;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();
    List<Album> findByTitle(String title);
}
