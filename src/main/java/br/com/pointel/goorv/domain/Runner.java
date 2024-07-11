package br.com.pointel.goorv.domain;

public class Runner {

    private final Source source;
    private final String title;

    public Runner(Source source, String title) {
        this.source = source;
        this.title = title;
    }

    public String toString() {
        return title;
    }

}
