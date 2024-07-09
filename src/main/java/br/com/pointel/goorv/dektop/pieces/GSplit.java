package br.com.pointel.goorv.dektop.pieces;

import java.awt.Component;
import javax.swing.JSplitPane;

public class GSplit extends JSplitPane {

    public GSplit() {
        super();
    }

    public GSplit(Component left, Component right) {
        super(JSplitPane.HORIZONTAL_SPLIT, left, right);
    }

    public GSplit(Component left, Component right, double location) {
        super(JSplitPane.HORIZONTAL_SPLIT, left, right);
        setDividerLocation(location);
    }

    public GSplit putHonrizontal() {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        return this;
    }

    public GSplit putVertical() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        return this;
    }

    public GSplit putLeft(Component component) {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(component);
        return this;
    }

    public GSplit putRight(Component component) {
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setRightComponent(component);
        return this;
    }

    public GSplit putTop(Component component) {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setTopComponent(component);
        return this;
    }

    public GSplit putBottom(Component component) {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setBottomComponent(component);
        return this;
    }

    public GSplit putDivider(int location) {
        setDividerLocation(location);
        return this;
    }

    public GSplit putDivider(double location) {
        setDividerLocation(location);
        return this;
    }

}
