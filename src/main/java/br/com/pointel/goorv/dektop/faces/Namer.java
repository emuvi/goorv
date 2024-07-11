package br.com.pointel.goorv.dektop.faces;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import br.com.pointel.goorv.dektop.pieces.GAct;
import br.com.pointel.goorv.dektop.pieces.GBox;
import br.com.pointel.goorv.dektop.pieces.GBoxBorder;
import br.com.pointel.goorv.dektop.pieces.GField;
import br.com.pointel.goorv.dektop.pieces.GFrame;

public class Namer extends GFrame {

    private final Consumer<String> consumer;
    
    private final GField nameField = new GField(30);
    private final GAct okAct = new GAct("Ok").putAct(this::actOk);
    private final GBox bodyBox = new GBoxBorder().putCenter(nameField).putEast(okAct).putBorder(4);

    public Namer(Consumer<String> consumer) {
        super("Namer");
        this.consumer = consumer;
        setContentPane(bodyBox);
        putDefaultButton(okAct);
        putEscaper();
    }

    private void actOk(ActionEvent event) {
        consumer.accept(nameField.getText());
        close();
    }

}
