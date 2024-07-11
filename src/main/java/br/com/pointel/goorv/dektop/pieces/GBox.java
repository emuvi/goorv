package br.com.pointel.goorv.dektop.pieces;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class GBox extends JPanel implements GWays<GBox> {

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

    public GBox putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    @Override
    public GBox putAct(ActionListener action) {
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
