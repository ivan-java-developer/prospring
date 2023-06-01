package com.prospring.ch15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableMBeanExport;

import java.io.IOException;

@EnableMBeanExport
@SpringBootApplication(scanBasePackages = {"com.prospring.ch15"})
public class JMXBootApplication {

	private static Logger logger = LoggerFactory.getLogger(JMXBootApplication.class);

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext ctx = SpringApplication.run(JMXBootApplication.class, args);
		assert (ctx != null);
		logger.info("Started ...");
		System.in.read();
		ctx.close();
	}
}

