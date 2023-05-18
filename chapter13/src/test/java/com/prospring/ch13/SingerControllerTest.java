package com.prospring.ch13;

import com.prospring.ch13.controllers.SingerController;
import com.prospring.ch13.entities.Singer;
import com.prospring.ch13.services.SingerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    private SingerController singerController;
    private SingerService singerService;

    @BeforeEach
    public void init() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singers.add(singer);
        singerService = mock(SingerService.class);
        singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService", singerService);

    }

    @Test
    public void testList() {
        when(singerService.findAll()).thenReturn(singers);

        List<Singer> modelSingers = singerController.listData();

        assertEquals(1, modelSingers.size());
        assertEquals("John", modelSingers.get(0).getFirstName());
    }

    @Test
    public void testCreate() {
        final Singer newSinger = new Singer();
        newSinger.setId(555L);
        newSinger.setFirstName("BB");
        newSinger.setLastName("King");

        when(singerService.save(newSinger)).thenAnswer(invocation -> {
            singers.add(newSinger);
            return newSinger;
        });

        Singer singer = singerController.create(newSinger);

        assertEquals(555L, singer.getId());
        assertEquals("BB", singer.getFirstName());
        assertEquals("King", singer.getLastName());
    }
}
