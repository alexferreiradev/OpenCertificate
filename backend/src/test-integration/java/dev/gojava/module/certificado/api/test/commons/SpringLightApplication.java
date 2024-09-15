package dev.gojava.module.certificado.api.test.commons;

import dev.gojava.SpringOpenCertificateApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringLightApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringOpenCertificateApplication.class, args);
    }
}
