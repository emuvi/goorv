package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;

public class Piped {

    private final List<Token> tokens;
    private final List<List<Token>> results;

    public Piped(List<Token> tokens) {
        this.tokens = tokens;
        this.results = new ArrayList<>();
        parse();
    }

    private List<Token> actual = new ArrayList<>();

    private void parse() {
        for (Token token : tokens) {
            if (token.isPipe()) {
                addActual();
            } else {
                actual.add(token);
            }
        }
        addActual();
        actual = null;
    }

    private void addActual() {
        if (!actual.isEmpty()) {
            results.add(actual);
            actual = new ArrayList<>();
        }
    }

    public int size() {
        return results.size();
    }

    public List<Token> get(int index) {
        return results.get(index);
    }

}
