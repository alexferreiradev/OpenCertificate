package dev.gojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringOpenCertificateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringOpenCertificateApplication.class, args);
    }
}
