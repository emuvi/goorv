package br.com.pointel.goorv.domain;

public abstract class Source {

    public abstract String getName();

    public abstract String getText();

    public abstract void setText(String text);

    public Parsed parse() {
        var text = getText();
        return new Parsed(text);
    }

    public String toString() {
        return getName();
    }

}
