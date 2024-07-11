package br.com.pointel.goorv.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Parsed implements Serializable {

    private final String text;

    private List<Token> tokens = new ArrayList<>();
    private StringBuilder maker = new StringBuilder();
    private boolean insideQuotes = false;
    private char priorChar = ' ';
    private char actualChar = ' ';
    private char nextChar = ' ';
    private int begin = 0;
    private int index = 0;

    public Parsed(String text) {
        this.text = text;
        this.parseTokens();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void parseTokens() {
        for (index = 0; index < text.length(); index++) {
            priorChar = index > 0 ? text.charAt(index - 1) : ' ';
            actualChar = text.charAt(index);
            nextChar = index < text.length() - 1 ? text.charAt(index + 1) : ' ';
            if (insideQuotes) {
                parseArgumentsInsideQuotes();
            } else {
                parseArgumentsOutsideQuotes();
            }
        }
        addToken();
    }

    private void parseArgumentsInsideQuotes() {
        if (actualChar == '"' && priorChar != '\\') {
            insideQuotes = false;
        }
        addActual();
    }

    private void parseArgumentsOutsideQuotes() {
        if (actualChar == ' ') {
            addToken();
            begin = index + 1;
        } else if (actualChar == '\r' || actualChar == '\n') {
            if (priorChar != '\r' && priorChar != '\n') {
                addToken();
                begin = index;
            }
            addActual();
            if (nextChar != '\r' && nextChar != '\n') {
                addToken();
                begin = index + 1;
            }
        } else {
            if (actualChar == '"') {
                insideQuotes = true;
            }
            addActual();
        }
    }

    private void addToken() {
        var chars = maker.toString();
        if (chars.length() > 0) {
            tokens.add(new Token(chars, begin));
        }
        maker = new StringBuilder();
    }

    private void addActual() {
        maker.append(actualChar);
    }

}
