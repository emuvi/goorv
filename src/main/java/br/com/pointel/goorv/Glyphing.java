package br.com.pointel.goorv;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Glyphing {

    private final Glyphed glyphed;
    private final Throwable error;

    public Glyphing(Object value) {
        Glyphed onGlyphed = null;
        Throwable onError = null;
        try {
            onGlyphed = new Glyphed(value);
            onError = null;
        } catch (Exception e) {
            onGlyphed = null;
            onError = e;
        } finally {
            this.glyphed = onGlyphed;
            this.error = onError;
        }
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

    public Glyphed getGlyphed() {
        if (!hasGlyphed()) {
            throw new IllegalStateException("No glyphed value available");
        }
        return glyphed;
    }

    public Object getValue() {
        if (!hasGlyphed()) {
            throw new IllegalStateException("No glyphed value available");
        }
        try {
            return glyphed.getValue();
        } catch (Exception e) {
            throw new IllegalStateException("Error on get value", e);
        }
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
