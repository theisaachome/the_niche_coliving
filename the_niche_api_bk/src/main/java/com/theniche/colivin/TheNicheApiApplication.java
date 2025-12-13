package com.theniche.colivin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TheNicheApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheNicheApiApplication.class, args);
    }

}
