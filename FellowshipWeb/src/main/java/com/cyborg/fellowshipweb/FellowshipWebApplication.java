package com.cyborg.fellowshipweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cyborg")
public class FellowshipWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FellowshipWebApplication.class, args);
    }

}
