package dev.gojava.core.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


public class LoggerProducer {

	@Produces
	public Logger getLoggerSLF4J(InjectionPoint bean) {
		return LoggerFactory.getLogger(bean.getClass());
	}
}
