package br.com.pointel.goorv.dektop.pieces;

import javax.swing.JTextField;

public class GField extends JTextField {

    public GField() {
        super();
    }

    public GField(String text) {
        super(text);
    }

    public GField(int columns) {
        super(columns);
    }

    public GField(String text, int columns) {
        super(text, columns);
    }

    public GField put(String text) {
        setText(text);
        return this;
    }

}
