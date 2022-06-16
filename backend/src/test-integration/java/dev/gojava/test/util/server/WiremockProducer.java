package dev.gojava.test.util.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;

import javax.enterprise.inject.Produces;


public class WiremockProducer {

    public WireMockServer createWiremockServer() {
        return new WireMockServer(Options.DYNAMIC_PORT);
    }
}
