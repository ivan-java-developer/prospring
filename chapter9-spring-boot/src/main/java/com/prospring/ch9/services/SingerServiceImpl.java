package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.repositories.SingerRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.xa.XAException;
import java.time.LocalDate;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    private SingerRepository singerRepository;
    private JmsTemplate jmsTemplate;

    public SingerServiceImpl(SingerRepository singerRepository, JmsTemplate jmsTemplate) {
        this.singerRepository = singerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Singer save(Singer singer) {
        jmsTemplate.convertAndSend("singers", "Just saved: " + singer);
        if (singer == null) {
            throw new RuntimeException(new XAException("Simulation of something going wrong."));
        }
        return singerRepository.save(singer);
    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return singerRepository.count();
    }
}
