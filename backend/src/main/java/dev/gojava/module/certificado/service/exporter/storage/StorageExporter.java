package dev.gojava.module.certificado.service.exporter.storage;

import java.io.File;
import java.util.List;

/**
 *  Interface that export list of files.
 */
public interface StorageExporter {

    void export(List<File> files);
}
