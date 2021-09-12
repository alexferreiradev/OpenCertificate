package dev.gojava.core.helper.zip;

import dev.gojava.core.helper.FileHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ZipHelper {

    /**
     * Cria arquivo zip e outstream para este arquivo.
     *
     * @param filePath caminho para arquivo zip.
     * @return stream para arquivo zip.
     * @throws ZipFileNotCreatedException caso não consiga criar arquivo ou escrever.
     */
    public static ZipOutputStream createZipOutputstreamTo(String filePath) throws ZipFileNotCreatedException {
        if (filePath == null) throw new ZipFileNotCreatedException("Caminho nulo");
        File fileCreated = FileHelper.createFileOrThrow(filePath, true);
        try {
            return new ZipOutputStream(new FileOutputStream(fileCreated));
        } catch (FileNotFoundException e) {
            throw new ZipFileNotCreatedException("Erro ao tentar criar arquivo zip: " + filePath, e);
        }
    }

    /**
     * Criaçao de bytes de arquivo zip com uma lista de arquivos a serem comprimidos.
     *
     * @param zipOut       arquivo zip
     * @param fileList     lista de arquivos
     * @param bufferLenght tamanho do buffer utilizado para escrever os bytes dos arquivos. Escolha um valor medio para o tamanho dos arquivos, o padrão é 8GB.
     * @throws ZipFileNotCreatedException quando não consegue escrever no arquivo zip.
     */
    public static void writeZipFrom(ZipOutputStream zipOut, List<File> fileList, int bufferLenght) throws ZipFileNotCreatedException {
        if (zipOut == null) throw new NullPointerException("Stream de zip nulo");
        if (fileList == null) throw new NullPointerException("Lista de certificados nula");

        for (File file : fileList) {
            try {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOut.putNextEntry(zipEntry);

                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                while (bufferedInputStream.available() > 0) {
                    zipOut.write(bufferedInputStream.readNBytes(bufferLenght));
                }
            } catch (IOException e) {
                throw new ZipFileNotCreatedException("Erro ao tentar escrever em arquivo zip");
            }
        }
    }
}
