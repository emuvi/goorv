package br.com.pointel.goorv.dektop.pieces;

import java.awt.FlowLayout;

public class GBoxLine extends GBox {

    public GBoxLine() {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    public GBoxLine(int gap) {
        super(new FlowLayout(FlowLayout.LEFT, gap, gap));
    }

    public GBoxLine(int hgap, int vgap) {
        super(new FlowLayout(FlowLayout.LEFT, hgap, vgap));
    }

    public GBoxLine putOnLeft() {
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.LEFT);
        return this;
    }

    public GBoxLine putOnCenter() {
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.CENTER);
        return this;
    }

    public GBoxLine putOnRight() {
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.RIGHT);
        return this;
    }

    public GBoxLine putOnJustify() {
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.LEADING);
        return this;
    }

    public GBoxLine putOnSpace() {
        ((FlowLayout) getLayout()).setAlignment(FlowLayout.TRAILING);
        return this;
    }

}
