package br.com.pointel.goorv;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Glyphed {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String clazz;
    private final String value;

    public Glyphed(Object value) throws Exception {
        this.clazz = value.getClass().getName();
        this.value = MAPPER.writeValueAsString(value);
    }

    public Object getValue() throws Exception {
        return MAPPER.readValue(this.value, Class.forName(this.clazz));
    }

}
