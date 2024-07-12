package br.com.pointel.goorv.domain;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SourceFile extends Source {

    private final File file;

    public SourceFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public String getText() {
        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Could not read the source file.", e);
        }
    }

    public void setText(String text) {
        try {
            Files.writeString(file.toPath(), text, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Could not write the source file.", e);
        }
    }

}
