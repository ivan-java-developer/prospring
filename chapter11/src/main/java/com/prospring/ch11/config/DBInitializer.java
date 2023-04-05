package com.prospring.ch11.config;

import com.prospring.ch11.entities.Car;
import com.prospring.ch11.repositories.CarRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DBInitializer {

    private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        Car car = new Car();
        car.setLicensePlate("e 002 ao 69");
        car.setManufacturer("Ford");
        car.setManufactureDate(DateTime.parse("12-09-2006", dateTimeFormatter));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("c 553 xx 77");
        car.setManufacturer("Ford");
        car.setManufactureDate(DateTime.parse("12-09-2006", dateTimeFormatter));
        carRepository.save(car);

        car = new Car();
        car.setLicensePlate("y 644 kk 23");
        car.setManufacturer("Ford");
        car.setManufactureDate(DateTime.parse("12-09-2006", dateTimeFormatter));
        carRepository.save(car);

        logger.info("Database initialization finished.");
    }
}
