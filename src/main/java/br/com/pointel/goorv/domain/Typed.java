package br.com.pointel.goorv.domain;

public class Typed {
    
    private final Class<?> type;
    private final Object value;

    public Typed(Class<?> type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public boolean hasValue() {
        return value != null;
    }

    public boolean hasType() {
        return type != null;
    }

    public boolean hasValueAndType() {
        return hasValue() && hasType();
    }

    public boolean hasValueAndType(Class<?> type) {
        return hasValue() && hasType() && type.isAssignableFrom(this.type);
    }

    public <T> T getValue(Class<T> type) {
        if (type.isAssignableFrom(this.type)) {
            return type.cast(value);
        }
        throw new IllegalStateException("No value available");
    }

    public <T> T getValueOrDefault(Class<T> type, T defaultValue) {
        if (hasValueAndType(type)) {
            return getValue(type);
        }
        return defaultValue;
    }

    public Boolean getBoolean() {
        return getValue(Boolean.class);
    }

    public Boolean getBoolean(Boolean defaultValue) {
        if (hasValueAndType(Boolean.class)) {
            return getBoolean();
        }
        return defaultValue;
    }

    public Byte getByte() {
        return getValue(Byte.class);
    }

    public Byte getByte(Byte defaultValue) {
        if (hasValueAndType(Byte.class)) {
            return getByte();
        }
        return defaultValue;
    }

    public Short getShort() {
        return getValue(Short.class);
    }

    public Short getShort(Short defaultValue) {
        if (hasValueAndType(Short.class)) {
            return getShort();
        }
        return defaultValue;
    }

    public Integer getInteger() {
        return getValue(Integer.class);
    }

    public Integer getInteger(Integer defaultValue) {
        if (hasValueAndType(Integer.class)) {
            return getInteger();
        }
        return defaultValue;
    }

    public Long getLong() {
        return getValue(Long.class);
    }

    public Long getLong(Long defaultValue) {
        if (hasValueAndType(Long.class)) {
            return getLong();
        }
        return defaultValue;
    }

    public Float getFloat() {
        return getValue(Float.class);
    }

    public Float getFloat(Float defaultValue) {
        if (hasValueAndType(Float.class)) {
            return getFloat();
        }
        return defaultValue;
    }

    public Double getDouble() {
        return getValue(Double.class);
    }

    public Double getDouble(Double defaultValue) {
        if (hasValueAndType(Double.class)) {
            return getDouble();
        }
        return defaultValue;
    }

    public Character getCharacter() {
        return getValue(Character.class);
    }

    public Character getCharacter(Character defaultValue) {
        if (hasValueAndType(Character.class)) {
            return getCharacter();
        }
        return defaultValue;
    }

    public String getString() {
        return getValue(String.class);
    }

    public String getString(String defaultValue) {
        if (hasValueAndType(String.class)) {
            return getString();
        }
        return defaultValue;
    }

}
