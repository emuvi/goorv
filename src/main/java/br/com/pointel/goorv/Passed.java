package br.com.pointel.goorv;

public class Passed {
    
    private final Param param;
    private final String glyphed;

    public Passed(Param param, String glyphed) {
        this.param = param;
        this.glyphed = glyphed;
    }

    public Param getParam() {
        return this.param;
    }

    public String getGlyphed() {
        return this.glyphed;
    }

}
