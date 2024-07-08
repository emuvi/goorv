package br.com.pointel.goorv.dektop.pieces;

import javax.swing.JFrame;
import br.com.pointel.goorv.service.wizard.WizSwing;

public class GFrame extends JFrame {

    public GFrame(String title) {
        super(title);
        WizSwing.initFrame(this);
    }

}
