package com.prospring.ch11.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TaskToExecute {

    private final Logger logger = LoggerFactory.getLogger(TaskToExecute.class);

    @Autowired
    TaskExecutor taskExecutor;

    public void executeTask() {
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(() -> {
                try {
                    Thread.sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    logger.error("Thread interrupted.", e);
                }
                logger.info("Hello from thread: " + Thread.currentThread().getName());
            });
        }
    }
}
