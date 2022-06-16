package dev.gojava.test.util.server;

import com.github.tomakehurst.wiremock.WireMockServer;

public interface InjectableTest {

    void injectWireMockServer(WireMockServer server);

}
