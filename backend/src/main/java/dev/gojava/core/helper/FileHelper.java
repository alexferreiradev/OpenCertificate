package dev.gojava.core.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipFile;

public final class FileHelper {

    /**
     * Verifica se um arquivo é valido.
     *
     * @param fileName nome do arquivo.
     * @return se arquivo pode ser encontrado e lido, é valido.
     */
    public static boolean isValidFile(String fileName) {
        if (fileName == null) {
            return false;
        }

        File file = new File(fileName);

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.read() > 0;
        } catch (java.io.IOException e) {
            return false;
        }
    }

    /**
     * Formata conteudo de arquivo removendo espaços e trocando / por -.
     *
     * @param text conteudo a ser formatado.
     * @return conteudo formatado.
     */
    public static String formatToValidFileName(String text) {
        String textFormatted = text.replaceAll("\\s", "_");
        textFormatted = textFormatted.replaceAll("/", "-");

        return textFormatted;
    }

    /**
     * Calcular tamanho de arquivo zip.
     *
     * @param arquivoZIP arquivo zip
     * @return tamanho de arquivo
     */
    public static long tamanhoArquivoZIP(ZipFile arquivoZIP) {
        if (arquivoZIP == null) return 0;

        return arquivoZIP.size();
    }

    /**
     * Cria arquivo ou lanca exception caso não conseguir criar, remover antigo.
     *
     * @param filePath caminho para arquivo.
     * @param replace  se deverá substituir o arquivo antigo.
     * @return arquivo criado ou encontrado
     */
    public static File createFileOrThrow(String filePath, boolean replace) {
        if (filePath == null) throw new NullPointerException("Caminho nulo");

        File file = new File(filePath);
        if (file.exists() && replace) {
            boolean fileDeleted = file.delete();
            if (!fileDeleted) throw new IllegalStateException("Não possível remover arquivo para substituição, tente remover o arquivo manualmente");
        }

        try {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) throw new IllegalStateException("Não foi possível criar arquivo: " + filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return file;
    }

    /**
     * Calcula tamanho de arquivo em MB.
     *
     * @param file arquivo.
     * @return tamanho de arquivo em MB.
     */
    public static long tamanhoArquivo(File file) {
        if (file == null) return 0;

        return file.length() >> 10;
    }

    /**
     * Cria uma pasta ou lança exception avisando sobre erro.
     *
     * @param directory pasta a ser criada.
     * @throws IllegalStateException caso não possa ser criada a pasta
     */
    public static void createDirOrThrow(File directory) {
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IllegalStateException("Pasta não pode ser criada");
            }
        }
    }
}
