package br.com.pointel.goorv.domain;

public class Param {
    
    private final String name;
    private final String brief;

    public Param(String name, String brief) {
        this.name = '-' + name;
        this.brief = brief;
    }

    public String name() {
        return this.name;
    }

    public String brief() {
        return this.brief;
    }

}
