package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class GCombo<T> extends JComboBox<T> {

    private final DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>();

    public GCombo() {
        super();
        setModel(model);
    }

    public GCombo<T> put(T item) {
        model.addElement(item);
        return this;
    }

    public GCombo<T> putAction(ActionListener action) {
        addActionListener(action);
        return this;
    }


}
