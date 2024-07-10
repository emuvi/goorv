package br.com.pointel.goorv.domain;

import java.io.File;
import java.io.Serializable;

public class Source implements Serializable {

    private File file;

    public Source(File file) {
        this.file = file;
    }

    public String toString() {
        return file.getName();
    }

}
