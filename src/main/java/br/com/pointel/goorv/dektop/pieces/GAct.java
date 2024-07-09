package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GAct extends JButton {

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

    public GAct putAction(ActionListener action) {
        addActionListener(action);
        return this;
    }

}
