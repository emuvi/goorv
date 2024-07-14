package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class GStyle extends JTextPane implements GWays<GStyle> {

    public GStyle() {
        super();
    }

    public GStyle(String text) {
        super();
        putText(text);
    }

    public GStyle putText(String text) {
        setText(text);
        return this;
    }

    public GStyle putEditable() {
        setEditable(true);
        return this;
    }

    public GStyle delEditable() {
        setEditable(false);
        return this;
    }
    
    public GStyle putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GStyle putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    public GStyle putAct(ActionListener action) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                    action.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "CTRL+ENTER"));
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
