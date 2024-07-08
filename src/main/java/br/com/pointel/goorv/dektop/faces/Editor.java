package br.com.pointel.goorv.dektop.faces;

import java.io.File;
import br.com.pointel.goorv.dektop.pieces.GFrame;

public class Editor extends GFrame {

    private final File file;

    public Editor(File file) {
        super("Editor - " + file.getName());
        this.file = file;
    }
    
}
