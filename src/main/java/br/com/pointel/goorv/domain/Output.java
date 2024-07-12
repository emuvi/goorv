package br.com.pointel.goorv.domain;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Output {

    private final Object value;
    private final Throwable error;

    public Output(Object value) {
        this.value = value;
        this.error = null;
    }

    public Output(Throwable error) {
        this.value = null;
        this.error = error;
    }

    public boolean hasValue() {
        return value != null;
    }

    public Object getValue() {
        if (!hasValue()) {
            throw new IllegalStateException("No value available");
        }
        return value;
    }

    public boolean hasError() {
        return error != null;
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

    public String toString() {
        if (hasValue()) {
            return value.toString();
        } else if (hasError()) {
            return error.getMessage() + "\n" + getErrorStack();
        } else {
            return "No value or error available";
        }
    }

}
