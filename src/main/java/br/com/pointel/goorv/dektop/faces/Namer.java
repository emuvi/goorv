package br.com.pointel.goorv.dektop.faces;

import java.util.function.Consumer;
import br.com.pointel.goorv.dektop.pieces.GAct;
import br.com.pointel.goorv.dektop.pieces.GBox;
import br.com.pointel.goorv.dektop.pieces.GBoxLine;
import br.com.pointel.goorv.dektop.pieces.GField;
import br.com.pointel.goorv.dektop.pieces.GFrame;

public class Namer extends GFrame {

    private final Consumer<String> consumer;
    
    private final GField nameField = new GField();
    private final GAct okAct = new GAct("Ok");
    private final GBox bodyBox = new GBoxLine().putAll(nameField, okAct);

    public Namer(Consumer<String> consumer) {
        super("Namer");
        this.consumer = consumer;
        setContentPane(bodyBox);
        initConsumer();
    }

    private void initConsumer() {
        okAct.putAction(e -> {
            consumer.accept(nameField.getText());
            close();
        });
    }

}
