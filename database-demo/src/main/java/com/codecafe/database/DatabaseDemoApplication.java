package com.codecafe.database;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.codecafe.database.dao.PersonRepository;
import com.codecafe.database.entity.Person;

@SpringBootApplication
@EntityScan("com.codecafe.database.entity")
public class DatabaseDemoApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseDemoApplication.class);

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users before -> {}", personRepository.findAll());

        logger.info("User id 10001 -> {}", personRepository.findById(10001));

        logger.info("Deleting id 10002");
        personRepository.deleteById(10002);

        logger.info("Inserting new record -> {}", personRepository.save(new Person("Tom", "Germany", new Date())));

        Person p = personRepository.findById(10003);
        p.setLocation("Amsterdam");
        logger.info("Update id 10003 -> No of Rows Updated - {}", personRepository.save(p));

        logger.info("Inserting new record -> {}", personRepository.save(new Person("Ashley", "Atlanta", new Date())));

        logger.info("All users after -> {}", personRepository.findAll());

    }

}