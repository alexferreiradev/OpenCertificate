package dev.gojava.module.certificado.api.test.commons.container;

import org.springframework.test.context.DynamicPropertyRegistry;

import java.util.Map;

public interface InjectedContainer {

    Map<String, String> configureProperties();

    void configure(DynamicPropertyRegistry registry);
}
