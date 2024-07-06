package br.com.pointel.goorv;

import br.com.pointel.goorv.service.wizard.WizGlyph;

public class ValuedAs {

    private final String string;
    private final Object object;

    public ValuedAs(String string) {
        this.string = string;
        this.object = null;
    }

    public ValuedAs(Object object) {
        this.string = null;
        this.object = object;
    }

    public boolean hasString() {
        return string != null;
    }

    public boolean hasObject() {
        return object != null;
    }

    public String getString() {
        if (!hasString()) {
            throw new IllegalStateException("No string value available");
        }
        return string;
    }

    public Object getObject() {
        if (!hasObject()) {
            throw new IllegalStateException("No object value available");
        }
        return object;
    }

    public <T> T getValue(Class<T> type) {
        if (hasString()) {
            return getValueFromString(type);
        }
        if (hasObject()) {
            return getValueFromObject(type);
        }
        throw new IllegalStateException("No value available");
    }

    private <T> T getValueFromString(Class<T> type) {
        if (type == Boolean.class) {
            return type.cast(Boolean.parseBoolean(string));
        }
        if (type == Byte.class) {
            return type.cast(Byte.parseByte(string));
        }
        if (type == Short.class) {
            return type.cast(Short.parseShort(string));
        }
        if (type == Integer.class) {
            return type.cast(Integer.parseInt(string));
        }
        if (type == Long.class) {
            return type.cast(Long.parseLong(string));
        }
        if (type == Float.class) {
            return type.cast(Float.parseFloat(string));
        }
        if (type == Double.class) {
            return type.cast(Double.parseDouble(string));
        }
        if (type == Character.class) {
            return type.cast(string.charAt(0));
        }
        if (type == String.class) {
            return type.cast(string);
        }
        if (type == Glyphed.class) {
            return type.cast(WizGlyph.from(string, type));
        }
        throw new IllegalArgumentException("Unsupported type: " + type + " from string value: " + string);
    }

    private <T> T getValueFromObject(Class<T> type) {
        if (type.isInstance(object)) {
            return type.cast(object);
        }
        throw new IllegalArgumentException("Unsupported type: " + type + " from object type: " + object.getClass());
    }

    public Object getValueAny() {
        if (hasString()) {
            return string;
        }
        if (hasObject()) {
            return object;
        }
        throw new IllegalStateException("No value available");
    }

}
