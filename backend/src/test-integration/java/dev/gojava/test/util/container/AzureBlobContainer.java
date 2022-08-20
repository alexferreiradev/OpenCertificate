package dev.gojava.test.util.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class AzureBlobContainer extends GenericContainer implements ConfigurableContainer {
    public AzureBlobContainer() {
        super(DockerImageName.parse("mcr.microsoft.com/azure-storage/azurite:3.17.1"));

        withExposedPorts(10000);
        start();
    }

    @Override
    public Map<String, String> createQuarkusConfiguration() {
        String connectionString = String.format("DefaultEndpointsProtocol=http;" + "AccountName=devstoreaccount1;"
                        + "AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;"
                        + "BlobEndpoint=http://127.0.0.1:%d/devstoreaccount1;"
                , getFirstMappedPort());
        return ImmutableMap.of("%test.client.azure.connection-string", connectionString);
    }
}
