package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class GField extends JTextField implements GWays<GField> {

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

    public GField putText(String text) {
        setText(text);
        return this;
    }

    public GField putEditable() {
        setEditable(true);
        return this;
    }

    public GField delEditable() {
        setEditable(false);
        return this;
    }

    public GField putColumns(int columns) {
        setColumns(columns);
        return this;
    }

    public GField putAct(ActionListener action) {
        addActionListener(action);
        return this;
    }

}
