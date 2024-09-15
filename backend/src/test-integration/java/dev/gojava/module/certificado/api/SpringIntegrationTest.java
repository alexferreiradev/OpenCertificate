package dev.gojava.module.certificado.api;

import dev.gojava.SpringOpenCertificateApplication;
import dev.gojava.core.producer.LoggerProducer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringOpenCertificateApplication.class)
@Import({LoggerProducer.class})
public abstract class SpringIntegrationTest {

    @LocalServerPort
    int port;

}
