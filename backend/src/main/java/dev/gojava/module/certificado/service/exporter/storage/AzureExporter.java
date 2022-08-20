package dev.gojava.module.certificado.service.exporter.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

@ApplicationScoped
public class AzureExporter implements StorageExporter {

    @Inject
    BlobServiceClient blobServiceClient;
    @Inject
    Logger logger;

    @Override
    public void export(List<File> files) {
        logger.debug("Start to export {} files to azure storage", files.size());

        BlobContainerClient container = createContainerIfDontExists();
        for (File file : files) {
            logger.debug("start to export file: {}", file.getName());
            BlobClient blobClient = container.getBlobClient(createBlobName(file));
//            blobClient.uploadFromFile(file.getAbsolutePath(), true);
            logger.debug("exported file: {}", file.getName());
        }
    }

    private BlobContainerClient createContainerIfDontExists() {
        BlobContainerClient container = blobServiceClient.getBlobContainerClient("international-dev-career-day");
//        if (!container.exists()) {
//            container.create();
//        }

        return container;
    }

    private String createBlobName(File file) {
        return file.getName();
    }
}
