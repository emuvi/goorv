package br.com.pointel.goorv.domain;

public class SourceText extends Source {

    private final String name;

    private String text;

    public SourceText(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SourceText putText(String text) {
        this.text = text;
        return this;
    }

}
