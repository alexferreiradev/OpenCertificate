package dev.gojava.core.producer;

import com.azure.core.util.ClientOptions;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

public class AzureBlobProducer {

    @Inject
    @ConfigProperty(name = "client.azure.connection-string")
    String connectionString;

    @Produces
    @Dependent
    public BlobServiceClient createContainer() {
        return new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
    }
}
