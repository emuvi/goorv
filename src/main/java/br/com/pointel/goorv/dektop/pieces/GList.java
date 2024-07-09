package br.com.pointel.goorv.dektop.pieces;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class GList<T> extends JList<T> {
    
    private DefaultListModel<T> model = new DefaultListModel<>();

    public GList() {
        super();
        setModel(model);
    }

    public GList<T> put(T item) {
        model.addElement(item);
        return this;
    }

}
