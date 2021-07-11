package dev.gojava.core.helper;

import java.io.File;
import java.io.FileInputStream;
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
    public static long tamanhoArquivo(ZipFile arquivoZIP) {
        if (arquivoZIP == null) return 0;

        return arquivoZIP.size();
    }
}
