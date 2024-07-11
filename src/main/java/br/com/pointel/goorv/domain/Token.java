package br.com.pointel.goorv.domain;

public class Token {

    private final String chars;
    private final int begin;

    public Token(String chars, int begin) {
        this.chars = chars;
        this.begin = begin;
    }

    public String getChars() {
        return chars;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return chars.length();
    }

    public boolean isPipe() {
        return chars.equals("|");
    }

    public boolean isBreak() {
        return chars.contains("\n") || chars.contains("\r");
    }

}
