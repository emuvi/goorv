package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class GPace extends JProgressBar implements GWays<GPace> {

    private static final long serialVersionUID = 1L;

    public GPace() {
        super();
    }

    public GPace(int min, int max) {
        super(min, max);
    }

    public GPace putMin(int min) {
        setMinimum(min);
        return this;
    }

    public GPace putMax(int max) {
        setMaximum(max);
        return this;
    }

    public GPace putPace(int value) {
        setValue(value);
        return this;
    }
    
    public GPace putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GPace putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    @Override
    public GPace putAct(ActionListener action) {
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
