package br.com.pointel.goorv.domain;

import java.util.ArrayList;
import java.util.List;

public class PassedBy {
    
    private final List<Passed> allPassed;

    public PassedBy() {
        this.allPassed = new ArrayList<>();
    }
    
    public void add(Param param, ValuedAs sourcing) {
        this.allPassed.add(new Passed(param, sourcing));
    }

    public List<Passed> getAllPassed() {
        return this.allPassed;
    }

    public Passed getPassed(Param param) {
        for (Passed passed : this.allPassed) {
            if (passed.getParam().equals(param)) {
                return passed;
            }
        }
        return null;
    }

    public Passed getPassedOrThrow(Param param) {
        var passed = this.getPassed(param);
        if (passed == null) {
            throw new IllegalArgumentException("Passed not found: " + param.getName());
        }
        return passed;
    }

    public <T> T getValue(Param param, Class<T> clazz) {
        var passed = this.getPassed(param);
        if (passed == null) {
            return null;
        }
        return passed.getValued().getValue(clazz);
    }

    public <T> T getValueOrThrow(Param param, Class<T> clazz) {
        var passed = this.getPassedOrThrow(param);
        return passed.getValued().getValue(clazz);
    }

    public <T> T getValue(Param param, Class<T> clazz, T defaultValue) {
        var value = getValue(param, clazz);
        return value != null ? value : defaultValue;
    }

    public <T> T getValueOrThrow(Param param, Class<T> clazz, T defaultValue) {
        var value = getValueOrThrow(param, clazz);
        return value != null ? value : defaultValue;
    }

    public Boolean getValueBoolean(Param param) {
        return getValue(param, Boolean.class);
    }

    public Boolean getValueBooleanOrThrow(Param param) {
        return getValueOrThrow(param, Boolean.class);
    }

    public Boolean getValueBoolean(Param param, Boolean defaultValue) {
        return getValue(param, Boolean.class, defaultValue);
    }

    public Boolean getValueBooleanOrThrow(Param param, Boolean defaultValue) {
        return getValueOrThrow(param, Boolean.class, defaultValue);
    }

    public Byte getValueByte(Param param) {
        return getValue(param, Byte.class);
    }

    public Byte getValueByteOrThrow(Param param) {
        return getValueOrThrow(param, Byte.class);
    }

    public Byte getValueByte(Param param, Byte defaultValue) {
        return getValue(param, Byte.class, defaultValue);
    }

    public Byte getValueByteOrThrow(Param param, Byte defaultValue) {
        return getValueOrThrow(param, Byte.class, defaultValue);
    }

    public Short getValueShort(Param param) {
        return getValue(param, Short.class);
    }

    public Short getValueShortOrThrow(Param param) {
        return getValueOrThrow(param, Short.class);
    }

    public Short getValueShort(Param param, Short defaultValue) {
        return getValue(param, Short.class, defaultValue);
    }

    public Short getValueShortOrThrow(Param param, Short defaultValue) {
        return getValueOrThrow(param, Short.class, defaultValue);
    }

    public Integer getValueInteger(Param param) {
        return getValue(param, Integer.class);
    }

    public Integer getValueIntegerOrThrow(Param param) {
        return getValueOrThrow(param, Integer.class);
    }

    public Integer getValueInteger(Param param, Integer defaultValue) {
        return getValue(param, Integer.class, defaultValue);
    }

    public Integer getValueIntegerOrThrow(Param param, Integer defaultValue) {
        return getValueOrThrow(param, Integer.class, defaultValue);
    }

    public Long getValueLong(Param param) {
        return getValue(param, Long.class);
    }

    public Long getValueLongOrThrow(Param param) {
        return getValueOrThrow(param, Long.class);
    }

    public Long getValueLong(Param param, Long defaultValue) {
        return getValue(param, Long.class, defaultValue);
    }

    public Long getValueLongOrThrow(Param param, Long defaultValue) {
        return getValueOrThrow(param, Long.class, defaultValue);
    }

    public Float getValueFloat(Param param) {
        return getValue(param, Float.class);
    }

    public Float getValueFloatOrThrow(Param param) {
        return getValueOrThrow(param, Float.class);
    }

    public Float getValueFloat(Param param, Float defaultValue) {
        return getValue(param, Float.class, defaultValue);
    }

    public Float getValueFloatOrThrow(Param param, Float defaultValue) {
        return getValueOrThrow(param, Float.class, defaultValue);
    }

    public Double getValueDouble(Param param) {
        return getValue(param, Double.class);
    }

    public Double getValueDoubleOrThrow(Param param) {
        return getValueOrThrow(param, Double.class);
    }

    public Double getValueDouble(Param param, Double defaultValue) {
        return getValue(param, Double.class, defaultValue);
    }

    public Double getValueDoubleOrThrow(Param param, Double defaultValue) {
        return getValueOrThrow(param, Double.class, defaultValue);
    }

    public Character getValueCharacter(Param param) {
        return getValue(param, Character.class);
    }

    public Character getValueCharacterOrThrow(Param param) {
        return getValueOrThrow(param, Character.class);
    }

    public Character getValueCharacter(Param param, Character defaultValue) {
        return getValue(param, Character.class, defaultValue);
    }

    public Character getValueCharacterOrThrow(Param param, Character defaultValue) {
        return getValueOrThrow(param, Character.class, defaultValue);
    }

    public String getValueString(Param param) {
        return getValue(param, String.class);
    }

    public String getValueStringOrThrow(Param param) {
        return getValueOrThrow(param, String.class);
    }

    public String getValueString(Param param, String defaultValue) {
        return getValue(param, String.class, defaultValue);
    }

    public String getValueStringOrThrow(Param param, String defaultValue) {
        return getValueOrThrow(param, String.class, defaultValue);
    }

    public Glyphed getValueGlyphed(Param param) {
        return getValue(param, Glyphed.class);
    }

    public Glyphed getValueGlyphedOrThrow(Param param) {
        return getValueOrThrow(param, Glyphed.class);
    }

    public Glyphed getValueGlyphed(Param param, Glyphed defaultValue) {
        return getValue(param, Glyphed.class, defaultValue);
    }

    public Glyphed getValueGlyphedOrThrow(Param param, Glyphed defaultValue) {
        return getValueOrThrow(param, Glyphed.class, defaultValue);
    }

    public Object getValueAny(Param param) {
        return getPassedOrThrow(param).getValued().getValueAny();
    }

    public Object getValueAnyOrThrow(Param param) {
        return getPassedOrThrow(param).getValued().getValueAny();
    }

}
