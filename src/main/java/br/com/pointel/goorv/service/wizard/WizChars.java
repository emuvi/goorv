package br.com.pointel.goorv.service.wizard;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class WizChars {

    public static String makeParameterName(String ofTitle) {
        return ofTitle.replace(" ", "_").toUpperCase();
    }

    public static String mountGrid(List<Pair<String, String>> grid) {
        var result = new StringBuilder();
        var max = 0;
        for (var line : grid) {
            max = Math.max(max, line.getLeft().length());
        }
        for (var line : grid) {
            result.append(StringUtils.rightPad(line.getLeft(), max, '.'));
            result.append("...: ");
            result.append(line.getRight());
            result.append("\n");
        }
        return result.toString();
    }

    public static String getNameWithNewIndex(String name, int addIndex) {
        int begin = -1;
        int end = name.length();
        for (int i = 0; i < name.length(); i++) {
            char charAt = name.charAt(i);
            if (begin == -1) {
                if (Character.isDigit(charAt)) {
                    begin = i;
                }
            } else {
                if (!Character.isDigit(charAt)) {
                    end = i;
                    break;
                }
            }
        }
        if (begin == -1) {
            return name;
        }
        int number = Integer.parseInt(name.substring(begin, end));
        int newNumber = number + addIndex;
        String newNameNumber = StringUtils.leftPad(newNumber + "", end - begin, '0');
        return name.substring(0, begin) + newNameNumber + name.substring(end);
    }

    public static Set<String> getKeyWords(String source) {
        return getWords(removeAccents(source));
    }
    
    public static Set<String> getWords(String source) {
        var result = new HashSet<String>();
        var partsOnSpace = source.split("\\s+");
        for (var spaced : partsOnSpace) {
            result.addAll(getWordsInBounds(spaced));
        }
        return result;
    }

    public static Set<String> getWordsInBounds(String source) {
        var result = new HashSet<String>();

        Consumer<String> addWord = (word) -> {
            while (word.length() > 0 && !Character.isLetterOrDigit(word.charAt(word.length() -1))) {
                word = word.substring(0, word.length() -1);
            }
            if (!word.isEmpty()) {
                result.add(word.toLowerCase());
            }
        };
        
        var parts = source.toCharArray();
        var maker = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            var prior = i > 0 ? parts[i - 1] : 0;
            var actual = parts[i];
            var next = i < parts.length - 1 ? parts[i + 1] : 0;
            if (Character.isLetterOrDigit(actual)
                    || (Character.isDigit(prior) || Character.isDigit(next))) {
                maker.append(actual);
            } else {
                addWord.accept(maker.toString());
                maker = new StringBuilder();
            }
        }
        addWord.accept(maker.toString());
        return result;
    }
    
    public static String switchCase(String ofChars) { 
        var result = new StringBuilder();
        for (char c : ofChars.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else  {
                result.append(Character.toUpperCase(c));
            }
        }
        return result.toString();
    }
    
    public static String removeAccents(String text) {
        String decomposed = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(decomposed).replaceAll("");
    }

    public static List<String> parseStrings(String source) {
        var results = new ArrayList<String>();
        var insideQuotes = false;
        var maker = new StringBuilder();
        Consumer<String> addParsed = (String parsed) -> {
            if (parsed.length() > 0) {
                results.add(parsed);
            }
        };
        for (int i = 0; i < source.length(); i++) {
            char before = i > 0 ? source.charAt(i - 1) : ' ';
            char actual = source.charAt(i);
            char next = i < source.length() - 1 ? source.charAt(i + 1) : ' ';
            if (actual == '"' && before != '\\') {
                insideQuotes = !insideQuotes;
                if (!insideQuotes) {
                    addParsed.accept(maker.toString());
                    maker = new StringBuilder();
                }
            } else if (actual == ' ' && !insideQuotes) {
                addParsed.accept(maker.toString());
                maker = new StringBuilder();
            } else if (actual != '\\' || next != '"') {
                maker.append(actual);
            }
        }
        addParsed.accept(maker.toString());
        return results;
    }

}
