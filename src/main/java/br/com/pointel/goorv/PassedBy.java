package br.com.pointel.goorv;

import java.util.ArrayList;
import java.util.List;
import br.com.pointel.goorv.exception.GlyphingException;
import br.com.pointel.goorv.service.wizard.WizGlyph;

public class PassedBy {
    
    private final List<Passed> allPassed;

    public PassedBy() {
        this.allPassed = new ArrayList<>();
    }
    
    public void add(Param param, String glyphed) {
        this.allPassed.add(new Passed(param, glyphed));
    }

    public boolean hasGlyphed(Param param) {
        for (Passed passed : this.allPassed) {
            if (passed.getParam().equals(param)) {
                return true;
            }
        }
        return false;
    }

    public String getGlyphed(Param param) {
        for (Passed passed : this.allPassed) {
            if (passed.getParam().equals(param)) {
                return passed.getGlyphed();
            }
        }
        throw new IllegalArgumentException("Param not found: " + param.getName());
    }

    public String getGlyphed(Param param, String defaultValue) {
        for (Passed passed : this.allPassed) {
            if (passed.getParam().equals(param)) {
                return passed.getGlyphed();
            }
        }
        return defaultValue;
    }

    public List<String> getAllGlyphed(Param param) {
        List<String> allGlyphed = new ArrayList<>();
        for (Passed passed : this.allPassed) {
            if (passed.getParam().equals(param)) {
                allGlyphed.add(passed.getGlyphed());
            }
        }
        return allGlyphed;
    }

    public Boolean getBoolean(Param param) {
        return Boolean.parseBoolean(this.getGlyphed(param));
    }

    public Boolean getBoolean(Param param, Boolean defaultValue) {
        return Boolean.parseBoolean(this.getGlyphed(param, defaultValue.toString()));
    }

    public Integer getInteger(Param param) {
        return Integer.parseInt(this.getGlyphed(param));
    }

    public Integer getInteger(Param param, Integer defaultValue) {
        return Integer.parseInt(this.getGlyphed(param, defaultValue.toString()));
    }

    public Double getDouble(Param param) {
        return Double.parseDouble(this.getGlyphed(param));
    }

    public Double getDouble(Param param, Double defaultValue) {
        return Double.parseDouble(this.getGlyphed(param, defaultValue.toString()));
    }

    public <T> T getValue(Param param, Class<T> type) throws GlyphingException {
        return WizGlyph.from(this.getGlyphed(param), type);
    }

    public <T> T getValue(Param param, Class<T> type, T defaultValue) throws GlyphingException {
        return ! hasGlyphed(param) ? defaultValue : WizGlyph.from(this.getGlyphed(param), type);
    }

    public <T> List<T> getAllValues(Param param, Class<T> type) throws GlyphingException {
        List<T> values = new ArrayList<>();
        for (String value : this.getAllGlyphed(param)) {
            values.add(WizGlyph.from(value, type));
        }
        return values;
    }


}
