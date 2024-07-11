package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class GList<T> extends JList<T> implements GWays<GList<T>> {
    
    private DefaultListModel<T> model = new DefaultListModel<>();

    public GList() {
        super();
        setModel(model);
    }

    public GList<T> put(T item) {
        model.addElement(item);
        return this;
    }

    public GList<T> putAll(T... items) {
        for (T item : items) {
            put(item);
        }
        return this;
    }

    public GList<T> clear() {
        model.clear();
        return this;
    }

    public GList<T> del(int index) {
        model.remove(index);
        return this;
    }

    public GList<T> del(T item) {
        model.removeElement(item);
        return this;
    }

    public GList<T> delAll(T... items) {
        for (T item : items) {
            del(item);
        }
        return this;
    }

    public GList<T> sort(Comparator<T> comparator) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            list.add(model.getElementAt(i));
        }
        list.sort(comparator);
        model.clear();
        list.forEach(item -> model.addElement(item));
        return this;
    }

    public GList<T> putAct(ActionListener action) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    action.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    action.actionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "DOUBLE_CLICK"));
                }
            }
        });
        return this;
    }

}
