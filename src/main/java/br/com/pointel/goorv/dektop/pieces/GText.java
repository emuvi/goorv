package br.com.pointel.goorv.dektop.pieces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GText extends JTextArea implements GWays<GText> {

    public GText() {
        super();
    }

    public GText(int rows, int columns) {
        super(rows, columns);
    }

    public GText(String text) {
        super(text);
    }

    public GText(String text, int rows, int columns) {
        super(text, rows, columns);
    }

    public GText putText(String text) {
        setText(text);
        return this;
    }

    public GText putEditable() {
        setEditable(true);
        return this;
    }

    public GText delEditable() {
        setEditable(false);
        return this;
    }

    public GText putWrap() {
        setLineWrap(true);
        setWrapStyleWord(true);
        return this;
    }

    public GText delWrap() {
        setLineWrap(false);
        setWrapStyleWord(false);
        return this;
    }

    public GText putColumns(int columns) {
        setColumns(columns);
        return this;
    }

    public GText putRows(int rows) {
        setRows(rows);
        return this;
    }
    
    public GText putHint(String hint) {
        setToolTipText(hint);
        return this;
    }

    public GText putBorder(int gap) {
        setBorder(new EmptyBorder(gap, gap, gap, gap));
        return this;
    }

    public GText putAct(ActionListener action) {
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
