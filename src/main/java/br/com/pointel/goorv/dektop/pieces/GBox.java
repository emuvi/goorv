package br.com.pointel.goorv.dektop.pieces;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;


public class GBox extends JPanel {

    public GBox() {
        super();
    }

    public GBox(LayoutManager layout) {
        super(layout);
    }

    public GBox putAll(Component... components) {
        for (Component component : components) {
            add(component);
        }
        return this;
    }

}
