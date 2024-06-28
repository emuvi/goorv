package br.com.pointel.goorv;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Glyphing {

    private final String glyphed;
    private final Throwable error;

    public Glyphing(String glyphed) {
        this.glyphed = glyphed;
        this.error = null;
    }

    public Glyphing(Throwable error) {
        this.glyphed = null;
        this.error = error;
    }

    public boolean hasGlyphed() {
        return glyphed != null;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getGlyphed() {
        if (!hasGlyphed()) {
            throw new IllegalStateException("No glyphed value available");
        }
        return glyphed;
    }

    public Throwable getError() {
        if (!hasError()) {
            throw new IllegalStateException("No error available");
        }
        return error;
    }

    public String getErrorStack() {
        if (!hasError()) {
            throw new IllegalStateException("No error available");
        }
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        error.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
