package br.com.pointel.goorv.dektop.pieces;

import javax.swing.JTextArea;

public class GText extends JTextArea {

    public GText() {
        super();
    }

    public GText(String text) {
        super(text);
    }

    public GText putText(String text) {
        setText(text);
        return this;
    }

    public GText putEditable() {
        setEditable(true);
        return this;
    }

    public GText delEditable() {
        setEditable(false);
        return this;
    }

    public GText putWrap() {
        setLineWrap(true);
        setWrapStyleWord(true);
        return this;
    }

    public GText delWrap() {
        setLineWrap(false);
        setWrapStyleWord(false);
        return this;
    }

}
