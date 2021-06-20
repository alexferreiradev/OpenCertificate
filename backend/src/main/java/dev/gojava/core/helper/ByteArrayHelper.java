package dev.gojava.core.helper;

public final class ByteArrayHelper {

    /**
     * Testa se um array de byte é igual ao outro.
     *
     * @param b1 array 1
     * @param b2 array 2
     * @return true se são iguais
     */
    public static boolean isSameByteArray(byte[] b1, byte[] b2) {
        if (b1.length != b2.length) {
            return false;
        }

        for (int i = 0; i < b1.length; i++) {
            byte byteComparing = b1[i];
            if (byteComparing != b2[i]) {
                return false;
            }
        }

        return true;
    }
}
