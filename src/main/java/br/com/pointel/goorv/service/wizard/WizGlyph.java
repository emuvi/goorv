package br.com.pointel.goorv.service.wizard;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.pointel.goorv.exception.GlyphingException;

public class WizGlyph {

    private static final ObjectMapper GLYPHER = new ObjectMapper();

    private WizGlyph() {}

    public static String make(Object value) {
        try {
            return GLYPHER.writeValueAsString(value);
        } catch (Exception e) {
            throw new GlyphingException(e);
        }
    }

    public static <T> T from(String glyph, Class<T> type) {
        try {
            return GLYPHER.readValue(glyph, type);
        } catch (Exception e) {
            throw new GlyphingException(e);
        }
    }

}
