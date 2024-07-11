package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class GText extends JTextArea implements GWays<GText> {

    public GText() {
        super();
    }

    public GText(int rows, int columns) {
        super(rows, columns);
    }

    public GText(String text) {
        super(text);
    }

    public GText(String text, int rows, int columns) {
        super(text, rows, columns);
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

    public GText putColumns(int columns) {
        setColumns(columns);
        return this;
    }

    public GText putRows(int rows) {
        setRows(rows);
        return this;
    }

    public GText putAct(ActionListener action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
