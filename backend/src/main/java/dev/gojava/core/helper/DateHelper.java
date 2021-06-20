package dev.gojava.core.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public final class DateHelper {

    /**
     * Cria uma string para data e estilo informado.
     *
     * @param date data
     * @param style estilo, Date.SHORT|*.
     * @return string representando data.
     */
    public static String format(Date date, int style) {
        DateFormat dateInstance = DateFormat.getDateInstance(style, Locale.getDefault());
        return dateInstance.format(date);
    }

    /**
     * Faz conversão de string em Date.
     *
     * @param stringDate texto a ser convertido.
     * @param style estilo que esta o texto, Date.SHORT|*.
     * @return objeto Date convertido.
     */
    public static Date parse(String stringDate, int style) {
        DateFormat dateInstance = DateFormat.getDateInstance(style, Locale.getDefault());
        try {
            return dateInstance.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
