package br.com.pointel.goorv.dektop.faces;

import java.io.File;
import br.com.pointel.goorv.dektop.pieces.GFrame;

public class Desk extends GFrame {
    
    private final File folder;

    public Desk(File folder) {
        super("Goorv");
        this.folder = folder;
    }

}
