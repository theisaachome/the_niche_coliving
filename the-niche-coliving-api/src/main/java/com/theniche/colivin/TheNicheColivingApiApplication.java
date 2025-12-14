package com.theniche.colivin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.theniche.colivin")
@EnableJpaRepositories(basePackages = "com.theniche.colivin")
@EnableJpaAuditing
public class TheNicheColivingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheNicheColivingApiApplication.class, args);
    }

}
