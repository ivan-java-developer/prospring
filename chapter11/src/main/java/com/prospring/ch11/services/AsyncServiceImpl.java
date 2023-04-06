package com.prospring.ch11.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

    final static Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("Star execution of async task");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("Task interruption", e);
        }
        logger.info("Complete execution of async task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        logger.info("Star execution of async task with return for " + name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Task interruption", e);
        }
        logger.info("Complete execution of async task with return for " + name);
        return new AsyncResult<>("Hello: " + name);
    }
}
