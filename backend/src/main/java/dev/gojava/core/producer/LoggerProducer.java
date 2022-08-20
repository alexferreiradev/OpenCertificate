package dev.gojava.core.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Scope;
import javax.inject.Singleton;


public class LoggerProducer {

    @Produces
    @Dependent
    public Logger getLoggerSLF4J(InjectionPoint bean) {
        return LoggerFactory.getLogger(bean.getClass());
    }
}
