package com.example.mongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.example.mongodb.domain.Person;
import com.mongodb.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MongodbApplication.class, args);
        MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "database");
        mongoOps.insert(new Person("Joe", 34));

        log.info("{}",mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));

        mongoOps.dropCollection("person");
    }

}
