package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class GAct extends JButton implements GWays<GAct> {

    public GAct() {
        super();
    }

    public GAct(String title) {
        super(title);
    }

    public GAct putText(String title) {
        setText(title);
        return this;
    }

    public GAct putShort(char cut) {
        setMnemonic(cut);
        return this;
    }

    public GAct putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GAct putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    public GAct putAct(ActionListener action) {
        addActionListener(action);
        return this;
    }

}
