package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

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

    public GCombo<T> putAll(T... items) {
        for (T item : items) {
            put(item);
        }
        return this;
    }

    public GCombo<T> clear() {
        model.removeAllElements();
        return this;
    }

    public GCombo<T> del(int index) {
        model.removeElementAt(index);
        return this;
    }

    public GCombo<T> del(T item) {
        model.removeElement(item);
        return this;
    }

    public GCombo<T> delAll(T... items) {
        for (T item : items) {
            del(item);
        }
        return this;
    }

    public GCombo<T> select(int index) {
        setSelectedIndex(index);
        return this;
    }

    public GCombo<T> select(T item) {
        setSelectedItem(item);
        return this;
    }

    public GCombo<T> putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GCombo<T> putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    public GCombo<T> putAct(ActionListener action) {
        addActionListener(action);
        return this;
    }

}
