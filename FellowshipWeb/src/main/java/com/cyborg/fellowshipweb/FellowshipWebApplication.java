package com.cyborg.fellowshipweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.cyborg")
@EntityScan("com.cyborg.fellowshipdataaccess.entity")
@EnableMongoRepositories(basePackages = "com.cyborg.fellowshipdataaccess.repository")
@EnableTransactionManagement
public class FellowshipWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FellowshipWebApplication.class, args);
    }

}
