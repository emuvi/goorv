package br.com.pointel.goorv;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.pointel.goorv.service.exception.GlyphingException;

public class Glyphed {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String clazz;
    private final String glyph;

    public Glyphed(Object value, Class<?> type) {
        try {
            this.clazz = type.getName();
            this.glyph = MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            throw new GlyphingException(e);
        }
    }

    public Glyphed(Object value) {
        try {
            this.clazz = value.getClass().getName();
            this.glyph = MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            throw new GlyphingException(e);
        }
    }

    public String getClazz() {
        return this.clazz;
    }

    public String getGlyph() {
        return this.glyph;
    }

    public Object getValue() {
        try {
            return MAPPER.readValue(this.glyph, Class.forName(this.clazz));
        } catch (Exception e) {
            throw new GlyphingException(e);
        }
    }

    public <T> T getValue(Class<T> type) {
        return type.cast(getValue());
    }

    public Boolean getValueBoolean() {
        return getValue(Boolean.class);
    }

    public Byte getValueByte() {
        return getValue(Byte.class);
    }

    public Short getValueShort() {
        return getValue(Short.class);
    }

    public Integer getValueInteger() {
        return getValue(Integer.class);
    }

    public Long getValueLong() {
        return getValue(Long.class);
    }

    public Float getValueFloat() {
        return getValue(Float.class);
    }

    public Double getValueDouble() {
        return getValue(Double.class);
    }

    public Character getValueCharacter() {
        return getValue(Character.class);
    }

    public String getValueString() {
        return getValue(String.class);
    }

    public String toString() {
        return this.glyph;
    }

}
