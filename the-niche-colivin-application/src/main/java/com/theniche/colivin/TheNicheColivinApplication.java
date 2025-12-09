package com.theniche.colivin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.theniche.colivin")
@EntityScan(basePackages = "com.theniche.colivin.domain.entity")
@EnableJpaRepositories(basePackages = "com.theniche.colivin.domain.repository")
@EnableJpaAuditing
public class TheNicheColivinApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TheNicheColivinApplication.class, args);
    }
}
