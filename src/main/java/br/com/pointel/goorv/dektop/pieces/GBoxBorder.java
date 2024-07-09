package br.com.pointel.goorv.dektop.pieces;

import java.awt.BorderLayout;
import java.awt.Component;

public class GBoxBorder extends GBox {

    public GBoxBorder() {
        super(new BorderLayout(0, 0));
    }

    public GBoxBorder(int gap) {
        super(new BorderLayout(gap, gap));
    }

    public GBoxBorder(int hgap, int vgap) {
        super(new BorderLayout(hgap, vgap));
    }

    public GBoxBorder putCenter(Component component) {
        add(component, BorderLayout.CENTER);
        return this;
    }

    public GBoxBorder putNorth(Component component) {
        add(component, BorderLayout.NORTH);
        return this;
    }

    public GBoxBorder putSouth(Component component) {
        add(component, BorderLayout.SOUTH);
        return this;
    }

    public GBoxBorder putEast(Component component) {
        add(component, BorderLayout.EAST);
        return this;
    }

    public GBoxBorder putWest(Component component) {
        add(component, BorderLayout.WEST);
        return this;
    }

}
