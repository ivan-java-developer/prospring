package com.prospring.ch11.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("scheduledCarService")
@Repository
@Transactional
public class ScheduledCarServiceImpl extends CarServiceImpl {

    @Scheduled(fixedDelay = 10000)
    @Override
    public void updateCarAgeJob() {
        super.updateCarAgeJob();
    }
}
