package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;

public class Commander {

    private final List<Token> tokens;
    private final Runner runner;
    private final Context context;
    
    public Commander(String text, Runner runner, Context context) {
        this.tokens = new Tokener(text).parse();
        this.runner = runner;
        this.context = context;
        this.commands = new ArrayList<>();
    }

    private List<Command> commands;
    private List<Token> actual;

    public List<Command> parse() {
        commands = new ArrayList<>();
        actual = new ArrayList<>();
        for (Token token : tokens) {
            if (token.isBreak()) {
                addActual();
            } else {
                actual.add(token);
            }
        }
        addActual();
        return commands;
    }

    private void addActual() {
        if (!actual.isEmpty()) {
            commands.add(new Command(runner, context, actual));
            actual = new ArrayList<>();
        }
    }

}
