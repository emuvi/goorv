package br.com.pointel.goorv.dektop.pieces;

import javax.swing.JSplitPane;

public class GSplitter extends JSplitPane {

    public GSplitter() {
        super();
    }

    public GSplitter putHonrizontal() {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        return this;
    }

    public GSplitter putVertical() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        return this;
    }

    public GSplitter putDivider(int location) {
        setDividerLocation(location);
        return this;
    }

    public GSplitter putLeft(GPanel panel) {
        setLeftComponent(panel);
        return this;
    }

    public GSplitter putRight(GPanel panel) {
        setRightComponent(panel);
        return this;
    }

    public GSplitter putTop(GPanel panel) {
        setTopComponent(panel);
        return this;
    }

    public GSplitter putBottom(GPanel panel) {
        setBottomComponent(panel);
        return this;
    }

}
