package com.prospring.ch8.service;

import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.repositories.SingerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("singerDataService")
@Transactional
public class SingerDataServiceImpl implements SingerDataService {

    @Autowired
    SingerDataRepository singerDataRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return StreamSupport.stream(singerDataRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByFirstName(String firstName) {
        return singerDataRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return singerDataRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
