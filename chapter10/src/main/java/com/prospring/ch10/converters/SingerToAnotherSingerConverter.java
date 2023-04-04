package com.prospring.ch10.converters;

import com.prospring.ch10.objects.AnotherSinger;
import com.prospring.ch10.objects.Singer;
import org.springframework.core.convert.converter.Converter;

public class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger> {
    @Override
    public AnotherSinger convert(Singer singer) {
        AnotherSinger anotherSinger = new AnotherSinger();
        anotherSinger.setFirstName(singer.getLastName());
        anotherSinger.setLastName(singer.getFirstName());
        anotherSinger.setBirthDate(singer.getBirthDate());
        anotherSinger.setSite(singer.getSite());

        return anotherSinger;
    }
}
