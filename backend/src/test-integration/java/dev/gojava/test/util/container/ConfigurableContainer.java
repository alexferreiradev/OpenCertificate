package dev.gojava.test.util.container;

import java.util.Map;

public interface ConfigurableContainer {

    Map<String, String> createQuarkusConfiguration();
}
