package dev.gojava.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexHelper {

    /**
     * Faz uso de regex para encontrar alguma expressao no texto informado.
     *
     * @param text texto base
     * @param expression expressao regular
     * @return texto encontrado no texto base de acordo com expressão
     */
    public static String find(String text, String expression) {
        String result = null;

        Pattern compile = Pattern.compile(expression);
        Matcher matcher = compile.matcher(text);

        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    /**
     * Faz uso de regex para encontrar várias ocorrências no texto informado.
     *
     * @param text texto base
     * @param expression expressao regular
     * @return lista de ocorrencias encontradas.
     * @see RegexHelper#find(String, String)
     */
    public static List<String> findAll(String text, String expression) {
        List<String> resultList = new ArrayList<>();

        Pattern compile = Pattern.compile(expression);
        Matcher matcher = compile.matcher(text);

        while (matcher.find()) {
            resultList.add(matcher.group());
        }

        return resultList;
    }
}
