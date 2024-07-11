package br.com.pointel.goorv.domain;

import java.util.List;

public class Parsed {

    private final String text;
    private final List<Token> tokens;

    public Parsed(String text, List<Token> tokens) {
        this.text = text;
        this.tokens = tokens;
    }

}
