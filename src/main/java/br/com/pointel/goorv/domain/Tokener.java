package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;

public class Tokener {

    private final String text;

    private List<Token> tokens;
    private StringBuilder maker;
    private boolean insideQuotes;
    private char priorChar;
    private char actualChar;
    private char nextChar;
    private int begin;
    private int index;

    public Tokener(String text) {
        this.text = text;
    }

    public List<Token> parse() {
        initParse();
        makeParse();
        return tokens;
    }

    private void initParse() {
        tokens = new ArrayList<>();
        maker = new StringBuilder();
        insideQuotes = false;
        priorChar = ' ';
        actualChar = ' ';
        nextChar = ' ';
        begin = 0;
        index = 0;
    }

    private void makeParse() {
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
