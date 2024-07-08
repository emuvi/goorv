package br.com.pointel.goorv.dektop.pieces;

import java.awt.Component;
import javax.swing.JPanel;

public class GPanel extends JPanel {

    public GPanel() {
        super();
    }

    public GPanel put(Component component) {
        add(component);
        return this;
    }

}
