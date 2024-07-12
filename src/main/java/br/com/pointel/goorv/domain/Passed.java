package br.com.pointel.goorv.domain;

public class Passed {
    
    private final Param param;
    private final Token token;

    public Passed(Param param, Token token) {
        this.param = param;
        this.token = token;
    }

    public Param param() {
        return this.param;
    }

    public Token token() {
        return this.token;
    }

    

}
