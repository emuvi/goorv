package br.com.pointel.goorv.dektop.pieces;

import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class GFrame extends JFrame {

    public GFrame(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocationByPlatform(true);
        WizSwing.initFrame(this);
    }

    public GFrame putDefaultButton(JButton button) {
        getRootPane().setDefaultButton(button);
        return this;
    }

    public GFrame putEscaper() {
        WizSwing.initEscaper(this);
        return this;
    }

    public GFrame putEscaper(Supplier<Boolean> condition) {
        WizSwing.initEscaper(this, condition);
        return this;
    }

    public GFrame putExitOnClose() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return this;
    }

    public GFrame start() {
        pack();
        WizSwing.setAllCompontentsFont(this, WizSwing.fontMonospaced());
        setVisible(true);
        return this;
    }

    public void close() {
        WizSwing.close(this);
    }

}
