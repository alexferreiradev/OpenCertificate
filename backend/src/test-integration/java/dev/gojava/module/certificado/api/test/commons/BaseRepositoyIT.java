package dev.gojava.module.certificado.api.test.commons;

import dev.gojava.module.certificado.api.test.commons.container.CustomPostgresContainer;
import dev.gojava.module.certificado.api.test.commons.container.InjectedContainer;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseRepositoyIT {

    private static final InjectedContainer sqlContainer = new CustomPostgresContainer();

    @DynamicPropertySource
    static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        CustomPostgresContainer postgresContainer = (CustomPostgresContainer) sqlContainer;
        postgresContainer.start();

        sqlContainer.configure(dynamicPropertyRegistry);
    }
}
