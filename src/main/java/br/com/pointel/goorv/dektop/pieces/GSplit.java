package br.com.pointel.goorv.dektop.pieces;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class GSplit extends JSplitPane implements GWays<GSplit> {

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

    public GSplit putOneTouchExpandable(boolean expandable) {
        setOneTouchExpandable(expandable);
        return this;
    }

    public GSplit putResizeWeight(double weight) {
        setResizeWeight(weight);
        return this;
    }
    
    public GSplit putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GSplit putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    public GSplit putAct(ActionListener action) {
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
