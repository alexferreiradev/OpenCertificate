package dev.gojava.core.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class LoggerProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerProducer.class);

    /**
     * Create logger.
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger createLogger(InjectionPoint injectionPoint) {
        Logger logger = LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
        LOGGER.trace("Log injection configured to {}", injectionPoint.getMember().getDeclaringClass().getName());

        return logger;
    }
}
