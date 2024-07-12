package br.com.pointel.goorv.domain;

import java.util.List;

public class Command {

    private final Context context;
    private final List<Token> tokens;

    public Command(Context context, List<Token> tokens) {
        this.context = context;
        this.tokens = tokens;
    }

    public void run() {
        System.out.println("Running command");
        for (var token : tokens) {
            System.out.println("Running token: '" + token.getChars() + "'");
        }
    }

}
