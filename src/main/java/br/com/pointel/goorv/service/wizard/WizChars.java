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

    public static String makeParameterKeyName(String ofTitle) {
        return ofTitle
                .replaceAll("\\s+", "_")
                .replaceAll("\\_+", "_")
                .replace("_-_", "_")
                .toUpperCase();
    }

    public static String mountGrid(List<Pair<String, String>> ofPairs) {
        var result = new StringBuilder();
        var max = 0;
        for (var line : ofPairs) {
            max = Math.max(max, line.getLeft().length());
        }
        for (var line : ofPairs) {
            result.append(StringUtils.rightPad(line.getLeft(), max, '.'));
            result.append("...: ");
            result.append(line.getRight());
            result.append("\n");
        }
        return result.toString();
    }

    public static String getNameWithNewIndex(String onName, int addIndex) {
        int begin = -1;
        int end = onName.length();
        for (int i = 0; i < onName.length(); i++) {
            char charAt = onName.charAt(i);
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
            return onName;
        }
        int number = Integer.parseInt(onName.substring(begin, end));
        int newNumber = number + addIndex;
        String newNameNumber = StringUtils.leftPad(newNumber + "", end - begin, '0');
        return onName.substring(0, begin) + newNameNumber + onName.substring(end);
    }

    public static Set<String> getKeyWords(String ofSource) {
        return getWords(removeAccents(ofSource));
    }
    
    public static Set<String> getWords(String ofSource) {
        var result = new HashSet<String>();
        var partsOnSpace = ofSource.split("\\s+");
        for (var spaced : partsOnSpace) {
            result.addAll(getWordsInBounds(spaced));
        }
        return result;
    }

    public static Set<String> getWordsInBounds(String ofSource) {
        var result = new HashSet<String>();

        Consumer<String> addWord = (word) -> {
            while (word.length() > 0 && !Character.isLetterOrDigit(word.charAt(word.length() -1))) {
                word = word.substring(0, word.length() -1);
            }
            if (!word.isEmpty()) {
                result.add(word.toLowerCase());
            }
        };
        
        var parts = ofSource.toCharArray();
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
    
    public static String removeAccents(String ofChars) {
        String decomposed = Normalizer.normalize(ofChars, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(decomposed).replaceAll("");
    }

    public static List<String> parseArguments(String ofCommand) {
        var context = new ParseArgumentsContext();
        for (int i = 0; i < ofCommand.length(); i++) {
            if (context.jumpNextChar) {
                context.jumpNextChar = false;
                continue;
            }
            context.actualChar = ofCommand.charAt(i);
            context.nextChar = i < ofCommand.length() - 1 ? ofCommand.charAt(i + 1) : ' ';
            if (context.insideQuotes) {
                parseArgumentsInsideQuotes(context);
            } else {
                parseArgumentsOutsideQuotes(context);
            }
        }
        context.addArgument();
        return context.results;
    }

    private static class ParseArgumentsContext {
        List<String> results = new ArrayList<>();
        StringBuilder maker = new StringBuilder();
        boolean insideQuotes = false;
        boolean jumpNextChar = false;
        char actualChar = ' ';
        char nextChar = ' ';

        public void addArgument() {
            var argument = maker.toString().trim();
            if (argument.length() > 0) {
                results.add(argument);
            }
            maker = new StringBuilder();
        }

        public void addActual() {
            maker.append(actualChar);
        }

        public void addSpecial(char special) {
            maker.append(special);
        }
    }

    private static void parseArgumentsInsideQuotes(ParseArgumentsContext onContext) {
        if (onContext.actualChar == '\\') {
            parseArgumentsInsideQuotesSpecialChars(onContext);
            onContext.jumpNextChar = true;
        } else {
            if (onContext.actualChar == '"') {
                onContext.insideQuotes = false;
            }
            onContext.addActual();
        }
    }

    private static void parseArgumentsInsideQuotesSpecialChars(ParseArgumentsContext onContext) {
        if (onContext.nextChar == 'n') {
            onContext.addSpecial('\n');
        } else if (onContext.nextChar == 'r') {
            onContext.addSpecial('\r');
        } else if (onContext.nextChar == 't') {
            onContext.addSpecial('\t');
        } else if (onContext.nextChar == 's') {
            onContext.addSpecial('\s');
        } else if (onContext.nextChar == 'f') {
            onContext.addSpecial('\f');
        } else {
            onContext.addSpecial(onContext.nextChar);
        }
    }

    private static void parseArgumentsOutsideQuotes(ParseArgumentsContext onContext) {
        if (onContext.actualChar == ' ') {
            onContext.addArgument();
        } else {
            if (onContext.actualChar == '"') {
                onContext.insideQuotes = true;
            }
            onContext.addActual();
        }
    }

    public static String removeQuotes(String ofText) {
        if (ofText.startsWith("\"") && ofText.endsWith("\"")) {
            return ofText.substring(1, ofText.length() - 1);
        }
        return ofText;
    }

}
