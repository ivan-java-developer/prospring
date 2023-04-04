package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;

public interface SingerService {
    Singer save(Singer singer);
    long count();
}
