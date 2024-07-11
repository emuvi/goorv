package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;

public class Parsed {

    private final String text;
    private final List<Token> tokens;

    public Parsed(String text) {
        this.text = text;
        this.tokens = new Tokener(text).get();
    }

    public String getText() {
        return text;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<Command> getCommands(Context context) {
        List<Command> commands = new ArrayList<>();
        List<Token> actual = new ArrayList<>();
        for (Token token : tokens) {
            if (token.isBreak()) {
                commands.add(new Command(context, actual));
                actual = new ArrayList<>();
            } else {
                actual.add(token);
            }
        }
        return commands;
    }

}
