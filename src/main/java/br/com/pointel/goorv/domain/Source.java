package br.com.pointel.goorv.domain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Source implements Serializable {

    private final File file;
    
    private String text = null;
    private Parsed parsed = null;

    public Source(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Source read() throws IOException {
        this.text = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        this.parsed = null;
        return this;
    }

    public Source write() throws IOException {
        Files.writeString(file.toPath(), this.text, StandardCharsets.UTF_8);
        return this;
    }

    public Source setText(String text) {
        this.text = text;
        this.parsed = null;
        return this;
    }

    public String getText() {
        return text;
    }

    public Parsed parse() {
        if (parsed == null) {
            parsed = new Parsed(this.text);
        }
        return parsed;
    }

    public String toString() {
        return file.getName();
    }

}
