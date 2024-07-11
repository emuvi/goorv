package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class GCombo<T> extends JComboBox<T> implements GWays<GCombo<T>> {

    private final DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>();

    public GCombo() {
        super();
        setModel(model);
    }

    public GCombo<T> put(T item) {
        model.addElement(item);
        return this;
    }

    public GCombo<T> putAct(ActionListener action) {
        addActionListener(action);
        return this;
    }

}
