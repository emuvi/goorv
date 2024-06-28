package br.com.pointel.goorv;

public class Param {
    
    private final String name;
    private final String brief;

    public Param(String name, String brief) {
        this.name = '-' + name;
        this.brief = brief;
    }

    public String getName() {
        return this.name;
    }

    public String getBrief() {
        return this.brief;
    }

}
